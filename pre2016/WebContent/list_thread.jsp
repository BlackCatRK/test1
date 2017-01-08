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
<link href="css/cssreset-min.css" rel="stylesheet" type="text/css" />
<link href="css/style_prj.css" rel="stylesheet" type="text/css" />
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
    	<div id="home_l">
        	<div id="prj">
            	<h2>${ sessionScope.prjS.name }</h2>
            	<c:if test="${ !empty sessionScope.puS }">
            	<p>${ sessionScope.puS.hn }さん</p>
            	</c:if>
            </div>
            <div class="ctg">
            	<div class="ctg_header">
	            		<a href="MemberListServlet" class="ctg_title">所属メンバー一覧</a>
	                </div>
	                <div class="ctg_list">
	                    <c:forEach var="p" items="${ sessionScope.memL }" begin="0" end="4">
							<a href="MemberServlet?mid=${ p.user_id }">${ p.hn }</a><br />
						</c:forEach>
	                </div>
	                <div class="ctg_more">
	                	<a href="MemberListServlet">もっと見る</a>
	                </div>
            </div>
        </div>
        <div id="home_r">
        	<div class="ctg_r">
                <div class="ctg_r_hd">
                    <a href="" class="ctg_title_r">スレッド一覧</a>
                </div>
                <div class="ctg_list_r">
                   	<c:forEach var="q" items="${ bbsL }">
						<tr>
                           	<p>${ q.upddate }　<a href="BbsServlet?bid=${ q.id }&page=1">${ q.title }</a></p>
                        </tr>
					</c:forEach>
                </div>
                <div class="ctg_more">
                	<a href="createbbs.jsp">スレッドの作成</a>
                </div>
            </div>
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
