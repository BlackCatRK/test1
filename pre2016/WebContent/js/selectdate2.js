/**
 * 
 */

var myD = new Date();

	var myYear = myD.getFullYear();
	var myMonth = myD.getMonth() + 1;
	var myDate = myD.getDate();
	var myHour = myD.getHours();
	var myMinute = myD.getMinutes();
	var flg = false;

$(function setYear2(){
	//今年から10年分表示
		
    for (var i = myYear; i <= myYear+10; i++) {
        if(i === myYear){
            $('#year2').append('<option value="'+i+'" selected>'+i+'</option>');
        } else {
            $('#year2').append('<option value="'+i+'">'+i+'</option>');
        }
    }
    setMonth2();
});

function setMonth2(){
	//1月～12月まで表示
	$('#month2').empty();
    if($('#year2').val()==myYear){
    	for (var i = myMonth; i <= 12; i++) {
            $('#month2').append('<option value="'+i+'">'+i+'</option>');
        }
    }else{
    	for (var i = 1; i <= 12; i++) {
            $('#month2').append('<option value="'+i+'">'+i+'</option>');
        }
    }
    flg=true;
    leapYearCheck2();
}


function setHour2(){
	$('#hour2').empty();
	if($('#month2').val()==myMonth&&$('#year2').val()==myYear&&$('#day2').val()==myDate){
    	for (var i = myHour; i <= 23; i++) {
		    $('#hour2').append('<option value="'+i+'">'+i+'</option>');
		}
    }else{
		for (var i = 0; i <= 23; i++) {
		    $('#hour2').append('<option value="'+i+'">'+i+'</option>');
		}
    }
	setMinute2();
}

function setMinute2(){
	$('#minute2').empty();
	if($('#month2').val()==myMonth&&$('#year2').val()==myYear&&$('#day2').val()==myDate&&$('#hour2').val()==myHour){
    	for (var i = myMinute; i <= 59; i++) {
		    $('#minute2').append('<option value="'+i+'">'+i+'</option>');
		}
    }else{
		for (var i = 0; i <= 59; i++) {
		    $('#minute2').append('<option value="'+i+'">'+i+'</option>');
		}
    }
}

// うるう年判定して日付を表示
function leapYearCheck2(){
	$('#day2').empty();
    var y = $("#year2").val();
    var m = $("#month2").val();
    if (2 == m && (0 == y % 400 || (0 == y % 4 && 0 != y % 100))) {
    	var last = 29;
    }else{
    	var last = new Array(31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31)[m - 1];
    }
    if($('#month2').val()==myMonth&&$('#year2').val()==myYear){
    	for (var i = myDate; i <= last; i++) {
		    $('#day2').append('<option value="'+i+'">'+i+'</option>');
		}
    }else{
		for (var i = 1; i <= last; i++) {
		    $('#day2').append('<option value="'+i+'">'+i+'</option>');
		}
    }
    setHour2();
}
