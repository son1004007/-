package config;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBconfig {
	public static Connection con;
	public static Connection get() {
		Connection conn=null;
		try {
			String id="sys as sysdba";
			String pw="oracle";
			String url = "jdbc:oracle:thin:@localhost:1521/xe";
			// JDBC 드라이버를 로딩하는 코드
			Class.forName("oracle.jdbc.driver.OracleDriver");
			//드라이버들이 읽히면 자동 객체 생성되면서 DriverManager에 등록됨
			conn=DriverManager.getConnection(url,id,pw);
			//DriverManager : Connection 객체를 연결하는 것
			System.out.println("데이터베이스에 연결됐다.");
		}catch(Exception e){ 
			e.printStackTrace();
			System.out.println("로딩 실패");
		}
		return conn;
	}
}
