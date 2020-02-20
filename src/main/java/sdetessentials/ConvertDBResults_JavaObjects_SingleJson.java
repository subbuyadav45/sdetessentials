package sdetessentials;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import org.json.simple.JSONObject;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.google.gson.Gson;
import com.google.gson.JsonArray;

public class ConvertDBResults_JavaObjects_SingleJson {

	public static void main(String[] args)
			throws SQLException, JsonGenerationException, JsonMappingException, IOException {

		Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/classicModels", "root", "kohli@18");
		Statement stmt = con.createStatement();
		String s = "select * from customerInfo";
		ResultSet rs = stmt.executeQuery(s);

		ArrayList<CustomerDetails> ar = new ArrayList<CustomerDetails>();

		while (rs.next()) {
			String bookname = rs.getString("BookName");
			String purchaseddate = rs.getString("purchasedDate");
			int amount = rs.getInt("Amount");
			String location = rs.getString("Location");

			CustomerDetails cd = new CustomerDetails();

			cd.setBookName(bookname);
			cd.setPurchasedDate(purchaseddate);
			cd.setAmount(amount);
			cd.setLocation(location);

			ar.add(cd);
		}
		JsonArray jsonr = new JsonArray();

		for (int i = 0; i < ar.size(); i++) {
			// converting java object into json string
			Gson g = new Gson();
			String jsonstring = g.toJson(ar.get(i));
			jsonr.add(jsonstring); // add json string to json array
		}

		JSONObject jo = new JSONObject();
		jo.put("data", jsonr);
		System.out.println(jo.toJSONString()); // contains escape characters

		String jsonformattedstring = jo.toJSONString().replace("\\\"", "\""); // remove escape characters
		System.out.println(jsonformattedstring);

		String finalJson = jsonformattedstring.replace("\"{", "{").replace("}\"", "}"); // remove double quotes from
																						// both sides
		System.out.println(finalJson);
		
		

		con.close();
		System.out.println("Done!!!");
	}

}
