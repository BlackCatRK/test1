<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<h1>${ bbs.title }</h1>
<p>${ bbs.descrpt }</p>
<c:forEach var="p" items="${ bbswL }">
<p>${ p.write_num }</p>
<p>${ p.text }</p>
<a href="MemberServlet?mid=${ p.author_id }">${ p.author }:${ p.w_date }</a>
</c:forEach>
<h3>書き込む</h3>
<form action="BbsWriteServlet" method="post">
<textarea cols="50" rows="10" name="text">
</textarea><br />
<input type="hidden" value="${ bid }" name="bid" />
<input type="submit" value="コメントする" />
</form>
</body>
</html>