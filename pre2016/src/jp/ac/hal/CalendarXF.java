package jp.ac.hal;

import java.util.ArrayList;

public class CalendarXF {
	private int year;
	private int month;
	private int day;
	private String sqldate;
	private String csscls;
	private ArrayList<Hoge> data;
	
	
	public ArrayList<Hoge> getData() {
		return data;
	}
	public void setData(ArrayList<Hoge> data) {
		this.data = data;
	}
	public String getCsscls() {
		return csscls;
	}
	public int getYear() {
		return year;
	}
	public void setYear(int year) {
		this.year = year;
	}
	public int getMonth() {
		return month;
	}
	public void setCsscls(String csscls) {
		this.csscls = csscls;
	}
	public void setMonth(int month) {
		this.month = month;
	}
	public int getDay() {
		return day;
	}
	public void setDay(int day) {
		this.day = day;
	}
	public String getSqldate() {
		return sqldate;
	}
	public void setSqldate(String sqldate) {
		this.sqldate = sqldate;
	}
	
	

}
