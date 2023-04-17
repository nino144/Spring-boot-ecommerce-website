package com.example.AmadoFurniture.Service;

import com.example.AmadoFurniture.Repository.BrandRepository;
import com.example.AmadoFurniture.model.Brand;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;


public class BrandServiceImpl implements BrandService {

    @Autowired
    private BrandRepository BrandRepository;
    
    @Override
    public List<Brand> getAllBrand() {
        return (List<Brand>) BrandRepository.findAll();
    }

    @Override
    public Page<Brand> findAllBrand(int currentPage, int size, String sortField, String sortDirection) {
        Sort sort = sortDirection.equalsIgnoreCase(Sort.Direction.ASC.name()) ?
                    Sort.by(sortField).ascending() :Sort.by(sortField).descending();
        
        Pageable pageable = PageRequest.of(currentPage, size, sort);
        return BrandRepository.findAllBrand(pageable);
    }

    @Override
    public void addNewBrand(String brandName) {
        if (BrandRepository.existsByBrandName(brandName)) {
            throw new IllegalArgumentException("Brand with name " + brandName + " already exists.");
        }
        Brand brand = new Brand();
        brand.setBrand_name(brandName);
        brand.setActive(true);      
        BrandRepository.save(brand);
    }

    @Override
    public void updateBrandByName(int brandId, String brandName) {
        Optional<Brand> optionalBrand = BrandRepository.findById(brandId);

        if (optionalBrand.isPresent()) {
            Brand brand = optionalBrand.get();
            brand.setBrand_name(brandName);
            BrandRepository.save(brand);
        }
        else{
            throw new IllegalArgumentException("Not exists.");
        }
    }

    @Override
    public void updateBrandByState(int brandId, boolean state) {
        Optional<Brand> optionalBrand = BrandRepository.findById(brandId);

        if (optionalBrand.isPresent()) {
            Brand brand = optionalBrand.get();
            brand.setActive(state);
            BrandRepository.save(brand);
        }
        else{
            throw new IllegalArgumentException("Not exists.");
        }
    }

    @Override
    public Brand findBrandByName(String brandName) {
       return BrandRepository.findBrandByName(brandName);
    }
    
}
