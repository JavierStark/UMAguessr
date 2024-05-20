package org.umaguessr.backend;

import java.util.Iterator;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class AppTest {

	public static void main(String[] args){
				
		ObjectMapper objm = new ObjectMapper();
			
		try {
			String json = "{ \"x\": 1, \"y\": 44 }";
			Image img1= objm.readValue(json, Image.class); 
			System.out.println(img1.getXCoord());
			System.out.println(img1.getYCoord());
		} catch (Exception e){
			System.out.println(":)");
			e.printStackTrace();
		}
		
		
	}

}
