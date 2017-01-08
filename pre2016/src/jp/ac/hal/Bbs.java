package jp.ac.hal;

import java.util.Date;

public class Bbs {
	private int id;
	private int project_id;
	private String title;
	private Date opendate;
	private Date closedate;
	private int lastnum;
	private String upddate;
	private String descrpt;
	
	
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
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public Date getOpendate() {
		return opendate;
	}
	public void setOpendate(Date opendate) {
		this.opendate = opendate;
	}
	public Date getClosedate() {
		return closedate;
	}
	public void setClosedate(Date closedate) {
		this.closedate = closedate;
	}
	public int getLastnum() {
		return lastnum;
	}
	public void setLastnum(int lastnum) {
		this.lastnum = lastnum;
	}
	public String getUpddate() {
		return upddate;
	}
	public void setUpddate(String upddate) {
		this.upddate = upddate;
	}
	public String getDescrpt() {
		return descrpt;
	}
	public void setDescrpt(String descrpt) {
		this.descrpt = descrpt;
	}
	
	
	
}
