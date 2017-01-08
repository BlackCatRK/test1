<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
${ errMsg }
<form action="LoginServlet" method="post">
	id:<input type="text" name="userid" /><br />
	pw:<input type="password" name="pw" /><br />
	<input type="submit" value="login" />
</form>
</body>
</html>