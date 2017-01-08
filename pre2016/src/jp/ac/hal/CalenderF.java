package jp.ac.hal;

import java.util.ArrayList;
import java.util.Calendar;

public class CalenderF {
	public ArrayList<CalendarXF> main(int year, int month, int day, int pid){
	    Calendar calendar = Calendar.getInstance();

	    System.out.print("本日の日時は");
	    System.out.println(year + "年" + (month + 1) + "月" + day + "日");

	    /* 今月が何曜日から開始されているか確認する */
	    calendar.set(year, month, 1);
	    int startWeek = calendar.get(Calendar.DAY_OF_WEEK);
	    System.out.println("今月の曜日は" + startWeek + "から");

	    /* 先月が何日までだったかを確認する */
	    calendar.set(year, month, 0);
	    int beforeMonthlastDay = calendar.get(Calendar.DATE);
	    System.out.println("先月は" + beforeMonthlastDay + "日まで");

	    /* 今月が何日までかを確認する */
	    calendar.set(year, month + 1, 0);
	    int thisMonthlastDay = calendar.get(Calendar.DATE);
	    System.out.println("今月は" + thisMonthlastDay + "日まで");

	    ArrayList<CalendarXF> calendarDay = new ArrayList<CalendarXF>();
	    int flg=43;
	    
	    CalendarXF p = new CalendarXF();
	    p.setYear(0);
	    p.setMonth(0);
	    p.setDay(0);
	    p.setSqldate("0000-00-00");
	    
	    ArrayList<Hoge> al=new ArrayList<Hoge>();
	    Hoge data = new Hoge();
	    data.setData1(0);
	    data.setData2("empty");
	    al.add(data);
	    p.setData(al);
	    
	    calendarDay.add(p);
	    flg=flg-1;

	    int y=0,m=0;
	    /* 先月分の日付を格納する */
	    for (int i = startWeek - 2 ; i >= 0 ; i--){
	    	CalendarXF q = new CalendarXF();
	    	q.setCsscls("noncrr");
	    	if(month-1==-1){
		    	q.setYear(year-1);
			    q.setMonth(11);
			    y=year-1;
			    m=11;
		    }else{
		    	q.setYear(year);
			    q.setMonth(month+1);
			    y=year;
			    m=month-1;
		    }
	    	q.setDay(beforeMonthlastDay - i);
		    /*q.setSqldate(String.valueOf(y)+"-"+String.valueOf(m+1)+"-"+(beforeMonthlastDay-i));*/
	    	String mm=changeformatM(m);
	    	String dd=changeformatD(beforeMonthlastDay-i);
	    	String sqldate=String.valueOf(y)+"-"+mm+"-"+dd;
	    	q.setSqldate(sqldate);
		    
	    	
	    	al=new ArrayList<Hoge>();
		    EventDAO dao=new EventDAO();
		    al=dao.listH(sqldate, pid);
		    q.setData(al);
		    
		    calendarDay.add(q);
		    flg=flg-1;
		    System.out.println(q.getCsscls());
	    }

	    /* 今月分の日付を格納する */
	    for (int i = 1 ; i <= thisMonthlastDay ; i++){
	    	CalendarXF q = new CalendarXF();
	    	q.setCsscls("crr");
		    q.setYear(year);
		    q.setMonth(month);
		    q.setDay(i);
		    
		    String mm=changeformatM(month);
	    	String dd=changeformatD(i);
		    String sqldate=String.valueOf(year)+"-"+mm+"-"+dd;
		    q.setSqldate(sqldate);
		    
		    
		    al=new ArrayList<Hoge>();
		    EventDAO dao=new EventDAO();
		    al=dao.listH(sqldate, pid);
		    q.setData(al);
		    
		    calendarDay.add(q);
		    flg=flg-1;
	    }

	    /* 翌月分の日付を格納する */
	    System.out.println("month is "+month);
	    y=0;
	    m=0;
	    int d=1;
	    while (flg != 0){
	    	CalendarXF q = new CalendarXF();
	    	q.setCsscls("noncrr");
	    	if(month==11){
		    	q.setYear(year+1);
			    q.setMonth(0);
			    y=year+1;
			    m=0;
		    }else{
		    	q.setYear(year);
			    q.setMonth(month+1);
			    y=year;
			    m=month+1;
		    }
		    q.setDay(d);
		    String mm=changeformatM(m);
	    	String dd=changeformatD(d);
	    	String sqldate=String.valueOf(y)+"-"+mm+"-"+dd;
		    q.setSqldate(sqldate);
		    /*q.setSqldate(String.valueOf(y)+"-"+String.valueOf(m+1)+"-"+d);*/
		    
		    
		    al=new ArrayList<Hoge>();
		    EventDAO dao=new EventDAO();
		    al=dao.listH(sqldate, pid);
		    q.setData(al);
		    
		    calendarDay.add(q);
		    flg=flg-1;
		    d=d+1;
	    }
	    System.out.println(calendarDay.size());
	    
	    for(int i=0; i<calendarDay.size(); i++){
	    	CalendarXF s=calendarDay.get(i);
	    	int ss=s.getDay();
	    	System.out.println(ss);
	    }
	    
	    return calendarDay;
	}
	
	public String changeformatM(int m){
		String mm;
		
	    if(m<8){
	    	mm="0"+String.valueOf(m+1);
	    }else{
	    	mm=String.valueOf(m+1);
	    }
		
		return mm;
	}
	public String changeformatD(int i){
		String dd;
	    if(i<10){
	    	dd="0"+String.valueOf(i);
	    }else{
	    	dd=String.valueOf(i);
	    }
	    return dd;
	}
}
