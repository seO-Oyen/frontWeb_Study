package frontweb.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import frontweb.Dept;
import frontweb.dao.DB;
import frontweb.vo.EmpShort;
import frontweb.vo.Jobs;
import frontweb.vo.Member;
import frontweb.vo.Reservation;

public class EmpDao {
	
	// 공통으로 사용할 전역 객체 선언
	// 1. 연결
	private Connection con;
	// 2. 대화
	private Statement stmt;
	private PreparedStatement pstmt;
	// 3. 결과
	private ResultSet rs;
	
	// 기능별 메서드 처리
	public EmpShort getEmpShort(int empno) {
		EmpShort se = null;
		String sql = "SELECT empno, ename, job\r\n"
				+ "FROM emp\r\n"
				+ "WHERE empno = " + empno;
		
		return se;
	}
	
	public List<String> getEnames(int start, int end) {
		List<String> enames = new ArrayList<String>();
		String sql = "SELECT ename\r\n"
				+ "FROM emp\r\n"
				+ "WHERE sal BETWEEN " + start + " AND " + end;
		
		return enames;
	}
	
	public List<Double> getSalaries(int deptno) {
		List<Double> sals = new ArrayList<Double>();
		String sql = "SELECT sal\r\n"
				+ "FROM emp\r\n"
				+ "WHERE deptno = " + deptno;
		
		return sals;
	}
	
	public void insertMember(Member ins) {
		String sql = "INSERT INTO member01 \r\n"
				+ "values(" + ins.getMno() 
				+ ", '" + ins.getName() + "', '" + ins.getId() 
				+ "', '" + ins.getPwd() + "', '" + ins.getAuth() 
				+ "', "+ ins.getPoint() +")";
	}

	public List<Member> schMember(String nameSch) {
		List<Member> mlist = new ArrayList<Member>();
		String sql = "";
		return mlist;
	}
	
	public double getSalByEname(String ename) {
		double sal = 0;
		String sql = "SELECT sal\r\n"
				+ "FROM emp\r\n"
				+ "WHERE ename = '" + ename +"'";
		
		return sal;
	}
	
	public List<Reservation> getResListByDate(String date) {
		List<Reservation> rlist = new ArrayList<Reservation>();
		String sql = "SELECT *\r\n"
			+ "FROM RESERVATION\r\n"
			+ "WHERE to_char(resdate, 'YYYY-MM-DD') = '" + date + "'";
				
		return rlist;
	}
	
	public void inserReservation(Reservation ins) {
		String sql = "INSERT INTO reservation VALUES(res_seq.nextval,\r\n"
				+ "	to_date('" + ins.getResdate() + "', 'YYYY-MM-DD'),\r\n"
				+ "	'" + ins.getStarttime() + "', '" + ins.getEndtime() 
				+ "', " + ins.getStarttime() + ", " + ins.getEndtime() + ")";
	}
	
	public double getMaxSal(int deptno) {
		double max = 0;
		
		String sql = "SELECT max(sal)\r\n"
				+ "FROM emp\r\n"
				+ "WHERE deptno = " + deptno;
		
		try {
			con = DB.con();
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				max = rs.getDouble(1);
			}
		} catch (SQLException e) {
		}
		
		return max;
	}
	
	/*
		회원 로그인 성공 여부 확인 기능 메서드
		select * from member01
		where id = 'himan' and pwd = '777'
	 * */
	public boolean loginSucc(String id, String pwd) {
		boolean isLogSucc = false;
		String sql = "select * from member01\r\n"
				+ "where id = '" + id + "' and pwd = '" + pwd + "'";
		
		try {
			// 1. 연결 => 필드에 선언된 con = DBConn에 할당
			con = DBCon.con();
			// 2. 대화처리
			stmt = con.createStatement();
			// 3. 결과
			rs = stmt.executeQuery(sql);
			// 데이터가 있는지 여부만 가지고 오게
			isLogSucc = rs.next();
			// 4. 예외와 자원해제
			
		} catch (SQLException e) {
			System.out.println("DB 예외 : " + e.getMessage());
		} catch (Exception e) {
			System.out.println("일반 예외 : " + e.getMessage());
		} finally {
			// 자원해제
			DBCon.close(rs, stmt, con);
		}
		
		return isLogSucc;
	}
	
	public EmpShort getEmpshort(int empno) {
		EmpShort es = null;
		String sql = "select empno, ename, job from emp\r\n"
				+ "where empno = " + empno;
		
		try {
			// 1. 연결 => 필드에 선언된 con = DBConn에 할당
			con = DBCon.con();
			// 2. 대화처리
			stmt = con.createStatement();
			// 3. 결과
			rs = stmt.executeQuery(sql);
			// 데이터가 있는지 여부만 가지고 오게
			if (rs.next()) {
				es = new EmpShort(rs.getInt("empno"),
							rs.getString("ename"), rs.getString("job"));
			}
			// 4. 예외와 자원해제
			
		} catch (SQLException e) {
			System.out.println("DB 예외 : " + e.getMessage());
		} catch (Exception e) {
			System.out.println("일반 예외 : " + e.getMessage());
		} finally {
			// 자원해제
			DBCon.close(rs, stmt, con);
		}
		
		return es;
	}
	
	public Dept getDept(int deptno) {
		Dept dept = null;
		String sql = "SELECT *\r\n"
				+ "FROM dept\r\n"
				+ "WHERE deptno = " + deptno;
		
		try {
			con = DBCon.con();
			stmt = con.createStatement();
			rs = stmt.executeQuery(sql);
			if (rs.next()) {
				dept = new Dept(rs.getInt("deptno"), rs.getString("dname"),
						rs.getString("loc"));
			}
			
		} catch (SQLException e) {
			System.out.println("DB 예외 : " + e.getMessage());
		} catch (Exception e) {
			System.out.println("일반 예외 : " + e.getMessage());
		} finally {
			// 자원해제
			DBCon.close(rs, stmt, con);
		}
		
		return dept;
	}
	
	public Member getMember(String id) {
		Member member = null;
		String sql = "SELECT *\r\n"
				+ "FROM member01\r\n"
				+ "WHERE id = '" + id + "'";
		
		try {
			con = DBCon.con();
			stmt = con.createStatement();
			rs = stmt.executeQuery(sql);
			if (rs.next()) {
				member = new Member(rs.getInt("mno"),
						rs.getString("name"),
						rs.getString("id"),
						rs.getString("pwd"),
						rs.getString("auth"),
						rs.getInt("point"));
			}
			
		} catch (SQLException e) {
			System.out.println("DB 예외 : " + e.getMessage());
		} catch (Exception e) {
			System.out.println("일반 예외 : " + e.getMessage());
		} finally {
			// 자원해제
			DBCon.close(rs, stmt, con);
		}
		
		return member;
	}
	
	public Jobs getJobs(String jobId) {
		Jobs job = null;
		String sql = "SELECT *\r\n"
				+ "FROM jobs\r\n"
				+ "WHERE job_id = '" + jobId + "'";
		
		try {
			con = DBCon.con();
			stmt = con.createStatement();
			rs = stmt.executeQuery(sql);
			if (rs.next()) {
				job = new Jobs(rs.getString("job_id"),
						rs.getString("job_title"),
						rs.getInt("min_salary"),
						rs.getInt("max_salary"));
			}
			
		} catch (SQLException e) {
			System.out.println("DB 예외 : " + e.getMessage());
		} catch (Exception e) {
			System.out.println("일반 예외 : " + e.getMessage());
		} finally {
			// 자원해제
			DBCon.close(rs, stmt, con);
		}
		
		return job;
	}
	
	public List<String> getEnames(int deptno) {
		List<String> enames = new ArrayList<String>();
		String sql = "SELECT ename\r\n"
				+ "FROM emp\r\n"
				+ "WHERE deptno = " + deptno;
		
		try {
			con = DBCon.con();
			stmt = con.createStatement();
			rs = stmt.executeQuery(sql);
			while (rs.next()) {
				enames.add(rs.getString("ename"));
			}
			
		} catch (SQLException e) {
			System.out.println("DB 예외 : " + e.getMessage());
		} catch (Exception e) {
			System.out.println("일반 예외 : " + e.getMessage());
		} finally {
			// 자원해제
			DBCon.close(rs, stmt, con);
		}
		
		return enames;
	}
	
	public List<Double> getsals(int deptno) {
		List<Double> sals = new ArrayList<Double>();
		String sql = "SELECT sal\r\n"
				+ "FROM emp\r\n"
				+ "WHERE deptno = " + deptno;
		
		try {
			con = DBCon.con();
			stmt = con.createStatement();
			rs = stmt.executeQuery(sql);
			while (rs.next()) {
				sals.add(rs.getDouble("sal"));
			}
			
		} catch (SQLException e) {
			System.out.println("DB 예외 : " + e.getMessage());
		} catch (Exception e) {
			System.out.println("일반 예외 : " + e.getMessage());
		} finally {
			// 자원해제
			DBCon.close(rs, stmt, con);
		}
		
		return sals;
	}
	
	public List<String> getEmails(int managerId) {
		List<String> emails = new ArrayList<String>();
		String sql = "SELECT email\r\n"
				+ "FROM employees\r\n"
				+ "WHERE manager_id = " + managerId;
		
		try {
			con = DBCon.con();
			stmt = con.createStatement();
			rs = stmt.executeQuery(sql);
			while (rs.next()) {
				emails.add(rs.getString("email"));
			}
			
		} catch (SQLException e) {
			System.out.println("DB 예외 : " + e.getMessage());
		} catch (Exception e) {
			System.out.println("일반 예외 : " + e.getMessage());
		} finally {
			// 자원해제
			DBCon.close(rs, stmt, con);
		}
		
		return emails;
	}
	
	public static void main(String[] args) {
		EmpDao dao = new EmpDao();
//		System.out.println("사원정보의 수 : " + dao.getEmpCount());
		/*System.out.println("성공여부 1 : " + dao.loginSucc("himan", "7777"));
		System.out.println("성공여부 2 : " + dao.loginSucc("himan", "8888"));
		System.out.println("성공여부 3 : " + dao.loginSucc("goodman", "7777"));*/
		/*EmpShort es = dao.getEmpshort(749);
		if (es != null) {
			System.out.println("데이터 있음");
			System.out.println(es.getEmpno());
			System.out.println(es.getEname());
			System.out.println(es.getJob());
		} else {
			System.out.println("데이터 없음");
		}*/
		/*Dept dept = dao.getDept(10);
		if (dept != null) {
			System.out.println("데이터 있음");
			System.out.println(dept.getDeptno());
			System.out.println(dept.getDname());
			System.out.println(dept.getLoc());
		} else {
			System.out.println("없음");
		}*/
		
		/*Member member = dao.getMember("himan");
		if(member != null) {
			System.out.println(member.getMno());
			System.out.println(member.getName());
		}*/
		/*Jobs job = dao.getJobs("AD_VP");
		if(job != null) {
			System.out.println(job.getJobId());
			System.out.println(job.getJobTitle());
			System.out.println(job.getMinSalary());
			System.out.println(job.getMaxSalary());
		}*/
		
		/*List<String> enames = dao.getEnames(10);
		
		for (String ename : enames) {
			System.out.println(ename);
		}*/
		
		/*for (double sal : dao.getsals(10)) {
			System.out.println(sal);
		}*/
		
		for (String email : dao.getEmails(101)) {
			System.out.println(email);
		}
		
		//System.out.println(dao.getMaxSal(20));
	}
	
	

}
