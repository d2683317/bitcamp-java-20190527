<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>수업 등록폼</title>
        <link rel='stylesheet' href='https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css' integrity='sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T' crossorigin='anonymous'>"
        <link rel='stylesheet' href='/css/common.css'>
        </head>
    <body>
    
<jsp:include page="/jsp/header.jsp"/>
    
<div id='content'>
<body><h1>수업 등록폼</h1>
<form action='/lesson/add' method='post'>
수업명:<input type='text' name='title'><br>
설명 : <textarea name='contents' rows='5' cols='50'></textarea><br>
시작일:<input type='text' name='startDate'><br>\n
종료일:<input type='text' name='endDate'><br>\n
총 수업시간:<input type='text' name='totalHours'><br>\n
일 수업시간:<input type='text' name='dayHours'><br>\n
<button>등록</button>
</form>
    
</div>

<jsp:include page="/jsp/footer.jsp"/>

</body>
</html>