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
<form action="ProjectCreateServlet" method="post">
<h2>プロジェクト作成</h2>
<p>プロジェクトタイトル</p>
<input type="text" name="name" value="${ name }" /><c:if test="${nameErr!=null||nameErr!=\"\"}" >${ nameErr }</c:if>
<p>説明</p>
<textarea cols="50" rows="10" name="desc">
</textarea>
<p>管理者以外のイベント作成</p>
<p>許可する<input type="radio" name="eventflg" value="1" checked/>許可しない<input type="radio" name="eventflg" value="0" /></p>
<p>管理者以外のスレッド作成</p>
<p>許可する<input type="radio" name="bbsflg" value="1" checked/>許可しない<input type="radio" name="bbsflg" value="0" /></p>
<p>参加への承認</p>
<p>不要<input type="radio" name="joinflg" value="0" checked/>必要<input type="radio" name="bbsflg" value="1" /></p>
<h2>プロジェクト内のプロフィール設定</h2>
name:<input type=radio name="nameflg" value="0" checked>非公開
<input type=radio name="nameflg" value="1">公開<br />
adrs:<input type=radio name="adrsflg" value="0" checked>非公開
<input type=radio name="adrsflg" value="1">公開<br />
tel:<input type=radio name="telflg" value="0" checked>非公開
<input type=radio name="telflg" value="1">公開<br />
hn:<input type="text" name="hn" value="${ sessionScope.userS.hn }" />${ hnErr }<br />
mail:<input type=text name="mail" value="${ sessionScope.userS.mail }" />${ mailErr }<br />
<input type="submit" value="生成" />
</form>
</body>
</html>