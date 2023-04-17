package com.example.AmadoFurniture.Service;

import com.example.AmadoFurniture.model.*;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ProductService {
    public List<Product> getIndexProduct();
    public Page<Product> getAllProduct(int currentPage,int size, 
                                    String sortField, String sortDirection);
    public Page<Product> findProductByCategory(int currentPage,int size, 
                                    String sortField, String sortDirection, Category category);
    public Page<Product> getAllProductBothState(int currentPage,int size, 
                                    String sortField, String sortDirection);
    public Product getProductById(int id);
    public Product getProductByIdBothState(int id);
    public Page<Product> findProductsByShopFilter(List<String> brands, double minPrice, double maxPrice, String color, Pageable pageable);
    public Page<Product> findProductByName(String name, int currentPage,int size,
                                        String sortField, String sortDirection);
    public Page<Product> findProductByNameBothState(String name, int currentPage,int size,
                                        String sortField, String sortDirection);
    public void updateProductByState(int productId, boolean state);
    public void saveProduct(Product product);

}
