package backend;

import org.umaguessr.backend.Image;
import org.umaguessr.backend.ImageRepository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ImageRepositoryTest {

    private ImageRepository imageRepository;

    @BeforeEach
    void setUp() {
        // Assuming ImageRepository can load images correctly from a predefined JSON file
        imageRepository = new ImageRepository();
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