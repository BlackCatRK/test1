<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<meta http-equiv="Content-Style-Type" content="text/css" />
<meta name="description" content="Social Base" />
<meta name="keywords" content="Social Base" />
<link href="<%=request.getContextPath()%>/css/cssreset-min.css" rel="stylesheet" type="text/css" />
<link href="<%=request.getContextPath()%>/css/style.css" rel="stylesheet" type="text/css" />
<title>Social Base</title>
</head>
<body id="">
<div id="wrapper">
	<div id="header">
    	<div id="logo">
        	<a href="HomeServlet">social base</a>
        </div>
	    <div id="navi_header">
   	    <p>${ userS.name }さん</p>
            <a href="LogoutServlet">ログアウト</a>
        </div>
    </div>
    <div id="navi">
    	<ul>
        	<li>
            	<a href="ProjectServlet?id=${ sessionScope.prjS.id }">プロジェクトホーム</a>
            </li>
            <li>
            	<a href="#">スレッド一覧</a>
            </li>
            <li>
            	<a href="#">イベント一覧</a>
            </li>
            <li>
            	<a href="CalendarServletFull">カレンダー</a>
            </li>
            <li>
            	<c:choose>
            		<c:when test="${ empty sessionScope.puS }"><a href="join.jsp">参加</a></c:when>
            		<c:when test="${ !empty sessionScope.puS }"><a href="LeaveServlet">脱退</a></c:when>
            	</c:choose>
            </li>
        </ul>
    </div>
    <div class="clear">
    </div>
    <div id="main">
    	<div class="unit_hd">
    		<h2>プロジェクトへの参加</h2>
            <p>ユーザーの情報を入力してください</p>
        </div>
        <form method="post" action="JoinServlet">
        <div class="unit">
            <table class="form">
            	<tr>
                	<td>
                    	<p>名前の公開</p>
                    </td>
                </tr>
                <tr>
                	<td>
                    	<p><input type="radio" name="nameflg" value="0" checked />非公開<input type="radio" name="nameflg" value="1" />公開</p>
                    </td>
                </tr>
                <tr>
                	<td>
                    	<p>メールアドレスの公開</p>
                    </td>
                </tr>
                <tr>
                	<td>
                    	<p><input type="radio" name="adrsflg" value="0" checked />非公開<input type="radio" name="adrsflg" value="1" />公開</p>
                    </td>
                </tr>
                <tr>
                	<td>
                    	<p>プロジェクト内で使用するニックネーム</p>
                        <p class="err">${ hnErr }</p>
                    </td>
                </tr>
                <tr>
                	<td>
                    	<p><input type="text" name="hn" value="${ sessionScope.userS.hn }" /></p>
                    </td>
                </tr>
                <tr>
                	<td>
                    	<p>プロジェクト内で使用するメールアドレス</p>
                        <p class="err">${ mailErr }</p>
                    </td>
                </tr>
                <tr>
                	<td>
                    	<p><input type="text" name="mail" value="${ sessionScope.userS.mail }" /></p>
                    </td>
                </tr>
            </table>
    	</div>
        <input type="submit" value="参加" />
        </form>
    </div>
    <div class="clear">
    </div>
    <div id="footer_l">
    	<p>Copyright (C) 2016 Social Base All rights reserved.</p>
    </div>
</div>
</body>
</html>
