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
<script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jquery/1.8.3/jquery.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/selectdate.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/selectdate2.js"></script>
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
            	<a href="ListBbsServlet">スレッド一覧</a>
            </li>
            <li>
            	<a href="ListEventServlet">イベント一覧</a>
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
    		<h2>イベント作成</h2>
            <p>イベントの情報を入力してください</p>
        </div>
        <form action="CreateEventServlet" method="post">
        <div class="unit">
            <table class="form">
            	<tr>
                	<td>
                    	<p>イベントのタイトル</p>
                        <p class="err">${ titleErr }</p>
                    </td>
                </tr>
                <tr>
                	<td>
                    	<input type="text" name="name" value="${ title }" />
                    </td>
                </tr>
                <tr>
                	<td>
                    	<p>イベントの説明</p>
                        <p class="err">${ descErr }</p>
                    </td>
                </tr>
                <tr>
                	<td>
                    	<textarea cols="40" rows="5" name="desc">${ desc }</textarea>
                    </td>
                </tr>
                <tr>
                	<td>
                    	<p>イベントの予定日時</p>
                    </td>
                </tr>
                <tr>
                	<td>
                    	<p><select id="year" name="yyyy1" onchange="setMonth()">
                            
                        </select>年
                        <select id="month" name="MM1" onchange="leapYearCheck()">
                            
                        </select>月
                        <select id="day" name="dd1" onchange="setHour()">
                            
                        </select>日<br />
                        <select id="hour" name="hh1" onchange="setMinute()">
                            
                        </select>時
                        <select id="minute" name="mm1">
                            
                        </select>分</p>
                    </td>
                </tr>
                <tr>
                	<td>
                    	<p>イベントの参加締切日時</p>
                    </td>
                </tr>
                <tr>
                	<td>
                    	<p><select id="year2" name="yyyy2" onchange="setMonth2()">
                            
                        </select>年
                        <select id="month2" name="MM2" onchange="leapYearCheck2()">
                            
                        </select>月
                        <select id="day2" name="dd2" onchange="setHour2()">
                            
                        </select>日<br />
                        <select id="hour2" name="hh2" onchange="setMinute2()">
                            
                        </select>時
                        <select id="minute2" name="mm2">
                            
                        </select>分</p>
                    </td>
                </tr>
            </table>
    	</div>
        <input type="submit" value="イベントを登録" />
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
