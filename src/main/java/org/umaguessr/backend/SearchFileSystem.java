package org.umaguessr.backend;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.google.gson.Gson;

public class SearchFileSystem implements Database {
	
	@Override
	public List<Image> read(String path) {
				
		List<Image> imageList = new ArrayList<>();
		
		try {
			
			
			BufferedReader jsonReader = new BufferedReader(new FileReader(path));
						
			Image[] imageArray = new Gson().fromJson(jsonReader, Image[].class);
			
			imageList = Arrays.asList(imageArray);
			
			return imageList;
			
		} catch (FileNotFoundException e) {
			
			// this should be changed later. Let's make our own exceptions!! ;)
			throw new RuntimeException("ERROR - Error while reading JSON file");
			
		}
		
	}

}
