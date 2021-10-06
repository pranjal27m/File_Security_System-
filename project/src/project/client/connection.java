package project.client;

import java.sql.Connection;
import java.sql.DriverManager;

public class connection {
	private static Connection con;

	static Connection connect(){
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl","system","rutuja");
			System.out.println("connection established");
		
		}catch(Exception ex) {
			
			ex.printStackTrace();
		
		}
		return con;
	}

}
