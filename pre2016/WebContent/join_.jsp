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
${ errMsg }
<form method="post" action="JoinServlet">
	name:<input type=radio name="nameflg" value="0" checked>非公開
	<input type=radio name="nameflg" value="1">公開<br />
	adrs:<input type=radio name="adrsflg" value="0" checked>非公開
	<input type=radio name="adrsflg" value="1">公開<br />
	tel:<input type=radio name="telflg" value="0" checked>非公開
	<input type=radio name="telflg" value="1">公開<br />
	hn:<input type="text" name="hn" value="${ sessionScope.userS.hn }" />${ hnErr }<br />
	mail:<input type=text name="mail" value="${ sessionScope.userS.mail }" />${ mailErr }<br />
	<input type="submit" value="参加" />
</form>
</body>
</html>