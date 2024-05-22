package backend;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;
import org.umaguessr.backend.ImageRepository;
import org.umaguessr.backend.Image;



import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ImageRepositoryTest {

    @TempDir
    Path tempDir;

    private ImageRepository imageRepository;

    @BeforeEach
    void setUp() throws IOException {
        // Create a temporary images.json file
        Path jsonFile = tempDir.resolve("images.json");
        String jsonContent = "[{\"id\":\"1\", \"name\":\"Image 1\"}, {\"id\":\"2\", \"name\":\"Image 2\"}]";
        Files.writeString(jsonFile, jsonContent);

        // Set up classloader to read from temp directory
        ClassLoader classLoader = mock(ClassLoader.class);
        when(classLoader.getResourceAsStream("images.json")).thenAnswer(invocation -> Files.newInputStream(jsonFile));

        imageRepository = new ImageRepository(classLoader);
    }

    @Test
    void testLoadImagesSuccessfully() {
        assertNotNull(imageRepository);
        List<Image> images = imageRepository.getAllImages();
        assertEquals(2, images.size());
        assertEquals("1", images.get(0).getId());
        assertEquals("2", images.get(1).getId());
    }

    @Test
    void testLoadImagesFileNotFound() {
        // Mock classloader to return null for getResourceAsStream
        ClassLoader classLoader = mock(ClassLoader.class);
        when(classLoader.getResourceAsStream("images.json")).thenReturn(null);

        IllegalStateException exception = assertThrows(IllegalStateException.class, () -> {
            new ImageRepository(classLoader);
        });

        assertEquals("images.json file not found in classpath", exception.getMessage());
    }

    @Test
    void testLoadImagesMalformedJson() throws IOException {
        // Create a temporary malformed images.json file
        Path jsonFile = tempDir.resolve("images.json");
        String malformedJsonContent = "[{\"id\":\"1\", \"name\":\"Image 1\", {\"id\":\"2\", \"name\":\"Image 2\"}]";
        Files.writeString(jsonFile, malformedJsonContent);

        // Set up classloader to read from temp directory
        ClassLoader classLoader = mock(ClassLoader.class);
        when(classLoader.getResourceAsStream("images.json")).thenAnswer(invocation -> Files.newInputStream(jsonFile));

        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            new ImageRepository(classLoader);
        });

        assertTrue(exception.getMessage().contains("Failed to load images from JSON file"));
    }

    @Test
    void testGetImageDataExisting() {
        Image image = imageRepository.getImageData("1");
        assertNotNull(image);
        assertEquals("1", image.getId());
    }

    @Test
    void testGetImageDataNonExisting() {
        Image image = imageRepository.getImageData("3");
        assertNull(image);
    }
}
