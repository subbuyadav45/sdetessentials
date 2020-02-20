package sdetessentials;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class ConvertDBResults_JavaObjects_jsonfiles {

	public static void main(String[] args) throws SQLException, JsonGenerationException, JsonMappingException, IOException {

		Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/classicModels", "root", "kohli@18");
		Statement stmt = con.createStatement();
		String s = "select * from customerInfo";
		ResultSet rs = stmt.executeQuery(s);
		
		
		ArrayList<CustomerDetails> ar=new ArrayList<CustomerDetails>();
		
		while (rs.next()) {
			String bookname = rs.getString("BookName");
			String purchaseddate = rs.getString("purchasedDate");
			int amount = rs.getInt("Amount");
			String location = rs.getString("Location");
			
		 CustomerDetails cd=new CustomerDetails();
			
          cd.setBookName(bookname);
          cd.setPurchasedDate(purchaseddate);
          cd.setAmount(amount);
          cd.setLocation(location);
          
          ar.add(cd);
		}
		//System.out.println(cd.getBookName());
		//System.out.println(cd.getAmount());
		
		//using jackson api,convert java object into json file
	
		for(int i=0;i<ar.size();i++) {
		    File jsonfile=new File("C:\\Users\\MANOJ\\eclipse-workspace\\sdetessentials\\custinfo"+i+".json");
		    ObjectMapper om=new ObjectMapper();
		    om.writeValue(jsonfile, ar.get(i));
		}
		con.close();
		System.out.println("Done!!!");
	}

}
