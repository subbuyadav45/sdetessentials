package sdetessentials;

import java.io.File;
import java.io.IOException;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonToJavaObject {
	public static void main(String[] args) throws JsonParseException, JsonMappingException, IOException {

		ObjectMapper om = new ObjectMapper();

		File jsonfile = new File("C:\\Users\\MANOJ\\eclipse-workspace\\sdetessentials\\custinfo0.json");
		CustomerDetails cd = om.readValue(jsonfile, CustomerDetails.class);
		System.out.println(cd.getBookName());
		System.out.println(cd.getAmount());
		System.out.println(cd.getPurchasedDate());
		System.out.println(cd.getLocation());

	}

}
