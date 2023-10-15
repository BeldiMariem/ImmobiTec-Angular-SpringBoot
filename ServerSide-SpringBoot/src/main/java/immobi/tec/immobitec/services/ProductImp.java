package immobi.tec.immobitec.services;

import immobi.tec.immobitec.entities.AppUser;
import immobi.tec.immobitec.entities.Product;
import immobi.tec.immobitec.entities.Session;
import immobi.tec.immobitec.repositories.ProductRepo;
import immobi.tec.immobitec.repositories.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.nio.Buffer;
import java.util.*;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ProductImp implements IProduct {
    @Autowired
    ProductRepo productRepo;
    private final ScheduledExecutorService executorService = Executors.newSingleThreadScheduledExecutor();

    @Override
    public Product addProduct(Product p) {
        AppUser user = userRepository.findByEmail(Session.getEmail());
        p.setUser(user);
        return productRepo.save(p);
    }

    @Override
    public List<Product> getAllProducts() {
        return productRepo.findAll();
    }

    @Override
    public Product getProductByid(int id_product) {
        return productRepo.findById(id_product).orElse(null);
    }


    @Override
    public void deleteProduct(int id_product) {
        productRepo.deleteById(id_product);
    }

    @Override
    public Product updateProduct(Product p) {
        return productRepo.save(p);
    }

    @Override
    public Product addImage(Product p, BufferedImage image) throws IOException {
        String dominantColor = dominantColor(image);

        // Save the image to disk
        String filename = "product_" + p.getName() + ".jpg";
        File file = new File(filename);
        ImageIO.write(image, "jpg", file);

        // Update the product with the image path and dominant color
        p.setImage(filename);
        p.setColors(dominantColor);
        return productRepo.save(p);
    }


    @Override
    public void productSale(int id, float newValue, long duration, TimeUnit unit) {
        Product product = productRepo.findById(id).orElse(null);

        float originalValue = product.getPrice();

        product.setPrice(newValue);

        productRepo.save(product);

        executorService.schedule(() -> {
            Product updatedProduct = productRepo.findById(id).orElse(null);
            updatedProduct.setPrice(originalValue);
            productRepo.save(updatedProduct);
        }, duration, unit);

    }


    @Override
    public String dominantColor(BufferedImage image) throws IOException {
        //BufferedImage image = ImageIO.read();

        // Convert the image to the HSB color space
        BufferedImage hsbImage = new BufferedImage(image.getWidth(), image.getHeight(), BufferedImage.TYPE_INT_RGB);
        Color.RGBtoHSB(0, 0, 0, null); // This line forces the HSB color model to be loaded
        Color.RGBtoHSB(255, 255, 255, null); // This line forces the HSB color model to be loaded
        Color.RGBtoHSB(127, 127, 127, null); // This line forces the HSB color model to be loaded
        Color.RGBtoHSB(128, 128, 128, null); // This line forces the HSB color model to be loaded
        Color.RGBtoHSB(129, 129, 129, null); // This line forces the HSB color model to be loaded
        for (int x = 0; x < image.getWidth(); x++) {
            for (int y = 0; y < image.getHeight(); y++) {
                Color color = new Color(image.getRGB(x, y));
                float[] hsb = Color.RGBtoHSB(color.getRed(), color.getGreen(), color.getBlue(), null);
                int rgb = Color.HSBtoRGB(hsb[0], hsb[1], hsb[2]);
                hsbImage.setRGB(x, y, rgb);
            }
        }

        // Analyze the color distribution
        int[] hueCounts = new int[360];
        int maxHueCount = 0;
        int dominantHue = 0;
        for (int x = 0; x < hsbImage.getWidth(); x++) {
            for (int y = 0; y < hsbImage.getHeight(); y++) {
                Color color = new Color(hsbImage.getRGB(x, y));
                float[] hsb = Color.RGBtoHSB(color.getRed(), color.getGreen(), color.getBlue(), null);

                int hue = (int) (hsb[0] * 360f);
                hueCounts[hue]++;
                if (hueCounts[hue] > maxHueCount) {
                    maxHueCount = hueCounts[hue];
                    dominantHue = hue;
                }
            }
        }

        // Output the dominant color
        Color dominantColor = Color.getHSBColor((float) dominantHue / 360f, 1f, 1f);
        String dominantColorRGB = String.format("%d,%d,%d", dominantColor.getRed(), dominantColor.getGreen(), dominantColor.getBlue());
        return dominantColorRGB;


    }

    @Override
    public List<Product> getProductsByClosestColor(int red, int green, int blue) throws IOException {
        List<Product> products = getAllProducts();


        // Calculate the distance between each product's color and the given RGB values
        Map<Double, List<Product>> distances = new TreeMap<>();
        for (Product product : products) {

            String[] rgb = product.getColors().split(",");
            int redP = Integer.parseInt(rgb[0]);
            int greenP = Integer.parseInt(rgb[1]);
            int blueP = Integer.parseInt(rgb[2]);

            Color color = new Color(red, green, blue);
            double distance = Math.sqrt(
                    Math.pow(red - redP, 2) +
                            Math.pow(green - greenP, 2) +
                            Math.pow(blue - blueP, 2)
            );
            if (!distances.containsKey(distance)) {
                distances.put(distance, new ArrayList<>());
            }
            distances.get(distance).add(product);
        }

        // Find the product(s) with the closest color
        double closestDistance = distances.keySet().stream().findFirst().orElse(null);
        return distances.get(closestDistance);
    }

    @Override
    public List<Product> sortProductsByPrice(List<Product> products, boolean ascending) {
        Comparator<Product> comparator = Comparator.comparingDouble(Product::getPrice);
        if (!ascending) {
            comparator = comparator.reversed();
        }
        return products.stream()
                .sorted(comparator)
                .collect(Collectors.toList());
    }

    Set<String> appliedPromoCodes = new HashSet<>();
    @Autowired
    private UserRepository userRepository;

    @Override
    public Product promoCodeDiscount(int productId, String code) {




        String PROMO_CODE = "Promo12345";




        Product product = productRepo.findById(productId).orElse(null);

        if(PROMO_CODE.equals(code)&& !appliedPromoCodes.contains(code)){
            float discountedPrice = (float) (product.getPrice()*0.6);
            product.setPrice(discountedPrice);
            appliedPromoCodes.add(code);
            productRepo.save(product);


            return product;
        } else
        {
            return null;
        }
    }

}







