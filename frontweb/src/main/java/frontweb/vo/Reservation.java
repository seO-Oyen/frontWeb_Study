package frontweb.vo;

import java.util.Date;

public class Reservation {
	private int rno;
	private Date resdate;
	private String startloc;
	private String endloc;
	private int starttime;
	private int endtime;
	
	public Reservation() {
		
	}
	
	public Reservation(int rno, Date resdate, String startloc, String endloc, int starttime, int endtime) {
		super();
		this.rno = rno;
		this.resdate = resdate;
		this.startloc = startloc;
		this.endloc = endloc;
		this.starttime = starttime;
		this.endtime = endtime;
	}
	
	public int getRno() {
		return rno;
	}
	public void setRno(int rno) {
		this.rno = rno;
	}
	public Date getResdate() {
		return resdate;
	}
	public void setResdate(Date resdate) {
		this.resdate = resdate;
	}
	public String getStartloc() {
		return startloc;
	}
	public void setStartloc(String startloc) {
		this.startloc = startloc;
	}
	public String getEndloc() {
		return endloc;
	}
	public void setEndloc(String endloc) {
		this.endloc = endloc;
	}
	public int getStarttime() {
		return starttime;
	}
	public void setStarttime(int starttime) {
		this.starttime = starttime;
	}
	public int getEndtime() {
		return endtime;
	}
	public void setEndtime(int endtime) {
		this.endtime = endtime;
	}
	
}
