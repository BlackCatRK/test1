<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" Content="text/html;charset=utf-8">
<meta http-equiv="Content-Style-Type" content="text/css">
<meta http-equiv="Content-Script-Type" content="text/javascript">

<title>スケジュール管理</title>

<style>
html{
	font-family:"ヒラギノ角ゴ Pro W3", "Hiragino Kaku Gothic Pro", "メイリオ", Meiryo, Osaka, "ＭＳ Ｐゴシック", "MS PGothic", sans-serif;	
}

#wrapper{width: 960px;}

table{border:1px solid #a9a9a9;width:960px;;padding:0px;margin:0px;border-collapse:collapse;}

#calender{display:table; border-collaplse:collapse;}

td{width:12%;border-top:1px solid #a9a9a9;border-left:1px solid #a9a9a9;vertical-align:top;margin:0px;padding:2px;}
.daycell{float:left; display:table-cell;width:132px;border-top:1px solid #a9a9a9;border-left:1px solid #a9a9a9;vertical-align:top;margin:0px;padding:2px;}
.daycell7{float:left; display:table-cell;width:132px;border-top:1px solid #a9a9a9;border-left:1px solid #a9a9a9;border-right:1px solid #a9a9a9;vertical-align:top;margin:0px;padding:2px;}
.daycellb{float:left; display:table-cell;width:132px;border-top:1px solid #a9a9a9;border-left:1px solid #a9a9a9;border-bottom:1px solid #a9a9a9;vertical-align:top;margin:0px;padding:2px;}
.daycelllast{float:left; display:table-cell;width:132px;border:1px solid #a9a9a9;vertical-align:top;margin:0px;padding:2px;}
td.week{background-color:#f0f8ff;text-align:center;}
.day{background-color:#f5f5f5;text-align:right;font-size:0.75em;}
.daycrr{background-color:#ddffff;text-align:right;font-size:0.75em;}

.crr{background-color:#fffffff;text-align:left;height:140px;}
.noncrr{background-color:#eeeeee;text-align:left;height:140px;}

img{border:0px;}
p{font-size:0.9em;}
.date{display: inline;}
.datesun{display: inline; color: #F00;}
.datesat{display: inline; color: #00F;}
</style>

</head>
<body>
<div id="wrapper">
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
					<img src="./img/memo.png" width="14" height="16"><br />
					<c:forEach var="q" items="${ p.data }" begin="0" end="1">
						<a href="EventServlet?eid=${ q.data1 }">${ q.data2 }</a><br />
					</c:forEach>
					<c:if test="${ fn:length(p.data)>2 }"><a href="#">もっと見る</a></c:if>
				</div>
			</div>
		</c:forEach>
	</div>
</div>
</body>
</html>
