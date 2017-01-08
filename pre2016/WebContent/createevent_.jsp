<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<h1>スレ立て</h1>
<p>${ errMsg }</p>
<form action="CreateEventServlet" method="post">
	<input type="text" name="name" value="${ name }" />${ nmErr }<br />
	<textarea cols="40" rows="5" name="desc">${ desc }</textarea><br />${ olErr }<br />
	<p>予定日</p>
	<select id="year" name="yyyy1">
		<option value="2016">2016</option>
		<option value="2017">2017</option>
	</select>
	<select id="month" name="MM1">
		<option value="1">1</option>
		<option value="2">2</option>
		<option value="3">3</option>
		<option value="4">4</option>
		<option value="5">5</option>
	</select>
	<select id="day" name="dd1">
		<option value="1">1</option>
		<option value="2">2</option>
		<option value="3">3</option>
		<option value="4">4</option>
		<option value="5">5</option>
	</select><br />
	<select id="hh" name="hh1">
		<option value="1">1</option>
		<option value="2">2</option>
		<option value="3">3</option>
		<option value="4">4</option>
		<option value="5">5</option>
	</select>
	<select id="mm" name="mm1">
		<option value="1">1</option>
		<option value="2">2</option>
		<option value="3">3</option>
		<option value="4">4</option>
		<option value="5">5</option>
	</select><br />
	<p>参加締め切り日</p>
	<select id="year" name="yyyy2">
		<option value="2016">2016</option>
		<option value="2017">2017</option>
	</select>
	<select id="month" name="MM2">
		<option value="1">1</option>
		<option value="2">2</option>
		<option value="3">3</option>
		<option value="4">4</option>
		<option value="5">5</option>
	</select>
	<select id="day" name="dd2">
		<option value="1">1</option>
		<option value="2">2</option>
		<option value="3">3</option>
		<option value="4">4</option>
		<option value="5">5</option>
	</select><br />
	<select id="hh" name="hh2">
		<option value="1">1</option>
		<option value="2">2</option>
		<option value="3">3</option>
		<option value="4">4</option>
		<option value="5">5</option>
	</select>
	<select id="mm" name="mm2">
		<option value="1">1</option>
		<option value="2">2</option>
		<option value="3">3</option>
		<option value="4">4</option>
		<option value="5">5</option>
	</select><br />
	<input type="submit" value="スレ立て" />
</form>
</body>
</html>