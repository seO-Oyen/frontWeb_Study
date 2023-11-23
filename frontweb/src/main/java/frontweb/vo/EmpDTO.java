package frontweb.vo;

public class EmpDTO {
	private int empno;
	private String ename;
	private String job;
	private int mgr;
	private String hiredateStr;
	private double sal;
	private double comm;
	private int deptno;
	
	public EmpDTO() {
		
	}

	public EmpDTO(int empno, String ename, String job, int mgr, 
			String hiredateStr, double sal, double comm, int deptno) {
		this.empno = empno;
		this.ename = ename;
		this.job = job;
		this.mgr = mgr;
		this.hiredateStr = hiredateStr;
		this.sal = sal;
		this.comm = comm;
		this.deptno = deptno;
	}
	
	public int getEmpno() {
		return empno;
	}
	public void setEmpno(int empno) {
		this.empno = empno;
	}
	public String getEname() {
		return ename;
	}
	public void setEname(String ename) {
		this.ename = ename;
	}
	public String getJob() {
		return job;
	}
	public void setJob(String job) {
		this.job = job;
	}
	public int getMgr() {
		return mgr;
	}
	public void setMgr(int mgr) {
		this.mgr = mgr;
	}
	public String getHiredateStr() {
		return hiredateStr;
	}
	public void setHiredateStr(String hiredateStr) {
		this.hiredateStr = hiredateStr;
	}
	public double getSal() {
		return sal;
	}
	public void setSal(double sal) {
		this.sal = sal;
	}
	public double getComm() {
		return comm;
	}
	public void setComm(double comm) {
		this.comm = comm;
	}
	public int getDeptno() {
		return deptno;
	}
	public void setDeptno(int deptno) {
		this.deptno = deptno;
	}
	
	

}
