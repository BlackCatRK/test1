<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<h1>スレ立て</h1>
<p>${ errMsg }</p>
<form action="CreateBbsServlet" method="post">
<input type="text" name="title" value="${ title }" />${ titleErr }<br />
<textarea cols="40" rows="5" name="desc">${ desc }</textarea><br />${ descErr }<br />
<input type="submit" value="スレ立て" />
</form>
</body>
</html>