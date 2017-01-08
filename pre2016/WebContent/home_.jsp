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
<h1>テストページ、ドスェ</h1>
ドーモ、${ sessionScope.userS.hn }＝サン。テストシステムです。
<h2>ユーザー情報、ドスェ</h2>
ユーザーID：${ sessionScope.userS.userid }<br />
名前：${ sessionScope.userS.name }<br />
半値：${ sessionScope.userS.hn }<br />
メール：${ sessionScope.userS.mail }<br />
<h2>稼働中プロジェクト一覧、ドスェ</h2>
<c:forEach var="p" items="${ prjS0 }">
<a href="ProjectServlet?id=${ p.id }">${ p.id }:${ p.name }</a><br />
</c:forEach>
<a href="ListProjectServlet">もっと見る</a><br />
<h2>参加中プロジェクト一覧、ドスェ</h2>
<c:forEach var="p" items="${ prjnS0 }">
<a href="ProjectServlet?id=${ p.id }">${ p.id }:${ p.name }</a><br />
</c:forEach>
<h2>所属中各プロジェクト最新BBS書き込み一覧、ドスェ</h2>
<c:forEach var="p" items="${ bbsh }">
<a href="#">prjid=${ p.prj_id },prjname=${ p.prj_name },bbsid=${ p.bbs_id },bbsname=${ p.bbs_title }</a><br />
upddate=${ p.upddate }<br />
</c:forEach>
<h2>所属中各プロジェクト最新イベント書き込み一覧、ドスェ</h2>
<c:forEach var="p" items="${ eventh }">
<a href="#">prjid=${ p.prj_id },prjname=${ p.prj_name },eventid=${ p.event_id },eventname=${ p.event_name }</a><br />
upddate=${ p.upddate }<br />
</c:forEach>
<h2>ログアウト、ドスェ</h2>
<a href="LogoutServlet">オタッシャデー！</a>
<h2>退会、ドスェ</h2>
<a href="outSystem.jsp">退会</a>
</body>
</html>