package sdetessentials;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class RetreiveDataFrom_Jdbc {

	public static void main(String[] args) throws SQLException {

		Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/classicModels", "root", "kohli@18");
		Statement stmt = con.createStatement();
		String s = "select * from customerInfo limit 1";
		ResultSet rs = stmt.executeQuery(s);
		while (rs.next()) {
			String bookname = rs.getString("BookName");
			String purchaseddate = rs.getString("purchasedDate");
			int amount = rs.getInt("Amount");
			String location = rs.getString("Location");

			System.out.println(bookname + " " + purchaseddate + " " + amount + " " + location);
		}
		con.close();
	}

}
