package ch12.d;

public class Patient {
  public final static int WOMAN = 1;
  public final static int MAN = 2;

  String name;
  int age;
  int height;
  int weight;
  int gender;

  public String toString() {
    return String.format("name=%s, age=%d, height=%d, weight=%d, gender=%d", this.name,
        this.age, this.height, this.weight, this.gender);
    // String s = "name=" + name + ", age=" + age + ", height=" + height + ", weight=" + weight
    // + ", gender=" + gender;
  }
}
