package org.umaguessr.backend;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.io.InputStreamReader;
import java.io.Reader;
import java.lang.reflect.Type;
import java.net.HttpURLConnection;
import java.net.URL;
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
import javax.imageio.ImageIO;

public class ImageService {

    private List<Image> imagesData;
    private Set<String> playedImageIds;
    Random random;

    public ImageService() {
        playedImageIds = new HashSet<>();
        loadImagesData();
        random = new Random();
    }

    public List<Image> getAllImages() {
        return new ArrayList<>(imagesData);
    }

    public void loadImagesData() {
        String urlString = "https://raw.githubusercontent.com/JavierStark/UMAguessr/main/images.json";
        try {
            URI uri = new URI(urlString);
            URL url = uri.toURL();
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");

            try (Reader reader = new InputStreamReader(connection.getInputStream())) {
                Type listType = new TypeToken<List<Image>>(){}.getType();
                imagesData = new Gson().fromJson(reader, listType);
            }
        } catch (Exception e) {
            throw new RuntimeException("Failed to load images from JSON file", e);
        }
    }

    public Image getImageData(String id) {
        Optional<Image> foundImage = imagesData.stream()
                .filter(image -> image.getId().equals(id))
                .findFirst();
        return foundImage.orElse(null);
    }

    public Image getImage() {
        if (imagesData != null && !imagesData.isEmpty()) {
            return imagesData.get(0);
        }
        return null;
    }

    public String getRandomUnplayedImageId() {
        List<Image> unplayedImages = new ArrayList<>();
        for (Image image : imagesData) {
            if (!playedImageIds.contains(image.getId())) {
                unplayedImages.add(image);
            }
        }
        if (unplayedImages.isEmpty()) {
            return null;
        }

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