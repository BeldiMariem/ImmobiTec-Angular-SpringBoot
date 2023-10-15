package immobi.tec.immobitec.services;

import immobi.tec.immobitec.entities.Product;
import org.springframework.web.multipart.MultipartFile;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.nio.Buffer;
import java.util.List;
import java.util.concurrent.TimeUnit;

public interface IProduct {

    public Product addProduct(Product p);
    public List<Product> getAllProducts();
    public Product getProductByid(int id_product);
    public void deleteProduct(int id_product);

    public Product updateProduct(Product p);
    public Product addImage(Product p, BufferedImage image)throws IOException;

    public void productSale(int id, float newValue, long duration, TimeUnit unit );
    public String dominantColor(BufferedImage image)throws IOException;
    List<Product> getProductsByClosestColor(int red, int green, int blue)throws IOException;


    public List<Product> sortProductsByPrice(List<Product> products, boolean ascending);

    public Product promoCodeDiscount(int productId,String code);

}
