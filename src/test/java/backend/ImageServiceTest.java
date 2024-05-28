package backend;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.umaguessr.backend.ImageService;
import org.umaguessr.backend.FilterByFaculty;
import org.umaguessr.backend.Image;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.List;
import java.net.URISyntaxException;

import static org.junit.jupiter.api.Assertions.*;

class ImageServiceTest {

    private ImageService imageRepository;

    @BeforeEach
    void setUp() throws IOException {
        imageRepository = new ImageService();
    }

    @Test
    void testLoadImagesDataSuccessfully() {
        assertNotNull(imageRepository);
        List<Image> images = imageRepository.getAllImages();
        assertEquals(5, images.size());
        assertEquals("img101", images.get(0).getId());
        assertEquals("img102", images.get(1).getId());
    }

    @Test
    void testGetImageDataValidId() {
        Image image = imageRepository.getImageData("img101");
        assertNotNull(image, "Image should not be null");
        assertEquals("img101", image.getId(), "Image ID should match");
        //assertEquals("https://example.com/image101.jpg", image.getURL(), "Image URL should match");
        assertEquals(150, image.getCoordinates()[0], "X coordinate should match");
        assertEquals(250, image.getCoordinates()[1], "Y coordinate should match");
    }

    @Test
    void testGetImageDataInvalidId() {
        Image image = imageRepository.getImageData("nonexistent");
        assertNull(image, "Image should be null for an invalid ID");
    }

    @Test
    void testGetRandomUnplayedImageId() {
        String imageId = imageRepository.getRandomUnplayedImageId();
        assertNotNull(imageId, "Image ID should not be null");
        assertTrue(imageRepository.getAllImages().stream().anyMatch(img -> img.getId().equals(imageId)), "Image ID should be valid");

        // Mark all images as played
        for (int i = 0; i < 5; i++) {
            imageRepository.getRandomUnplayedImageId();
        }

        // Now all images have been played
        assertNull(imageRepository.getRandomUnplayedImageId(), "Should return null when all images have been played");
    }

    @Test
    void testReadImageFromURL() throws IOException, URISyntaxException {
        BufferedImage image = imageRepository.readImageFromURL("https://upload.wikimedia.org/wikipedia/commons/2/28/JPG_Test.jpg");
        assertNotNull(image, "BufferedImage should not be null");
    }

    @Test
    void testCheckFacultyIsCorrect() {
        assertEquals("Sciences", imageRepository.getImageData("img101").getFaculty());
        assertEquals("ETSII", imageRepository.getImageData("img102").getFaculty());
    }

    @Test
    void testCheckDifficultyIsCorrect() {
        assertEquals(1, imageRepository.getImageData("img101").getDifficulty());
        assertEquals(7, imageRepository.getImageData("img104").getDifficulty());
    }

    @Test
    void testFilterByFaculty() {
    	ImageService filteredImageService = new ImageService(new FilterByFaculty("ETSII"));
    	assertEquals(3, filteredImageService.getAllImages().size());
    }

}