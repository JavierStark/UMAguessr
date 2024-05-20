package org.umaguessr.backend;

import java.io.BufferedReader;
import java.io.FileReader;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import com.google.gson.Gson;

public class AppTest {

	public static void main(String[] args){
							
		try {
			
			Scanner sc = new Scanner(Path.of("pics.json"));
			BufferedReader br = new BufferedReader(new FileReader("pics.json"));
			
			List<Image> al = new ArrayList<>();
			
			Image[] imageArray = new Gson().fromJson(br, Image[].class);
			
			al = Arrays.asList(imageArray);
			
			System.out.println(al);
			
		} catch (Exception e){
			System.out.println(":)");
			e.printStackTrace();
		}
		
		
	}

}
