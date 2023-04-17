package com.example.AmadoFurniture.Service;

import com.example.AmadoFurniture.Repository.ProductRepository;
import com.example.AmadoFurniture.model.Category;
import com.example.AmadoFurniture.model.Product;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository ProductRepository;
    
    @Override
    public List<Product> getIndexProduct() {
        List<Product> products = ProductRepository.findAll(PageRequest.of(0, 9)).getContent();
        return products;
    }
    
    @Override
    public Page<Product> getAllProduct(int currentPage,int size, String sortField, String sortDirection) {
        Sort sort = sortDirection.equalsIgnoreCase(Sort.Direction.ASC.name()) ?
                    Sort.by(sortField).ascending() :Sort.by(sortField).descending();
        
        Pageable pageable = PageRequest.of(currentPage, size, sort);
        return ProductRepository.findAll(pageable);
    }

    @Override
    public Page<Product> getAllProductBothState(int currentPage,int size, String sortField, String sortDirection) {
        Sort sort = sortDirection.equalsIgnoreCase(Sort.Direction.ASC.name()) ?
                    Sort.by(sortField).ascending() :Sort.by(sortField).descending();
        
        Pageable pageable = PageRequest.of(currentPage, size, sort);
        return ProductRepository.findAllBothState(pageable);
    }
    
    @Override
    public Product getProductById(int id) {
        Optional<Product> productOptional = ProductRepository.findProductById(id);

        if (productOptional.isPresent()) {
            return productOptional.get();
        }
        else{
            throw new IllegalArgumentException("Not exists.");
        }
    }

    @Override
    public Product getProductByIdBothState(int id) {
        Optional<Product> productOptional = ProductRepository.findProductByIdBothState(id);
        if (productOptional.isPresent()) {
            return productOptional.get();
        }
        else{
            throw new IllegalArgumentException("Not exists.");
        }
    }
    
    @Override
    public Page<Product> findProductsByShopFilter(List<String> brands, double minPrice, double maxPrice, String color, Pageable pageable){
        return ProductRepository.findProductsByShopFilter(brands, minPrice, maxPrice, color, pageable);
    }
    
    @Override
    public Page<Product> findProductByName(String name, int currentPage,int size, String sortField, String sortDirection){
        Sort sort = sortDirection.equalsIgnoreCase(Sort.Direction.ASC.name()) ?
                    Sort.by(sortField).ascending() :Sort.by(sortField).descending();
        
        Pageable pageable = PageRequest.of(currentPage, size, sort);
        
        return ProductRepository.findProductByName(name, pageable);
    }

    @Override
    public Page<Product> findProductByNameBothState(String name, int currentPage,int size, String sortField, String sortDirection){
        Sort sort = sortDirection.equalsIgnoreCase(Sort.Direction.ASC.name()) ?
                    Sort.by(sortField).ascending() :Sort.by(sortField).descending();
        
        Pageable pageable = PageRequest.of(currentPage, size, sort);
        
        return ProductRepository.findProductByNameBothState(name, pageable);
    }

    @Override
    public void updateProductByState(int productId, boolean state) {
        Optional<Product> optionalProduct = ProductRepository.findById(productId);

        if (optionalProduct.isPresent()) {
            Product product = optionalProduct.get();
            product.setActive(state);
            ProductRepository.save(product);
        }
        else{
            throw new IllegalArgumentException("Not exists.");
        }
    }

    @Override
    public void saveProduct(Product product) {
        ProductRepository.save(product);
    }

    @Override
    public Page<Product> findProductByCategory(int currentPage, int size, String sortField, String sortDirection,
            Category category) {
        Sort sort = sortDirection.equalsIgnoreCase(Sort.Direction.ASC.name()) ?
                    Sort.by(sortField).ascending() :Sort.by(sortField).descending();
        
        Pageable pageable = PageRequest.of(currentPage, size, sort);
        
        return ProductRepository.findProductByCategory(category, pageable);
    }
   
}
