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
<form action="UserInputServlet" method="post">
	id<input type="text" name="userid" value="${ userid }" /><c:if test="${idErr!=null||idErr!=\"\"}" >${ idErr }</c:if><br />
	pw<input type="password" name="pw" /><c:if test="${pwErr!=null||pwErr!=\"\"}" >${ pwErr }</c:if><br/>
	pw2<input type="password" name="pw2" /><br/>
	lname<input type="text" name="lname" value="${ lname }" />
	fname<input type="text" name="fname" value="${ fname }" /><c:if test="${lnameErr!=null||idErr!=\"\"}" >${ lnameErr }</c:if>:<c:if test="${fnameErr!=null||fnameErr!=\"\"}" >${ fnameErr }</c:if><br />
	adrs<input type="text" name="adrs" value="${ adrs }" /><c:if test="${adrsErr!=null||adrsErr!=\"\"}" >${ adrsErr }</c:if><br />
	tel<input type="text" name="tel" value="${ tel }" /><c:if test="${telErr!=null||telErr!=\"\"}" >${ telErr }</c:if><br />
	hn<input type="text" name="hn" value="${ hn }" /><c:if test="${hnErr!=null||hnErr!=\"\"}" >${ hnErr }</c:if><br />
	mail<input type="text" name="mail" value="${ mail }" /><c:if test="${mailErr!=null||mailErr!=\"\"}" >${ mailErr }</c:if><br />
	<input type="submit" value="submit"/>
</form>
</body>
</html>