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
<link href="<%=request.getContextPath()%>/css/style_index.css" rel="stylesheet" type="text/css" />
<title>Social Base</title>
</head>
<body id="">
<div id="wrapper">
	<div id="header">
    	<div id="logo">
        	<a href="index.html">social base</a>
        </div>
    </div>
    <div id="main">
    	<div id="join_hd">
    		<h2>ユーザー登録</h2>
            <p>登録情報を入力してください</p>
        </div>
        	<form action="UserInputServlet" method="post">
            <table id="form">
            	<tr>
                	<td>
                    	<p>ユーザーID</p>
                        <p class="err">${ idErr }</p>
                    </td>
                </tr>
                <tr>
                	<td>
                    	<input type="text" name="userid" value="${ userid }" />
                    </td>
                </tr>
                <tr>
                	<td>
                    	<p>パスワード</p>
                        <p class="err">${ pwErr }</p>
                    </td>
                </tr>
                <tr>
                	<td>
                    	<input type="password" name="pw" />
                    </td>
                </tr>
                <tr>
                	<td>
                    	<p>パスワード（確認入力）</p>
                    </td>
                </tr>
                <tr>
                	<td>
                    	<input type="password" name="pw2" />
                    </td>
                </tr>
                <tr>
                	<td>
                    	<p>姓</p>
                        <p class="err">${ lnameErr }</p>
                    </td>
                    <td>
                    	<p>名</p>
                        <p class="err">${ fnameErr }</p>
                    </td>
                </tr>
                <tr>
                	<td>
                    	<input type="text" name="lname" value="${ lname }" />
                    </td>
                    <td>
                    	<input type="text" name="fname" value="${ fname }" />
                    </td>
                </tr>
                <tr>
                	<td>
                    	<p>ニックネーム</p>
                        <p class="err">${ hnErr }</p>
                    </td>
                </tr>
                <tr>
                	<td>
                    	<input type="text" name="hn" value="${ hn }" />
                    </td>
                </tr>
                <tr>
                	<td>
                    	<p>メールアドレス</p>
                        <p class="err">${ mailErr }</p>
                    </td>
                </tr>
                <tr>
                	<td>
                    	<input type="text" name="mail" value="${ mail }" />
                    </td>
                </tr>
            </table>
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
