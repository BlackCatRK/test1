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
<h2>プロジェクト一覧</h2>
<c:forEach var="p" items="${ prjL }">
<a href="ProjectServlet?id=${ p.id }">${ p.id }:${ p.name }</a><br />
<p>${ p.descrpt }</p>
</c:forEach>
<h2>プロジェクト作成</h2>
<a href="createprj.jsp">作成</a>
</body>
</html>