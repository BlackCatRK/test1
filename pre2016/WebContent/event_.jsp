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
<h2>${ requestScope.event.name }</h2>
<h3>説明</h3>
<p>${ requestScope.event.descrpt }</p>
<h3>参加期限</h3>
${ event.limitdate }
<c:if test="${ limitflg==true }">
<p>参加期限を過ぎています</p>
</c:if>
<h3>書き込み一覧</h3>
<c:forEach var="p" items="${ requestScope.ewL }" begin="0" end="20">
<a href="MemberServlet?mid=${ p.author_id }">${ p.write_num }${ p.author }:${ p.w_date }</a>
<p>${ p.text }</p>
</c:forEach>
<h3>書き込む</h3>
<form action="EventWriteServlet" method="post">
<textarea cols="50" rows="10" name="text">
</textarea><br />
<c:if test="${ (limitflg==false)&&(joinflg==false) }">
<input type="submit" name="join" value="参加する"/>
</c:if>
<input type="submit" value="コメントする" />
<input type="hidden" name="eid" value="${ requestScope.event.id }">
</form>
<h3>参加者一覧</h3>
<c:forEach var="s" items="${ requestScope.mlist }" begin="0" end="20">
<a href="MemberServlet?mid=${ s.user_id }">${ s.hn }</a>
</c:forEach>
</body>
</html>