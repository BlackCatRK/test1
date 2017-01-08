<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<meta http-equiv="Content-Style-Type" content="text/css" />
<meta name="description" content="Social Base" />
<meta name="keywords" content="Social Base" />
<link href="css/cssreset-min.css" rel="stylesheet" type="text/css" />
<link href="<%=request.getContextPath()%>/css/style_calendar.css" rel="stylesheet" type="text/css" />
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
    	<p>${ year }年${ month+1 }月</p>
	<a href="NorPFServlet?year=${ year }&month=${ month }">前月＜</a><a href="CalendarServletFull">当月</a><a href="NorPFServlet?year=${ year }&month=${ month+2 }">＞次月</a>
	<table>
	<tr><td class="week">日</td><td class="week">月</td><td class="week">火</td><td class="week">水</td><td class="week">木</td><td class="week">金</td><td class="week">土</td></tr>
	</table>
	<div id="calender">
		<c:forEach var="p" items="${ days }" begin="1" end="42" step="1" varStatus="status">
			<div class="daycell<c:choose><c:when test="${status.index>35&&status.index%7==0}">last</c:when><c:when test="${status.index%7==0}">7</c:when><c:when test="${status.index>35}">b</c:when></c:choose>">
				<div class="day<c:if test="${ p.year==year&&p.month==month&&p.day==day }">crr</c:if>">
					<p class="date<c:choose><c:when test="${status.index%7==0}">sat</c:when><c:when test="${status.index%7==1}">sun</c:when></c:choose>">${ p.day }</p>
				</div>
				<div class="${ p.csscls }">
					<c:forEach var="q" items="${ p.data }" begin="0" end="1">
						<a href="EventServlet?eid=${ q.data1 }&page=1">${ q.data2 }</a><br />
					</c:forEach>
					<c:if test="${ fn:length(p.data)>2 }"><a href="#">もっと見る</a></c:if>
				</div>
			</div>
		</c:forEach>
	</div>
    </div>
    <div class="clear">
    </div>
    <div id="footer_l">
    	<p>Copyright (C) 2016 Social Base All rights reserved.</p>
    </div>
</div>
</body>
</html>
