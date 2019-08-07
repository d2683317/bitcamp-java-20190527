// v35_2: serverstop 명령어에 대해 JVM 강제 종료하기!
package com.eomcs.lms;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import com.eomcs.lms.context.ServletContextListener;
import com.eomcs.lms.servlet.Servlet;

public class ServerApp{

  ArrayList<ServletContextListener> listeners = new ArrayList<>();
  int port;

  // 서버가 실행되는 동안 공유할 객체를 보관하는 저장소를 준비한다.
  HashMap<String, Object> servletContext = new HashMap<>();

  // 스레드 풀
  ExecutorService executorService = Executors.newCachedThreadPool();

  public ServerApp(int port) {
    this.port = port;
  }

  public void execute() {
    System.out.println("[수업관리시스템 서버 애플리케이션]");

    try (ServerSocket serverSocket = new ServerSocket(this.port)) {
      System.out.println("서버 시작!");

      // 서버가 시작되면 보고를 받을 관찰자(observer)에게 보고한다.
      for(ServletContextListener listener : listeners) {
        listener.contextInitialized(servletContext);
      }

      while (true) {
        System.out.println("클라이언트 요청을 기다리는 중...");

        Socket socket = serverSocket.accept();

        // 스레드풀을 사용할 때는 직접 스레드를 만들지 않는다.
        // 단지 스레드풀에게 "스레드가 실행할 코드(Runnable 구현체)"를 제출한다.
        // => 스레드풀은 남아 있는 스레드가 없으면 새로 만들어 RequestHandler를 실행할 것이다.
        // => 남아 있는 스레드가 있다면 그 스레드를 이용하여 RequestHandler를 실행할 것이다.
        executorService.submit(new RequestHandler(socket));
      } // while

    } catch (Exception e) {
      e.printStackTrace();
    } 
  }

  // 서버가 시작하거나 종료할 때 보고를 받을 객체를 등록하는 메서드
  // => 즉 서블릿을 실행하는데 필요한 환경을 준비시키는 객체를 등록한다.
  public void addServletContextListener(ServletContextListener listener) {
    listeners.add(listener);
  }

  private Servlet findServlet(String command) {
    Set<String> keys = servletContext.keySet();

    // 명령어에 포함된 키를 찾아서, 해당 키로 저장된 서블릿을 꺼낸다.
    // => 명령(/board/list) : 키(/board/)
    for (String key : keys) {
      if (command.startsWith(key)) {
        return (Servlet)servletContext.get(key);
      }
    }
    return null;
  }

  // serverstop 명령 처리
  private void stop() {
    // 서버가 종료될 때 관찰자(observer)에게 보고한다.
    for (ServletContextListener listener : listeners) {
      listener.contextdestroyed(servletContext);
    }

    // 스레드풀에게 동작을 멈추라고 알려준다. 그리고 즉시 리턴한다.
    // => 그러면 스레드풀은 작업 중인 모든 스레드가 작업이 완료될 때까지 기다렸다가
    //    스레드풀의 작업을 종료한다.
    executorService.shutdown();
    
    System.out.println("서버 종료!");
    System.exit(0); // 단점! 현재 실행 중인 스레드까지 강제 종료시킨다.
  }

  // Thread를 상속 받아 직접 스레드 역할을 하는 대신에
  // Thread를 통해 실행할 코드를 정의한다.
  private class RequestHandler implements Runnable {

    Socket socket;

    // 클라이언트가 접속을 하면 그 소켓 정보를 가지고 이 클래스의 인스턴스를 생성한다.
    // => 그런 후 스레드를 분리하여 시작시키면, run()이 실행된다.
    public RequestHandler(Socket socket) {
      this.socket = socket;
    }

    // 별도의 실행 흐름(실;스레드)은 run() 메서드에 작성한다.
    @Override
    public void run() {
      try (Socket clientSocket = this.socket;
          ObjectInputStream in =
              new ObjectInputStream(clientSocket.getInputStream());
          ObjectOutputStream out =
              new ObjectOutputStream(clientSocket.getOutputStream())) {

        System.out.println("클라이언트와 연결되었음.");

        // 클라이언트가 보낸 명령을 읽는다.
        String command = in.readUTF();
        System.out.println(command + " 요청 처리중...");

        Servlet servlet = null;

        if(command.equals("serverstop")) {
          stop();
          return;

        } else if((servlet = findServlet(command)) != null) {
          servlet.service(command, in, out);

        } else {
          out.writeUTF("fail");
          out.writeUTF("지원하지 않는 명령입니다.");
        }

        out.flush();
        System.out.println("클라이언트에게 응답 완료!");

      } catch (Exception e) {
        System.out.println("클라이언트와의 통신 오류! - " + e.getMessage());
      }

      System.out.println("클라이언트와 연결을 끊었음.");
    }


  }

  public static void main(String[] args) {

    ServerApp server = new ServerApp(8888);

    // 서버의 시작과 종료 상태를 보고 받을 객체를 등록한다.
    // => 보고를 받는 객체는 ServletContextListener 규칙에 따라 만든 클래스여야 한다.
    server.addServletContextListener(new AppInitListener());

    server.execute();
  }
}
