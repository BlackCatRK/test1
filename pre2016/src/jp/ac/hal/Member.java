package jp.ac.hal;

import java.sql.Date;

public class Member {
	private String name;
	private String hn;
	private String mail;
	private Date indate;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getHn() {
		return hn;
	}
	public void setHn(String hn) {
		this.hn = hn;
	}
	public String getMail() {
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
}
