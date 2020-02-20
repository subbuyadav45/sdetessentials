package sdetessentials;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class ConvertDBResult_JavaObject {

	public static void main(String[] args) throws SQLException, JsonGenerationException, JsonMappingException, IOException {

		Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/classicModels", "root", "kohli@18");
		Statement stmt = con.createStatement();
		String s = "select * from customerInfo limit 1";
		ResultSet rs = stmt.executeQuery(s);
		
		CustomerDetails cd=new CustomerDetails();
		
		while (rs.next()) {
			String bookname = rs.getString("BookName");
			String purchaseddate = rs.getString("purchasedDate");
			int amount = rs.getInt("Amount");
			String location = rs.getString("Location");
          cd.setBookName(bookname);
          cd.setPurchasedDate(purchaseddate);
          cd.setAmount(amount);
          cd.setLocation(location);
		}
		//System.out.println(cd.getBookName());
		//System.out.println(cd.getAmount());
		
		//using jackson api,convert java object into json file
	
		
		File jsonfile=new File("C:\\Users\\MANOJ\\eclipse-workspace\\sdetessentials\\custinfo.json");
		ObjectMapper om=new ObjectMapper();
		om.writeValue(jsonfile, cd);
		
		con.close();
		System.out.println("Done!!!");
	}

}
