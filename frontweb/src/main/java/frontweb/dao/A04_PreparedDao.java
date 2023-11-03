package frontweb.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import frontweb.Emp;

// ctrl+shift+o
// backendWeb.z01_vo.Emp
// backendWeb.a01_dao.A04_PreparedDao
public class A04_PreparedDao {

	private Connection con;
	private PreparedStatement pstmt;
	private ResultSet rs;

	public List<Emp> getEmpList() {
		List<Emp> elist = new ArrayList<>();
		String sql = "SELECT * FROM emp order by empno";

		try {
			con = DB.con();
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				elist.add(new Emp(rs.getInt("empno"), rs.getString("ename"), rs.getString("job"), rs.getInt("mgr"),
						rs.getDate("hiredate"), rs.getDouble("sal"), rs.getDouble("comm"), rs.getInt("deptno")));
			}
		} catch (SQLException e) {
			System.out.println("DB 관련 오류: " + e.getMessage());
		} catch (Exception e) {
			System.out.println("일반 오류: " + e.getMessage());
		} finally {
			DB.close(rs, pstmt, con);
		}
		return elist;
	}
	/*
	 * SELECT * FROM emp
	 * WHERE ename LIKE '%B%';
	 * */

	public List<Emp> getEmpList(String ename) {
		List<Emp> elist = new ArrayList<>();
		String sql = "SELECT * FROM emp\n"
				+ "WHERE ename LIKE '%" + ename + "%'";
	
	
		try {
			con = DB.con();
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
	
			while (rs.next()) {
				elist.add(new Emp(rs.getInt("empno"), rs.getString("ename"), rs.getString("job"), rs.getInt("mgr"),
						rs.getDate("hiredate"), rs.getDouble("sal"), rs.getDouble("comm"), rs.getInt("deptno")));
			}
		} catch (SQLException e) {
			System.out.println("DB 관련 오류: " + e.getMessage());
		} catch (Exception e) {
			System.out.println("일반 오류: " + e.getMessage());
		} finally {
			DB.close(rs, pstmt, con);
		}
		return elist;
	}

	public static void main(String[] args) {
		A04_PreparedDao dao = new A04_PreparedDao();
		System.out.println(dao.getEmpList().size());

		for (Emp emp : dao.getEmpList("A")) {
			System.out.print(emp.getEmpno() + "\t");
			System.out.print(emp.getEname() + "\t");
			System.out.print(emp.getJob() + "\t");
			System.out.println(emp.getSal());
		}

	}

}