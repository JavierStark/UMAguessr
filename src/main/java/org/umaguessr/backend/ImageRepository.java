package org.umaguessr.backend;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.io.InputStreamReader;
import java.io.InputStream;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ImageRepository {
    private ClassLoader classLoader;
    private List<Image> images;

    public ImageRepository(ClassLoader classLoader) {
        this.classLoader = classLoader;
        loadImages();
        
    }
    public List<Image> getAllImages() {
    return new ArrayList<>(images);
}

    public void loadImages() {
        try (InputStream inputStream = getClass().getClassLoader().getResourceAsStream("images.json")) {
            if (inputStream == null) {
                throw new IllegalStateException("images.json file not found in classpath");
            }
            InputStreamReader reader = new InputStreamReader(inputStream);
            Type listType = new TypeToken<List<Image>>(){}.getType();
            images = new Gson().fromJson(reader, listType);
        } catch (Exception e) {
            throw new RuntimeException("Failed to load images from JSON file", e);
        }
    }
    

    public Image getImageData(String id) {
        Optional<Image> foundImage = images.stream()
                .filter(image -> image.getId().equals(id))
                .findFirst();
        return foundImage.orElse(null);
    }

    public Image getImage() {
        if (images != null && !images.isEmpty()) {
            return images.get(0);
        }
        return null;
    }
}