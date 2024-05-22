package backend;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;
import org.umaguessr.backend.ImageService;
import org.umaguessr.backend.Image;



import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ImageServiceTest {

    private ImageService imageRepository;

    @BeforeEach
    void setUp() throws IOException {
        imageRepository = new ImageService("images.json");
    }

    @Test
    void testLoadImagesSuccessfully() {
        assertNotNull(imageRepository);
        List<Image> images = imageRepository.getAllImages();
        assertEquals(5, images.size());
        assertEquals("img101", images.get(0).getId());
        assertEquals("img102", images.get(1).getId());
    }

        
    @Test
    void testLoadImagesFileNotFound() {
    	
        IllegalStateException exception = assertThrows(IllegalStateException.class, () -> {
            new ImageService("totallyNotExisting.json");
        });

        assertEquals("images.json file not found in classpath", exception.getMessage());
    }

    @Test
    void testLoadImagesMalformedJson() throws IOException {
        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            new ImageService("badImages.json");
        });

        assertTrue(exception.getMessage().contains("Failed to load images from JSON file"));
    }
    
    @Test
    void testGetImageDataValidId() {
        // Test with a valid image ID that exists in the JSON file
        Image image = imageRepository.getImageData("img101");
        assertNotNull(image, "Image should not be null");
        assertEquals("img101", image.getId(), "Image ID should match");
        assertEquals("https://example.com/image101.jpg", image.getURL(), "Image URL should match");
        assertEquals(150, image.getCoordinates()[0], "X coordinate should match");
        assertEquals(250, image.getCoordinates()[1], "Y coordinate should match");
    }

    @Test
    void testGetImageDataInvalidId() {
        // Test with an invalid image ID that does not exist in the JSON file
        Image image = imageRepository.getImageData("nonexistent");
        assertNull(image, "Image should be null for an invalid ID");
    }
}
