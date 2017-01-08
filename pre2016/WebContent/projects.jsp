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
            	<a href="#">プロジェクトを作成する</a>
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
            <a href="createprj.jsp">新規プロジェクト作成</a>
    	</div>
        <div id="home_r">
        	<div class="ctg_r">
                <div class="ctg_r_hd">
                    <a href="" class="ctg_title_r">
                    	<c:choose>
                    		<c:when test="${ flg==true }">現在所属中のプロジェクト一覧</c:when>
                    		<c:when test="${ flg==false }">すべてのプロジェクト一覧</c:when>
                    	</c:choose>
                   　			</a>
                </div>
            </div>
            <c:forEach var="p" items="${ prjL }">
				<div class="bbs_w">
	            	<div class="write_num">
	                	<a href="ProjectServlet?id=${ p.id }">${ p.id }:${ p.name }</a>
	                </div>
	            	<div class="bbs_text">
	                	<p>${ p.descrpt }</p>
	                </div>
	                <div class="bbs_w_footer">
	                	<a href="ProjectServlet?id=${ p.id }">詳細</a>
	                </div>
            	</div>
			</c:forEach>
        	<div id="page">
		        <ul>
		        	<c:forEach begin="1" end="${ page }" varStatus="stat">
		        		<li>
		        			<a href="ListProjectServlet?page=${ stat.index }">${ stat.index }</a>
		        		</li>
		        	</c:forEach>
		        </ul>
	        </div>
        </div>
        <div class="clear">
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
