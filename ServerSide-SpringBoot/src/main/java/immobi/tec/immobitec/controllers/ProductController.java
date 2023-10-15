package immobi.tec.immobitec.controllers;

import immobi.tec.immobitec.entities.AppUser;
import immobi.tec.immobitec.entities.Ordre;
import immobi.tec.immobitec.entities.Product;
import immobi.tec.immobitec.repositories.ProductRepo;
import immobi.tec.immobitec.repositories.UserRepository;
import immobi.tec.immobitec.services.IOrdre;
import immobi.tec.immobitec.services.IProduct;
import immobi.tec.immobitec.services.UserService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.List;
import java.util.concurrent.TimeUnit;

import static org.springframework.http.MediaType.MULTIPART_FORM_DATA_VALUE;

@RestController



@CrossOrigin(origins = "http://localhost:4200")

@RequestMapping("/product")


public class ProductController {
    @Autowired
    IProduct iProduct;
    @Autowired
    IOrdre iOrdre;
    @Autowired
    private ProductRepo productRepo;
    @Autowired
    UserRepository userService;

    @PostMapping(path="/addProduct")
    @ResponseBody
    //public Product addProduct(@RequestBody Product p, @RequestParam(value = "file") MultipartFile file) throws IOException {
    public Product addProduct(@RequestBody Product p) {
        return iProduct.addProduct(p);
    }


    @GetMapping("/getAllProducts")
    public List<Product> getAllProducts(){
        return iProduct.getAllProducts();
    }



    @GetMapping("/getProductByid/{id_product}")
    public Product getProductById(@PathVariable ("id_product") int id_product){
        return iProduct.getProductByid(id_product);
    }
    @PostMapping("/addORdre/{id_product}/{idUser}")
    public Ordre addOrder(@PathVariable ("id_product") int id_product,@PathVariable ("idUser") int idUser,@RequestBody Ordre order){
        Product p = iProduct.getProductByid(id_product);
        AppUser u = userService.findById(idUser).orElse(null);
        order.setUser(u);
        order.setProduct(p);
        return    iOrdre.addOrdre(order);
    }


    @DeleteMapping("/deleteProduct/{id_product}")
    public void deleteProduct(@PathVariable ("id_product") int id_product){
        iProduct.deleteProduct(id_product);
    }



    @PutMapping("/updateProduct")
    public Product updateProduct(@RequestBody Product product)

    {
        iProduct.updateProduct(product);
        return product;
    }

    @PutMapping("/productSale/{id}/{newValue}/{duration}/{unit}")
    public void productSale (@PathVariable("id")int id,
                             @PathVariable("newValue")float newValue,
                             @PathVariable("duration")long duration,
                             @PathVariable("unit") TimeUnit unit)
    {
        iProduct.productSale(id,newValue,duration,unit);
    }

    @PostMapping("/image/color")
    public String getImageDominantColor(@RequestParam(value = "file") MultipartFile file) throws IOException {
        System.out.println("3aslema"+file);
        File f = new File("C:\\Users\\Admin\\Desktop\\baabous\\temp.png");
        f.setWritable(true);
        InputStream inputStream = file.getInputStream();

//        try(OutputStream outputStream= new FileOutputStream(f)){
//            byte[] buffer = new byte[file.getInputStream().available()];
//            outputStream.write(buffer);
//
//        }

        return iProduct.dominantColor(ImageIO.read(inputStream));
        //return new Color(12,4,6);
        //return new Color(12,4,6);
    }
    @PostMapping("/addProductImage/{productId}")
    public void addImage(@PathVariable("productId") int productId, @RequestParam("file") MultipartFile file) throws IOException {
        Product product = iProduct.getProductByid(productId);
        if (product == null) {
            throw new IllegalArgumentException("Invalid product id");
        }
        if (file.isEmpty()) {
            throw new IllegalArgumentException("Empty file");
        }
        BufferedImage image = ImageIO.read(file.getInputStream());
        String dominantColor = iProduct.dominantColor(image);
        // assuming you have a "setImage" method on the Product entity
        product.setImage(file.getOriginalFilename());
        product.setColors(dominantColor);
        iProduct.updateProduct(product);
    }
    @GetMapping("/getProductsByClosestColor/{red}/{green}/{blue}")
    public List<Product> getProductsByClosestColor(
            @PathVariable("red") int red,
            @PathVariable("green") int green,
            @PathVariable("blue") int blue) throws IOException{
        return iProduct.getProductsByClosestColor(red,green,blue);
    }


    @GetMapping("/getSortedProductsByPrice/{ascending}")
    public List<Product> getProducts(@PathVariable(name = "ascending" ) boolean ascending) {

        List<Product> products = iProduct.getAllProducts();

        return iProduct.sortProductsByPrice(products,ascending);
    }

    @PutMapping("/productPromoCodeDiscount/{productId}/{promoCode}")
    public Product discountCode(@PathVariable("productId") int productId,@PathVariable("promoCode") String promoCode){
        return iProduct.promoCodeDiscount(productId,promoCode);
    }



}
