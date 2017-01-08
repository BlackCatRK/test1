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
<h1>メンバー詳細</h1>
<h3>名前</h3>
<p>${ mem.name }</p>
<h3>ニックネーム</h3>
<p>${ mem.hn }</p>
<h3>メール</h3>
<p>${ mem.mail }</p>
<h3>参加日</h3>
<p>${ mem.indate }</p>
</body>
</html>