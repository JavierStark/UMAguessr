package backend;

import org.umaguessr.backend.Image;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class ImageTest {

    @Test
    void testImageConstructorValidURL() {
        int[] coords = {100, 200};
        Image image = new Image("1", "https://example.com/image.png", coords, "ETSII", 5);
        assertNotNull(image);
        assertEquals("https://example.com/image.png", image.getURL());
        assertArrayEquals(coords, image.getCoordinates());
    }

    @Test
    void testImageConstructorInvalidURL() {
        int[] coords = {100, 200};
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            new Image("1", "http://example.com/image.png", coords, "Sciences", 5);
        });
        assertEquals("Image URL must start with 'https://'", exception.getMessage());
    }

    @Test
    void testGetURL() {
        int[] coords = {100, 200};
        Image image = new Image("1", "https://example.com/image.png", coords, "Sciences", 5);
        assertEquals("https://example.com/image.png", image.getURL());
    }

    @Test
    void testGetCoordinates() {
        int[] coords = {100, 200};
        Image image = new Image("1", "https://example.com/image.png", coords, "Sciences", 5);
        assertArrayEquals(new int[]{100, 200}, image.getCoordinates());
    }
}