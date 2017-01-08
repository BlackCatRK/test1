<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<h1>退会ドスェ</h1>
${ errMsg }
<form action="OutServiceServlet" method="post">
	<input type="password" name="pw" /><br />
	<input type="password" name="pw2" /><br />
	<input type="hidden" name="userid" value="${ sessionScope.userS.userid }">
	<input type="submit" value="退会" />
</form>
</body>
</html>