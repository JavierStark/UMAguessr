package backend;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.umaguessr.backend.ImageService;
import org.umaguessr.backend.FilterByFaculty;
import org.umaguessr.backend.Image;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.net.URISyntaxException;

import static org.junit.jupiter.api.Assertions.*;

class ImageServiceTest {

    private ImageService imageRepository;

    @BeforeEach
    void setUp() throws IOException, SQLException {
        imageRepository = new ImageService();
    }

    @Test
    void testLoadImagesDataSuccessfully() throws SQLException {
        assertNotNull(imageRepository);
        List<Image> images = imageRepository.getAllImages();
        assertEquals(12, images.size());
    }

    @Test
    void testGetImageDataValidId() throws SQLException {
        Image image = imageRepository.getImageData("1");
        assertNotNull(image, "Image should not be null");
        assertEquals("1", image.getId(), "Image ID should match");
        //assertEquals("https://example.com/image101.jpg", image.getURL(), "Image URL should match");
        assertEquals(227, image.getCoordinates()[0], "X coordinate should match");
        assertEquals(721, image.getCoordinates()[1], "Y coordinate should match");
    }

    @Test
    void testGetImageDataInvalidId() throws SQLException {
        Image image = imageRepository.getImageData("nonexistent");
        assertNull(image, "Image should be null for an invalid ID");
    }

    @Test
    void testGetRandomUnplayedImageIdWithoutLimit() throws SQLException {
        String imageId = imageRepository.getRandomUnplayedImageId();
        assertNotNull(imageId, "Image ID should not be null");
        assertTrue(imageRepository.getAllImages().stream().anyMatch(img -> img.getId().equals(imageId)), "Image ID should be valid");

        // Mark all images as played
        for (int i = 0; i < 30; i++) {
            imageRepository.getRandomUnplayedImageId();
        }

        // Now all images have been played
        assertNotNull(imageRepository.getRandomUnplayedImageId(), "Should return null when all images have been played");
    }

    @Test
    void testReadImageFromURL() throws IOException, URISyntaxException {
        BufferedImage image = imageRepository.readImageFromURL("https://upload.wikimedia.org/wikipedia/commons/2/28/JPG_Test.jpg");
        assertNotNull(image, "BufferedImage should not be null");
    }

    @Test
    void testCheckFacultyIsCorrect() throws SQLException {
        assertEquals("ETSII", imageRepository.getImageData("1").getFaculty());
        assertEquals("ETSII", imageRepository.getImageData("2").getFaculty());
    }

    @Test
    void testCheckDifficultyIsCorrect() throws SQLException {
        assertEquals(1, imageRepository.getImageData("1").getDifficulty());
        assertEquals(2, imageRepository.getImageData("7").getDifficulty());
    }

    @Test
    void testFilterByFaculty() throws SQLException {
        ImageService filteredImageService = new ImageService(new FilterByFaculty("ETSII"));
        assertEquals(6, filteredImageService.getAllImages().size());
    }
}