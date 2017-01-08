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
<link href="<%=request.getContextPath()%>/css/style_prj.css" rel="stylesheet" type="text/css" />
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
            <a href="LogoutServlet">ログアウト</a><br />
            <a href="#">個人設定</a>
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
    	<div id="home_l">
        	<div id="prj">
            	<h2>${ sessionScope.prjS.name }</h2>
            	<p>${ sessionScope.puS.hn }さん</p>
            </div>
            <div class="ctg">
            	<div class="ctg_header">
            		<a href="" class="ctg_title">所属メンバー一覧</a>
                </div>
                <div class="ctg_list">
                    <a href="">hoge</a><br />
                    <a href="">hoge</a><br />
                    <a href="">hoge</a><br />
                </div>
                <div class="ctg_more">
                	<a href="">もっと見る</a>
                </div>
            </div>
        </div>
        <div id="home_r">
        	<div class="ctg_r">
                <div class="ctg_r_hd">
                    <a href="" class="ctg_title_r">${ bbs.title }</a>
                </div>
                <div class="ctg_list_r">
                    <p>${ bbs.descrpt }</p>
                </div>
            </div>
            <c:forEach var="p" items="${ bbswL }">
	            <div class="bbs_w">
	            	<div class="write_num">
	                	<p>${ p.write_num }</p>
	                </div>
	            	<div class="bbs_text">
	                	<p>${ p.text }</p>
	                </div>
	                <div class="bbs_w_footer">
	                	<a href="MemberServlet?mid=${ p.author_id }">${ p.author }</a>
	                    <p>${ p.w_date }</p>
	                </div>
	            </div>
	        </c:forEach>
            <div class="ctg_r">
                <div class="ctg_r_hd">
                    <h2>書き込む</h2>
                </div>
                <div class="writing">
                    <form action="BbsWriteServlet" method="post">
                    <textarea cols="65" rows="10" name="text"></textarea>
                    <input type="submit" value="コメントする" />
                    <input type="hidden" name="bid" value="${ requestScope.bbs.id }" />
				</form>
                </div>
            </div>
        </div>
    </div>
    <div id="footer">
    	<ul>
        	<li>
            	<a href="index.html">ホーム</a>
            </li>
            <li>
            	<a href="index.html">dummy</a>
            </li>
            <li>
            	<a href="index.html">dummy</a>
            </li>
        </ul>
    </div>
    <div id="footer_l">
    	<p>Copyright (C) 2016 Social Base All rights reserved.</p>
    </div>
</div>
</body>
</html>
