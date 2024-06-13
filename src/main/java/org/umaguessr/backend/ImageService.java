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

/**
 * This class represents an Image Service that interacts with a database to
 * retrieve and manipulate images.
 */
public class ImageService {

    private static final String JDBC_URL = "jdbc:postgresql://vps.damianverde.es:5432/umaguessr";
    private static final String JDBC_USER = "postgres";
    private static final String JDBC_PASSWORD = "bombardeenlaetsii";

    private List<Image> imagesData;
    private Set<String> playedImageIds;
    Random random;

    /**
     * Constructs a new ImageService object.
     */
    public ImageService() {
        playedImageIds = new HashSet<>();
        try {
            loadImagesData();
            random = new Random();
        } catch (SQLException e) {
            handleSQLException(e);
        }
    }

    /**
     * Constructs a new ImageService object with a specified filter.
     *
     * @param filter The filter to apply when loading images.
     */
    public ImageService(ImageFilter filter) {
        this();
        loadImagesWithFilter(filter);
    }

    /**
     * Retrieves all images from the database.
     *
     * @return A list of all images.
     */
    public List<Image> getAllImages() {
        return new ArrayList<>(imagesData);
    }

    /**
     * Loads the images data from the database.
     *
     * @throws SQLException If an SQL exception occurs.
     */
    public void loadImagesData() throws SQLException {
        String query = "SELECT * FROM images";
        try (Connection conn = DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASSWORD);
                PreparedStatement ps = conn.prepareStatement(query);
                ResultSet rs = ps.executeQuery()) {

            imagesData = new ArrayList<>();
            while (rs.next()) {
                imagesData.add(new Image(
                        Integer.toString(rs.getInt("image_id")),
                        rs.getString("image_url"),
                        new int[] { rs.getInt("pos_x"), rs.getInt("pos_y") },
                        rs.getString("faculty"),
                        rs.getInt("difficulty")));
            }
        }
    }

    /**
     * Loads the images with a specified filter.
     *
     * @param filter The filter to apply when loading images.
     */
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

    /**
     * Retrieves the image data for a specified image ID.
     *
     * @param id The ID of the image.
     * @return The image data.
     */
    public Image getImageData(String id) {
        Optional<Image> foundImage = imagesData
                .stream()
                .filter(image -> image.getId().equals(id))
                .findFirst();
        return foundImage.orElse(null);
    }

    /**
     * Retrieves a random image from the available images.
     *
     * @return A random image.
     */
    public Image getImage() {
        if (imagesData != null && !imagesData.isEmpty()) {
            return imagesData.get(0);
        }
        return null;
    }

    /**
     * Retrieves a random unplayed image ID.
     *
     * @return A random unplayed image ID.
     */
    public String getRandomUnplayedImageId() {
        List<Image> unplayedImages = new ArrayList<>();
        for (Image image : imagesData) {
            if (!playedImageIds.contains(image.getId())) {
                unplayedImages.add(image);
            }
        }
        if (unplayedImages.isEmpty()) {
            playedImageIds.clear();
            return getRandomUnplayedImageId();
        }

        Image randomImage = unplayedImages.get(random.nextInt(unplayedImages.size()));
        playedImageIds.add(randomImage.getId());
        return randomImage.getId();
    }

    /**
     * Reads an image from a specified URL.
     *
     * @param imageUrl The URL of the image.
     * @return The BufferedImage object representing the image.
     * @throws IOException        If an I/O exception occurs.
     * @throws URISyntaxException If a URI syntax exception occurs.
     */
    public BufferedImage readImageFromURL(String imageUrl) throws IOException, URISyntaxException {
        URI uri = new URI(imageUrl);
        URL url = uri.toURL();
        return ImageIO.read(url);
    }

    private void handleSQLException(SQLException e) {
        e.printStackTrace();
    }
}