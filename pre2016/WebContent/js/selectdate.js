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

$(function setYear(){
	//今年から10年分表示
		
    for (var i = myYear; i <= myYear+10; i++) {
        if(i === myYear){
            $('#year').append('<option value="'+i+'" selected>'+i+'</option>');
        } else {
            $('#year').append('<option value="'+i+'">'+i+'</option>');
        }
    }
    setMonth();
});

function setMonth(){
	//1月～12月まで表示
	$('#month').empty();
    if($('#year').val()==myYear){
    	for (var i = myMonth; i <= 12; i++) {
            $('#month').append('<option value="'+i+'">'+i+'</option>');
        }
    }else{
    	for (var i = 1; i <= 12; i++) {
            $('#month').append('<option value="'+i+'">'+i+'</option>');
        }
    }
    flg=true;
    leapYearCheck();
}


function setHour(){
	$('#hour').empty();
	if($('#month').val()==myMonth&&$('#year').val()==myYear&&$('#day').val()==myDate){
    	for (var i = myHour; i <= 23; i++) {
		    $('#hour').append('<option value="'+i+'">'+i+'</option>');
		}
    }else{
		for (var i = 0; i <= 23; i++) {
		    $('#hour').append('<option value="'+i+'">'+i+'</option>');
		}
    }
	setMinute();
}

function setMinute(){
	$('#minute').empty();
	if($('#month').val()==myMonth&&$('#year').val()==myYear&&$('#day').val()==myDate&&$('#hour').val()==myHour){
    	for (var i = myMinute; i <= 59; i++) {
		    $('#minute').append('<option value="'+i+'">'+i+'</option>');
		}
    }else{
		for (var i = 0; i <= 59; i++) {
		    $('#minute').append('<option value="'+i+'">'+i+'</option>');
		}
    }
}

// うるう年判定して日付を表示
function leapYearCheck(){
	$('#day').empty();
    var y = $("#year").val();
    var m = $("#month").val();
    if (2 == m && (0 == y % 400 || (0 == y % 4 && 0 != y % 100))) {
    	var last = 29;
    }else{
    	var last = new Array(31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31)[m - 1];
    }
    if($('#month').val()==myMonth&&$('#year').val()==myYear){
    	for (var i = myDate; i <= last; i++) {
		    $('#day').append('<option value="'+i+'">'+i+'</option>');
		}
    }else{
		for (var i = 1; i <= last; i++) {
		    $('#day').append('<option value="'+i+'">'+i+'</option>');
		}
    }
    setHour();
}
