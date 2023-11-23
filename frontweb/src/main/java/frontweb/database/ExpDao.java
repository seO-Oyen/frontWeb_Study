package frontweb.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import frontweb.Dept;
import frontweb.Emp;
import frontweb.dao.DB;
import frontweb.vo.Employees;

public class ExpDao {
	
	private Connection con;
	private Statement stmt;
	private PreparedStatement pstmt;
	private ResultSet rs;
	
	public List<String> getStreets(String country) {
		List<String> streets = new ArrayList<String>();
		String sql = "SELECT street_address\r\n"
				+ "FROM locations\r\n" 
				+ "WHERE country_id = '" + country + "'";
		
		try {
			con = DBCon.con();
			stmt = con.createStatement();
			rs = stmt.executeQuery(sql);
			// 데이터가 있는지 여부만 가지고 오게
			while (rs.next()) {
				streets.add(rs.getString("streets_address"));
			}
			
		} catch (SQLException e) {
			System.out.println("DB 예외 : " + e.getMessage());
		} catch (Exception e) {
			System.out.println("일반 예외 : " + e.getMessage());
		} finally {
			// 자원해제
			DBCon.close(rs, stmt, con);
		}
		
		return streets;
	}
	
	public List<Emp> getEmpList(String sch) {
		List<Emp> elist = new ArrayList<>();
		String sql = "SELECT * \r\n"
				+ "FROM emp \r\n"
				+ "WHERE ename LIKE '%" + sch + "%'";

		try {
			con = DB.con();
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				elist.add(new Emp(rs.getInt("empno"),
						rs.getString("ename"),
						rs.getString("job"), 
						rs.getInt("mgr"),
						rs.getDate("hiredate"),
						rs.getDouble("sal"),
						rs.getDouble("comm"),
						rs.getInt("deptno")));
			}
			System.out.println("조회된 사원 정보 갯수 : " + elist.size());
		} catch (SQLException e) {
			System.out.println("DB 관련 오류: " + e.getMessage());
		} catch (Exception e) {
			System.out.println("일반 오류: " + e.getMessage());
		} finally {
			DB.close(rs, pstmt, con);
		}
		return elist;
	}

	public List<Dept> getDeptList(String sch) {
		List<Dept> dlist = new ArrayList<>();
		String sql = "SELECT * \r\n"
				+ "FROM dept \r\n"
				+ "WHERE dname LIKE '%" + sch + "%'";

		try {
			con = DB.con();
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				dlist.add(new Dept(rs.getInt(1),
						rs.getString(2),
						rs.getString(3)));
			}
			System.out.println("조회된 부서 정보 갯수 : " + dlist.size());
		} catch (SQLException e) {
			System.out.println("DB 관련 오류: " + e.getMessage());
		} catch (Exception e) {
			System.out.println("일반 오류: " + e.getMessage());
		} finally {
			DB.close(rs, pstmt, con);
		}
		return dlist;
	}
	
	public List<Employees> getEmployees(int min, int max) {
		List<Employees> employees = new ArrayList<Employees>();
		String sql = "SELECT employee_id, first_name, email, salary\r\n"
				+ "FROM EMPLOYEES\r\n"
				+ "WHERE SALARY BETWEEN " + min + " AND " + max;
		
		try {
			con = DB.con();
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				employees.add(new Employees(rs.getInt(1),
						rs.getString(2),
						rs.getString(3),
						rs.getInt(4)));
			}
			System.out.println("조회된 사원 정보 갯수 : " + employees.size());
		} catch (SQLException e) {
			System.out.println("DB 관련 오류: " + e.getMessage());
		} catch (Exception e) {
			System.out.println("일반 오류: " + e.getMessage());
		} finally {
			DB.close(rs, pstmt, con);
		}
		
		return employees;
	}
	
	public static void main(String[] args) {
		ExpDao dao = new ExpDao();
		/*for (String street : dao.getStreets("US")) {
			System.out.println(street);
		}*/
		
		/*for (Emp emp : dao.getEmpList("A")) {
			System.out.println("========================");
			System.out.println(emp.getEmpno());
			System.out.println(emp.getEname());
			System.out.println(emp.getJob());
			System.out.println(emp.getMgr());
			System.out.println(emp.getHireDate());
			System.out.println(emp.getSal());
			System.out.println(emp.getComm());
			System.out.println(emp.getDeptno());
		}*/
		
		for (Dept dept : dao.getDeptList("A")) {
			System.out.println("========================");
			System.out.println(dept.getDeptno());
			System.out.println(dept.getDname());
			System.out.println(dept.getLoc());
		}
	}
}
