<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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
    	<div class="unit_hd">
    		<h2>プロジェクト作成</h2>
            <p>プロジェクトの情報を入力してください</p>
        </div>
        <form action="ProjectCreateServlet" method="post">
        <div class="unit">
            <table class="form">
            	<tr>
                	<td>
                    	<p>プロジェクトのタイトル</p>
                        <p class="err">${ nameErr }</p>
                    </td>
                </tr>
                <tr>
                	<td>
                    	<input type="text" name="name" value="${ name }" />
                    </td>
                </tr>
                <tr>
                	<td>
                    	<p>プロジェクトの説明</p>
                        <p class="err">${ descErr }</p>
                    </td>
                </tr>
                <tr>
                	<td>
                    	<textarea cols="50" rows="10" name="desc">${ desc }</textarea>
                    </td>
                </tr>
                <tr>
                	<td>
                    	<p>管理者以外のイベント作成を許可</p>
                    </td>
                </tr>
                <tr>
                	<td>
                    	<p>許可する<input type="radio" name="eventflg" value="1" checked />許可しない<input type="radio" name="eventflg" value="0" /></p>
                    </td>
                </tr>
                <tr>
                	<td>
                    	<p>管理者以外の掲示板作成を許可</p>
                    </td>
                </tr>
                <tr>
                	<td>
                    	<p>許可する<input type="radio" name="bbsflg" value="1" checked />許可しない<input type="radio" name="bbsflg" value="0" /></p>
                    </td>
                </tr>
                <tr>
                	<td>
                    	<p>プロジェクトの参加に管理者の承諾</p>
                    </td>
                </tr>
                <tr>
                	<td>
                    	<p>不要<input type="radio" name="joinflg" value="0" checked />必要<input type="radio" name="joinflg" value="1" /></p>
                    </td>
                </tr>
            </table>
    	</div>
        <div class="unit_hd">
        	<h2>プロジェクト内プロフィール作成</h2>
            <p>プロジェクト内で使用するプロフィールを入力してください</p>
        </div>
        <div class="unit">
        	<table class="form">
            	<tr>
                	<td>
                    	<p>プロジェクト内で自分の名前の公開</p>
                        <p><input type="radio" name="nameflg" value="0" checked />非公開<input type="radio" name="nameflg" value="1" />公開</p>
                    </td>
                </tr>
                <tr>
                	<td>
                    	<p>プロジェクト内部で使用するニックネーム</p>
                    	<p class="err">${ hnErr }</p>
                        <input type="text" name="hn" value="${ sessionScope.userS.hn }" />
                    </td>
                </tr>
                <tr>
                	<td>
                    	<p>プロジェクト内で自分のメールアドレスの公開</p>
                        <p><input type="radio" name="adrsflg" value="0" checked />非公開<input type="radio" name="adrsflg" value="1" />公開</p>
                    </td>
                </tr>
                <tr>
                	<td>
                    	<p>プロジェクト内で使用するメールアドレス</p>
                    	<p class="err">${ mailErr }</p>
                        <input type="text" name="mail" value="${ sessionScope.userS.mail }" />
                    </td>
                </tr>
            </table>
        </div>
        <input type="submit" value="登録" />
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
