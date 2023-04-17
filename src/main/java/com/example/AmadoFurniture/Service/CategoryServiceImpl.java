package com.example.AmadoFurniture.Service;

import com.example.AmadoFurniture.Repository.CategoryRepository;
import com.example.AmadoFurniture.model.Category;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;


public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryRepository CategoryRepository;
    
    @Override
    public List<Category> getAllCategory() {
        return (List<Category>) CategoryRepository.findAll();
    }

    @Override
    public Page<Category> findAllCategory(int currentPage, int size, String sortField, String sortDirection) {
        Sort sort = sortDirection.equalsIgnoreCase(Sort.Direction.ASC.name()) ?
                    Sort.by(sortField).ascending() :Sort.by(sortField).descending();
        
        Pageable pageable = PageRequest.of(currentPage, size, sort);
        return CategoryRepository.findAllCategory(pageable);
    }

    @Override
    public void addNewCategory(String CategoryName) {
        if (CategoryRepository.existsByCategoryName(CategoryName)) {
            throw new IllegalArgumentException("Category with name " + CategoryName + " already exists.");
        }
        Category category = new Category();
        category.setCategory_name(CategoryName);
        category.setActive(true);      
        CategoryRepository.save(category);
    }

    @Override
    public void updateCategoryByName(int CategoryId, String CategoryName) {
        Optional<Category> optionalCategory = CategoryRepository.findById(CategoryId);

        if (optionalCategory.isPresent()) {
            Category category = optionalCategory.get();
            category.setCategory_name(CategoryName);
            CategoryRepository.save(category);
        }
        else{
            throw new IllegalArgumentException("Not exists.");
        }
    }

    @Override
    public Category findCategoryById(int CategoryId) {
        Optional<Category> optionalCategory = CategoryRepository.findById(CategoryId);

        if (optionalCategory.isPresent()) {
            return optionalCategory.get();
        }
        else{
            throw new IllegalArgumentException("Not exists.");
        }
    }

    @Override
    public void updateCategoryByState(int categoryId, boolean state) {
        Optional<Category> optionalCategory = CategoryRepository.findById(categoryId);

        if (optionalCategory.isPresent()) {
            Category category = optionalCategory.get();
            category.setActive(state);
            CategoryRepository.save(category);
        }
        else{
            throw new IllegalArgumentException("Not exists.");
        }
    }

    @Override
    public Category findCategoryByName(String categoryName) {
       return CategoryRepository.findCategoryByName(categoryName);
    }
    
}
