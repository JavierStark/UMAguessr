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

    /**
     * Default constructor. Initializes the array imagesData with the images
     * from the database.
     */
    public ImageService() {

        imagesData = new ArrayList<>();
        playedImageIds = new HashSet<>();

        try {
            loadImagesData();
        } catch (SQLException e) {
            System.err.println("[ERROR | SQL]" + e.getMessage());
            e.printStackTrace();
        }
    }

    /**
     * Alternative constructor that takes into account a filter.
     * @param filter
     */
    public ImageService(ImageFilter filter) {
        this();
        loadImagesWithFilter(filter);
    }

    public List<Image> getAllImages() {
        return new ArrayList<>(imagesData);
    }

    /**
     * Method to retrieve an image given an ID.
     * @param id ID of the image to search
     * @return The Image with that ID, if it exists. Null otherwise.
     */
    public Image getImageData(String id) {
        Optional<Image> foundImage = imagesData
                .stream()
                .filter(image -> image.getId().equals(id))
                .findFirst();
        return foundImage.orElse(null);
    }

    /**
     * This method returns the ID of an image that has not been played yet.
     * @return ID of a random unplayed image
     */
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

    /**
     * This method returns a BufferedImage object from Java.awt given an online URL.
     * @param imageUrl URL of the image
     * @return The Image with that URL.
     * @throws IOException If an error occurs while reading the URL.
     * @throws URISyntaxException in the same case as URI does (If the given string violates RFC 2396)
     */
    public BufferedImage readImageFromURL(String imageUrl) throws IOException, URISyntaxException {
        URI uri = new URI(imageUrl);
        URL url = uri.toURL();
        return ImageIO.read(url);
    }

    /**
     * Auxiliary method to load the data from the database into the array of images.
     * @throws SQLException
     */
    private void loadImagesData() throws SQLException {

        DatabaseService.loadImagesData(imagesData);
    }

    /**
     * Auxiliary method that applies filter to the full array of images.
     * @param filter
     */
    private void loadImagesWithFilter(ImageFilter filter) {

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

    /**
     * Auxiliary method to fill the array of unplayed images from which the system
     * chooses one to show to the user.
     * @param unplayedImages
     */
    private void fillUnplayedImages(List<Image> unplayedImages){
        for (Image image : imagesData) {
            if (!playedImageIds.contains(image.getId())) {
                unplayedImages.add(image);
            }
        }
    }



}