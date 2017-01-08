package jp.ac.hal;

import java.sql.Date;
import java.sql.Timestamp;

public class Event {
	private int id;
	private int project_id;
	private String name;
	private String descrpt;
	private Timestamp startdate;
	private String stdate;
	private Timestamp limitdate;
	private Date upddate;
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getProject_id() {
		return project_id;
	}
	public void setProject_id(int project_id) {
		this.project_id = project_id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescrpt() {
		return descrpt;
	}
	public void setDescrpt(String descrpt) {
		this.descrpt = descrpt;
	}
	public Timestamp getStartdate() {
		return startdate;
	}
	public void setStartdate(Timestamp startdate) {
		this.startdate = startdate;
	}
	public String getStdate() {
		return stdate;
	}
	public void setStdate(String stdate) {
		this.stdate = stdate;
	}
	public Timestamp getLimitdate() {
		return limitdate;
	}
	public void setLimitdate(Timestamp limitdate) {
		this.limitdate = limitdate;
	}
	public String getLmtdate() {
		return lmtdate;
	}
	public void setLmtdate(String lmtdate) {
		this.lmtdate = lmtdate;
	}
	private String lmtdate;
	public Date getUpddate() {
		return upddate;
	}
	public void setUpddate(Date upddate) {
		this.upddate = upddate;
	}
	
}
