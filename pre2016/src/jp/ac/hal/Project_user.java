package jp.ac.hal;

import java.util.Date;

public class Project_user {
	private int project_id;
	private int user_id;
	private boolean nameflg;
	private boolean adrsflg;
	private boolean telflg;
	private String hn;
	private String mail;
	private Date indate;
	private Date outdate;
	private int type;
	
	
	public int getProject_id() {
		return project_id;
	}
	public void setProject_id(int project_id) {
		this.project_id = project_id;
	}
	public int getUser_id() {
		return user_id;
	}
	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}
	public boolean isNameflg() {
		return nameflg;
	}
	public void setNameflg(boolean nameflg) {
		this.nameflg = nameflg;
	}
	public boolean isAdrsflg() {
		return adrsflg;
	}
	public void setAdrsflg(boolean adrsflg) {
		this.adrsflg = adrsflg;
	}
	public boolean isTelflg() {
		return telflg;
	}
	public void setTelflg(boolean telflg) {
		this.telflg = telflg;
	}
	public String getHn() {
		return hn;
	}
	public void setHn(String hn) {
		this.hn = hn;
	}
	public String isMail() {
		return mail;
	}
	public void setMail(String mail) {
		this.mail = mail;
	}
	public Date getIndate() {
		return indate;
	}
	public void setIndate(Date indate) {
		this.indate = indate;
	}
	public Date getOutdate() {
		return outdate;
	}
	public void setOutdate(Date outdate) {
		this.outdate = outdate;
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	
	
	

}
