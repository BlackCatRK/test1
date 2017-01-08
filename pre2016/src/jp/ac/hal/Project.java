package jp.ac.hal;

import java.util.Date;

public class Project {
	private int id;
	private String name;
	private Date startdate;
	private Date closedate;
	private boolean eventflg;
	private boolean bbsflg;
	private boolean joinflg;
	private String descrpt;
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Date getStartdate() {
		return startdate;
	}
	public void setStartdate(Date startdate) {
		this.startdate = startdate;
	}
	public Date getClosedate() {
		return closedate;
	}
	public void setClosedate(Date closedate) {
		this.closedate = closedate;
	}
	public boolean isEventflg() {
		return eventflg;
	}
	public void setEventflg(boolean eventflg) {
		this.eventflg = eventflg;
	}
	public boolean isBbsflg() {
		return bbsflg;
	}
	public void setBbsflg(boolean bbsflg) {
		this.bbsflg = bbsflg;
	}
	public boolean isJoinflg() {
		return joinflg;
	}
	public void setJoinflg(boolean joinflg) {
		this.joinflg = joinflg;
	}
	public String getDescrpt() {
		return descrpt;
	}
	public void setDescrpt(String descrpt) {
		this.descrpt = descrpt;
	}
	
}
