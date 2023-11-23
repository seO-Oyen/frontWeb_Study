package frontweb.vo;

public class EmpHire {
	private int empno;
	private String ename;
	private String job;
	private String hire_qua;
	private String hire_str;
	
	public EmpHire() {
	}
	
	public EmpHire(int empno, String ename, String job, String hire_qua, String hire_str) {
		super();
		this.empno = empno;
		this.ename = ename;
		this.job = job;
		this.hire_qua = hire_qua;
		this.hire_str = hire_str;
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
	public String getHire_qua() {
		return hire_qua;
	}
	public void setHire_qua(String hire_qua) {
		this.hire_qua = hire_qua;
	}
	public String getHire_str() {
		return hire_str;
	}
	public void setHire_str(String hire_str) {
		this.hire_str = hire_str;
	}
	
	

}
