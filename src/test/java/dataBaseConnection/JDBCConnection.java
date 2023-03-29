package dataBaseConnection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.testng.annotations.Test;

public class JDBCConnection {
	
	@Test
	public void createConn() throws SQLException {
		
		String host = "localhost";
		String port = "3306";
		String database = "Qadbt";
		
		Connection conn = DriverManager.getConnection("jdbc:mysql://" + host + ":" + port + "/" + database,"root","Root123");
		Statement stmt = conn.createStatement();
		ResultSet result = stmt.executeQuery("select * from EmployeeInfo where name='Tam'");
		
		result.next();
		String name = result.getString("name");
		String location = result.getString("location");
		int age = result.getInt("age");
		System.out.println("User " + name + " is residing in " + location + " and his age is " + age);
	}

}
