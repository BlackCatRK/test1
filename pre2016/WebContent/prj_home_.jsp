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
<p>${ sessionScope.userS.hn }＝サン</p>
<p>${ msg }</p>
<form action="${ joinOrLeave }" method="post">
	<input type="submit" value="${ JorL }">
</form>
<h2>${ sessionScope.prjS.name }</h2>
<h3>説明</h3>
<p>${ sessionScope.prjS.descrpt }</p>
<c:if test="${ !empty sessionScope.puS }">
<p>参加中</p>
<p>プロジェクト内HN:${ sessionScope.puS.hn }</p>
<a href="createbbs.jsp">スレ立て</a><br />
<a href="createevent.jsp">イベント立て</a><br />
<a href="MemberListServlet">所属メンバー一覧</a><br />
<h2>プロジェクト内掲示板一覧</h2>
<c:forEach var="p" items="${ bbsL }">
<a href="BbsServlet?bid=${ p.id }">${ p.title }</a><br />
</c:forEach>
<h2>プロジェクト内イベント一覧</h2>
<c:forEach var="q" items="${ eventL }">
<a href="EventServlet?eid=${ q.id }">${ q.stdate }:${ q.name }</a><br />
</c:forEach>
</c:if>
<h2>カレンダー</h2>
<a href="CalendarServletFull">カレンダー</a>
</body>
</html>