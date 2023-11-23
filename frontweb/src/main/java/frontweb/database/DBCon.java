package frontweb.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBCon {
   // 데이터베이스 서버 연동 처리..
   // 1. 연동 기능 메서드..
   //     1) 기본 접속정보
   private static final String 
      URL = "jdbc:oracle:thin:@localhost:1521:xe";
   //         드라이버정보:@ip:port:DB명
   private static final String USERNAME = "scott";
   private static final String PASSWORD = "tiger";
   public static Connection con() throws SQLException, ClassNotFoundException {
        Class.forName("oracle.jdbc.driver.OracleDriver");
      return DriverManager.getConnection(URL,USERNAME,PASSWORD);
   }
   // 2. DB 처리한 후, 자원을 해제하는 기능 공통 기능 메서드
   //    AutoCloseable... resources : 여러개의 객체를 매개변수로
   //    받아서 처리할 때, 사용하는 형태로 배열로 받는다.
   public static void close(AutoCloseable... resources) {
      for(AutoCloseable resource:resources) {
         if(resource!=null) {
            try {
               resource.close();
            } catch (Exception e) {
               // ㅣ해당 클래스의 예외가 나올 때 처리
               System.out.print(
                     resource.getClass().getSimpleName());
               System.out.println("닫기 실패");
            }
         }
      }
      System.out.println("자원해제처리!");
      
   }

   public static void main(String[] args) {
      // TODO Auto-generated method stub
      /*
      try {
         close( con() );
         System.out.println("접속성공!");
      } catch (SQLException e) {
         // TODO Auto-generated catch block
         System.out.println("에러발생:"+e.getMessage());
      }
      */
   }

}