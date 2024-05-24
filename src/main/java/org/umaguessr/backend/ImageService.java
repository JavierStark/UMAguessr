package org.umaguessr.backend;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.io.InputStreamReader;
import java.io.InputStream;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.Set;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import javax.imageio.ImageIO;

public class ImageService {

    private List<Image> images;
    private Set<String> playedImageIds;

    public ImageService(String filePath) {
        playedImageIds = new HashSet<>();
        loadImages(filePath);
    }

    public List<Image> getAllImages() {
        return new ArrayList<>(images);
    }

    public void loadImages(String filePath) {
        try (InputStream inputStream = getClass().getClassLoader().getResourceAsStream(filePath)) {
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

    public String getRandomUnplayedImageId() {
        List<Image> unplayedImages = new ArrayList<>();
        for (Image image : images) {
            if (!playedImageIds.contains(image.getId())) {
                unplayedImages.add(image);
            }
        }
        if (unplayedImages.isEmpty()) {
            return null;
        }
        Random random = new Random();
        Image randomImage = unplayedImages.get(random.nextInt(unplayedImages.size()));
        playedImageIds.add(randomImage.getId());
        return randomImage.getId();
    }

    public BufferedImage readImageFromURL(String imageUrl) throws IOException, URISyntaxException {
        URI uri = new URI(imageUrl);
        URL url = uri.toURL();
        return ImageIO.read(url);
    }
}