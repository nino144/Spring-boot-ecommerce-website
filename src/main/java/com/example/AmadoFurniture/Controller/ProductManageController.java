package com.example.AmadoFurniture.Controller;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.example.AmadoFurniture.Service.*;
import com.example.AmadoFurniture.form.ProductAddForm;
import com.example.AmadoFurniture.model.*;

@Controller
public class ProductManageController {

    private static final Path CURRENT_FOLDER = Paths.get(System.getProperty("user.dir"));

    @Autowired
    private ProductService ProductService;

    @Autowired
    private BrandService BrandService;
        
    @Autowired
    private CategoryService CategoryService;
    
    @Autowired
    private ColorService ColorService;

    @Autowired
    private ImageService ImageService;

    public Page<Product> products;
    public List<Brand> brands;
    public List<Category> categories;
    public List<Color> colors;
    
    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/product-manage")
    public String getProductManage(@RequestParam(name = "page", defaultValue = "0", required = false) Integer currentPage,
                                   @RequestParam(name = "sortField", defaultValue = "product_name") String sortField,
                                   @RequestParam(name = "sortDir", defaultValue = "desc") String sortDir,
                                   @RequestParam(name="product-name", required=false) String name,
                                   Model model){     

        if(name != null){
            products = ProductService.findProductByNameBothState(name, currentPage, 3, sortField, sortDir);
        }
        else{
            products = ProductService.getAllProductBothState(currentPage, 3, sortField, sortDir);
        }
        int totalPages = products.getTotalPages();
        Pagination pagination = new Pagination(totalPages,currentPage);
        List<Integer> paginationResult = pagination.getPagination();
        int startPage = paginationResult.get(0);
        int endPage = paginationResult.get(1);
        int startIndex = currentPage * 3 + 1;

        model.addAttribute("startIndex", startIndex);
        model.addAttribute("startPage", startPage);
        model.addAttribute("endPage", endPage);
        model.addAttribute("totalPage", totalPages);
        model.addAttribute("currentPage", currentPage);
        model.addAttribute("sortField", sortField);
        model.addAttribute("sortDir", sortDir);
        model.addAttribute("reverseSortDir", sortDir.equals("asc") ? "desc" : "asc");
        model.addAttribute("products", products);
        return "product-manage";
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/product-manage/statechange")
    public String changeBrandState(@RequestParam(name = "id") Integer productId,
                                   @RequestParam(name = "state") boolean state){
        if(productId != null){
            ProductService.updateProductByState(productId, !state);
        }
        return "redirect:/product-manage";
    }
    
    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/product-edit/{id}")
    public String getProductEdit(@PathVariable("id") int id, Model model){ 

        brands = BrandService.getAllBrand();
        categories = CategoryService.getAllCategory();
        colors = ColorService.getAllColor();
        
        Product product_edit = ProductService.getProductByIdBothState(id);
        List<Image> images = ImageService.findImagesByProduct(product_edit);

        ProductAddForm product = new ProductAddForm(product_edit);
        product.setProduct_id(id);
        product.setMain_image(product_edit.getMain_image());

        model.addAttribute("product",product);
        model.addAttribute("images",images);
        model.addAttribute("colors",colors);
        model.addAttribute("brands",brands);
        model.addAttribute("categories",categories);
        return "product-edit";
    }

    @PostMapping("/product-edit/{id}")
    public String postProductEdit(@PathVariable("id") int id,
                                  @ModelAttribute("product") ProductAddForm productUpdate,
                                  @RequestParam("files") MultipartFile[] files,Model model){

        String productName = productUpdate.getProduct_name();
        Integer maxQuantity =  productUpdate.getMax_quantity();
        Double price = productUpdate.getPrice();
        String description = productUpdate.getDescription();
        String brandName = productUpdate.getBrand_name();
        String colorName = productUpdate.getColor();
        String categoryName = productUpdate.getCategory_name();

        Product product_edit = ProductService.getProductByIdBothState(id);
        List<Image> images = ImageService.findImagesByProduct(product_edit);  
        images.add(0,images.get(0));
        model.addAttribute("test",images);

        if(productName != null) {
            product_edit.setProduct_name(productName);
        }

        if(maxQuantity != null ) {
            product_edit.setMax_quantity(maxQuantity);
        }

        if(price != null) {
            product_edit.setPrice(price);
        }

        if(description != null) {
            product_edit.setDescription(description);
        }
        
        if(files[0] != null && !files[0].isEmpty()){
            product_edit.setMain_image(files[0].getOriginalFilename());
        }

        if(brandName != null){
            Brand brand = BrandService.findBrandByName(brandName);
            product_edit.setBrand(brand);
        }

        if(categoryName != null){
            Category category = CategoryService.findCategoryByName(categoryName);
            product_edit.setCategory(category);
        }

        if(colorName != null){
            Color color = ColorService.findColorByName(colorName);
            product_edit.setColor(color);
        }

        int i = -1;

        for (MultipartFile file : files){
            ++i;
            if (file != null && !file.isEmpty()) {
                String fileName = file.getOriginalFilename();
                Path srcPath = Paths.get("src");
                Path mainPath = Paths.get("main");
                Path resourcePath = Paths.get("resources");
                Path staticPath = Paths.get("static");
                Path imagePath = Paths.get("image");

                if(!images.get(i).getUrl().equals(fileName)){
                    images.get(i).setUrl(fileName);
                }
        
                Path filePath = CURRENT_FOLDER.resolve(srcPath).resolve(mainPath).resolve(resourcePath)
                                          .resolve(staticPath).resolve(imagePath).resolve(fileName);
                try (OutputStream os = Files.newOutputStream(filePath)) {
                    os.write(file.getBytes());
                } catch (IOException ioe) {  
                    ioe.printStackTrace();     
                } 
            }    
        }  
    
        images.remove(0);
        ProductService.saveProduct(product_edit);
        ImageService.saveAllImage(images);
        
        return "redirect:/product-edit/{id}";
    }
    
    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/product-add")
    public String getProductAdd(Model model){  

        brands = BrandService.getAllBrand();
        categories = CategoryService.getAllCategory();
        colors = ColorService.getAllColor();
        
        ProductAddForm product = new ProductAddForm();

        model.addAttribute("product",product);
        model.addAttribute("colors",colors);
        model.addAttribute("brands",brands);
        model.addAttribute("categories",categories);

        return "product-add";
    }

    @PostMapping("/product-add")
    public String getProductAdd(@ModelAttribute("product") ProductAddForm product2,
                                @RequestParam("files") MultipartFile[] files,
                                Model model) throws IOException {
        
        Product newProduct = new Product(); 
        List<Image> images = new ArrayList<Image>();
        String productName = product2.getProduct_name();
        Integer maxQuantity =  product2.getMax_quantity();
        Double price = product2.getPrice();
        String description = product2.getDescription();                           
        String brandName = product2.getBrand_name();
        String colorName = product2.getColor();
        String categoryName = product2.getCategory_name(); 

        if(productName != null) {
            newProduct.setProduct_name(productName);
        }

        if(maxQuantity !=null ) {
            newProduct.setMax_quantity(maxQuantity);
        }

        if(price != null) {
            newProduct.setPrice(price);
        }

        if(description != null) {
            newProduct.setDescription(description);
        }
        
        if(brandName != null){
            Brand brand = BrandService.findBrandByName(brandName);
            newProduct.setBrand(brand);
        }

        if(categoryName != null){
            Category category = CategoryService.findCategoryByName(categoryName);
            newProduct.setCategory(category);
        }

        if(colorName != null){
            Color color = ColorService.findColorByName(colorName);
            newProduct.setColor(color);
        }

        if(files[0] != null && !files[0].isEmpty()){
            newProduct.setMain_image(files[0].getOriginalFilename());
        }

        newProduct.setActive(true);

        for (MultipartFile file : files){
            if (file != null && !file.isEmpty()) {
                String fileName = file.getOriginalFilename();
                Path srcPath = Paths.get("src");
                Path mainPath = Paths.get("main");
                Path resourcePath = Paths.get("resources");
                Path staticPath = Paths.get("static");
                Path imagePath = Paths.get("image");

                images.add(new Image(fileName, newProduct));
        
                Path filePath = CURRENT_FOLDER.resolve(srcPath).resolve(mainPath).resolve(resourcePath)
                                          .resolve(staticPath).resolve(imagePath).resolve(fileName);
                try (OutputStream os = Files.newOutputStream(filePath)) {
                    os.write(file.getBytes());
                } catch (IOException ioe) {       
                    throw new IOException("Could not save image file: " + fileName, ioe);
                } 
            }    
        }

    images.remove(0);
    ProductService.saveProduct(newProduct);
    ImageService.saveAllImage(images);
        return "redirect:/product-add";
    }
}
