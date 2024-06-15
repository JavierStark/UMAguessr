package org.umaguessr.backend;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.Set;
import java.util.HashSet;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import javax.imageio.ImageIO;
import javax.xml.crypto.Data;

/**
 * This class handles the exchange of images between frontend
 * and backend.
 */
public class ImageService {

    private List<Image> imagesData;
    private final Set<String> playedImageIds;

    public ImageService() {

        imagesData = new ArrayList<>();
        playedImageIds = new HashSet<>();

        try {
            loadImagesData();
        } catch (SQLException e) {
            handleSQLException(e);
        }
    }

    public ImageService(ImageFilter filter) {
        this();
        loadImagesWithFilter(filter);
    }

    public List<Image> getAllImages() {
        return new ArrayList<>(imagesData);
    }

    public void loadImagesData() throws SQLException {

        DatabaseService.loadImagesData(imagesData);
    }

    public void loadImagesWithFilter(ImageFilter filter) {

        List<Image> newImageList = new ArrayList<>();
        if (imagesData != null) {
            for (Image img : imagesData) {
                if (filter.check(img)) {
                    newImageList.add(img);
                }
            }
        }
        imagesData = newImageList;

    }

    public Image getImageData(String id) {
        Optional<Image> foundImage = imagesData
                .stream()
                .filter(image -> image.getId().equals(id))
                .findFirst();
        return foundImage.orElse(null);
    }

    public String getRandomUnplayedImageId() {

        List<Image> unplayedImages = new ArrayList<>();

        fillUnplayedImages(unplayedImages);

        if (unplayedImages.isEmpty()) {
            playedImageIds.clear();
            return getRandomUnplayedImageId();
        }

        Random randomSelector = new Random();
        Image randomImage = unplayedImages.get(randomSelector.nextInt(unplayedImages.size()));
        playedImageIds.add(randomImage.getId());
        return randomImage.getId();
    }

    public BufferedImage readImageFromURL(String imageUrl) throws IOException, URISyntaxException {
        URI uri = new URI(imageUrl);
        URL url = uri.toURL();
        return ImageIO.read(url);
    }

    private void handleSQLException(SQLException e) {
        e.printStackTrace();
    }

    private void fillUnplayedImages(List<Image> unplayedImages){
        for (Image image : imagesData) {
            if (!playedImageIds.contains(image.getId())) {
                unplayedImages.add(image);
            }
        }
    }

}