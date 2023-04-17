package com.example.AmadoFurniture.Service;

import com.example.AmadoFurniture.model.Brand;
import java.util.List;

import org.springframework.data.domain.Page;

public interface BrandService {
    public List<Brand> getAllBrand();
    public Page<Brand> findAllBrand(int currentPage, int size, String sortField, String sortDirection);
    public void addNewBrand(String brandName);
    public void updateBrandByName(int brandId, String brandName);
    public void updateBrandByState(int brandId, boolean state);
    public Brand findBrandByName(String brandName);
}
