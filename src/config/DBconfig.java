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
			// JDBC ����̹��� �ε��ϴ� �ڵ�
			Class.forName("oracle.jdbc.driver.OracleDriver");
			//����̹����� ������ �ڵ� ��ü �����Ǹ鼭 DriverManager�� ��ϵ�
			conn=DriverManager.getConnection(url,id,pw);
			//DriverManager : Connection ��ü�� �����ϴ� ��
			System.out.println("�����ͺ��̽��� ����ƴ�.");
		}catch(Exception e){ 
			e.printStackTrace();
			System.out.println("�ε� ����");
		}
		return conn;
	}
}
