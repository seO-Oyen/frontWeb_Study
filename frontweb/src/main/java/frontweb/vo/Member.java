package frontweb.vo;

public class Member {
	private int mno;
	private String name;
	private String id;
	private String pwd;
	private String auth;
	private int point;
	
	public Member() {
		
	}
	
	public Member(int mno, String name, String id, String pwd, String auth, int point) {
		super();
		this.mno = mno;
		this.name = name;
		this.id = id;
		this.pwd = pwd;
		this.auth = auth;
		this.point = point;
	}
	
	public int getMno() {
		return mno;
	}
	public void setMno(int mno) {
		this.mno = mno;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPwd() {
		return pwd;
	}
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
	public String getAuth() {
		return auth;
	}
	public void setAuth(String auth) {
		this.auth = auth;
	}
	public int getPoint() {
		return point;
	}
	public void setPoint(int point) {
		this.point = point;
	}

}
