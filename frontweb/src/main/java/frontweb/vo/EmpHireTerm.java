package frontweb.vo;

public class EmpHireTerm {
	private int empno;
	private String ename;
	private String job;
	private String hireStr;
	private int deptno;
	
	public EmpHireTerm() {
		
	}
	
	public EmpHireTerm(int empno, String ename, String job, String hireStr, int deptno) {
		super();
		this.empno = empno;
		this.ename = ename;
		this.job = job;
		this.hireStr = hireStr;
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
	public String getHireStr() {
		return hireStr;
	}
	public void setHireStr(String hireStr) {
		this.hireStr = hireStr;
	}
	public int getDeptno() {
		return deptno;
	}
	public void setDeptno(int deptno) {
		this.deptno = deptno;
	}
	
	

}
