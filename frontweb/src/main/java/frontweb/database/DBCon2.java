package frontweb.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBCon2 {
	
	private static final String URL = "jdbc:oracle:thin:@localhost:1521:xe";
	private static final String USERNAME = "scott";
	private static final String PASSWORD = "tiger";
	
	public static Connection con() throws SQLException {
		return DriverManager.getConnection(URL, USERNAME, PASSWORD);
	}
	
	public static void close(AutoCloseable... resources) {
		for (AutoCloseable resource : resources) {
			if (resource != null) {
				try {
					resource.close();
					System.out.println("닫기 성공");
				} catch (Exception e) {
					System.out.println("닫기 실패");
				}
			}
		}
	}

	public static void main(String[] args) {
		try {
			Connection con = con();
			System.out.println("접속성공");
			close(con);
		} catch (SQLException e) {
			System.out.println("에러발생 " + e.getMessage());
		}
		
	}

}
