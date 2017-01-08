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
<link href="css/style.css" rel="stylesheet" type="text/css" />
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
    <!-- 
    <div id="navi">
    	<ul>
        	<li>
            	<a href="HomeServlet">ホーム</a>
            </li>
            <li>
            	<a href="#">すべてのプロジェクト</a>
            </li>
            <li>
            	<a href="#">所属プロジェクト一覧</a>
            </li>
            <li>
            	<a href="#">カレンダー</a>
            </li>
            <li>
            	<a href="#">メッセージ</a>
            </li>
        </ul>
    </div>
    <div class="clear">
    </div>
     -->
    <div id="main">
    	<div id="home_l">
        	<div id="user">
            	<h2>${ sessionScope.userS.hn }さん</h2>
            </div>
            <div class="ctg">
            	<div class="ctg_header">
            		<a href="" class="ctg_title">現在所属中のプロジェクト一覧</a>
                </div>
                <div class="ctg_list">
                    <c:forEach var="p" items="${ prjnS0 }" begin="0" end="4">
						<a href="ProjectServlet?id=${ p.id }">${ p.name }</a><br />
					</c:forEach>
                </div>
                <div class="ctg_more">
                	<a href="ListProjectInServlet?page=1">もっと見る</a>
                </div>
            </div>
            <div class="ctg">
            	<div class="ctg_header">
            		<a href="" class="ctg_title">現在稼働中のプロジェクト一覧</a>
                </div>
                <div class="ctg_list">
                    <c:forEach var="p" items="${ prjS0 }" begin="0" end="4">
						<a href="ProjectServlet?id=${ p.id }">${ p.name }</a><br />
					</c:forEach>
                </div>
                <div class="ctg_more">
                	<a href="ListProjectServlet?page=1">もっと見る</a>
                </div>
            </div>
        </div>
        <div id="home_r">
        	<div class="ctg_r">
            	<div class="ctg_r_hd">
                	<h2>スレッド最新書き込み</h2>
                </div>
                <div class="ctg_list_r">
                    <c:forEach var="p" items="${ bbsh }">
						<p>${ p.upddate }　<a href="BbsServletHome?pid=${ p.prj_id }&bid=${ p.bbs_id }">${ p.bbs_title }(${ p.prj_name })</a></p>
					</c:forEach>
                </div>
            </div>
            <div class="ctg_r">
            	<div class="ctg_r_hd">
                	<h2>イベント最新書き込み</h2>
                </div>
                <div class="ctg_list_r">
                    <c:forEach var="p" items="${ eventh }">
						<p>${ p.upddate }　<a href="EventServletHome?pid=${ p.prj_id }&eid=${ p.event_id }">${ p.event_name }(${ p.prj_name })</a></p>
					</c:forEach>
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
