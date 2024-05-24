package backend;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.umaguessr.backend.Image;
import org.umaguessr.backend.SearchFileSystem;

class SearchFileSystemTest {

	SearchFileSystem searchTest;
	
	@BeforeEach
	void setUp() throws Exception {
		searchTest = new SearchFileSystem();
	}

	@AfterEach
	void tearDown() throws Exception {
	}
	
	@Test
	void whenReadingTheJSONItIsCorrect() {
		
		final String EXPECTED_JSON_OUTPUT = "[(ID: 1, URL: pics/Img1.jpg, X: 3, Y: 0), (ID: 2, URL: pics/Img2.jpg, X: 77, Y: 0), (ID: 3, URL: pics/Img2.jpg, X: 77, Y: 0)]";
		
		List<Image> imageList = searchTest.read("pics.json");
		
		// This depends on Image.toString(). It should be changed if it changes
		// or the .JSON is larger. It's here just to make sure that things work ;)
		assertEquals(EXPECTED_JSON_OUTPUT, imageList.toString());
		
	}
	
	@Test
	void ifFilePathIsNotCorrectItThrowsAnExceptionWithTheCorrectMessage() {
		
		String filename = "totallyNotExistingFile.json";
		
		Exception e = assertThrows(Exception.class, () -> searchTest.read(filename));
		
		assertEquals("ERROR - Error while reading JSON file", e.getMessage());
	}
	

}
