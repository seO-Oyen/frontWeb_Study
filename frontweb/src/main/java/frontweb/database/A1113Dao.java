package frontweb.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class A1113Dao {
	private Connection con;
	private Statement stmt;
	private PreparedStatement pstmt;
	private ResultSet rs;

	// 
	public int getEmpCount() {
		int cnt = 0;
		String sql = "SELECT count(empno)\r\n"
				+ "FROM emp";
		
		try {
			con = DBCon.con();
			stmt = con.createStatement();
			rs = stmt.executeQuery(sql);
			// 데이터가 있는지 여부만 가지고 오게
			while (rs.next()) {
				cnt = rs.getInt(1);
			}
			
		} catch (SQLException e) {
			System.out.println("DB 예외 : " + e.getMessage());
		} catch (Exception e) {
			System.out.println("일반 예외 : " + e.getMessage());
		} finally {
			// 자원해제
			DBCon.close(rs, stmt, con);
		}
		
		return cnt;
	}
	
	public static void main(String[] args) {
		
		
	}
}
