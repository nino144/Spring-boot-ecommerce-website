package com.example.AmadoFurniture.Service;

import com.example.AmadoFurniture.model.Category;
import java.util.List;

import org.springframework.data.domain.Page;

public interface CategoryService {
    public List<Category> getAllCategory();
    public Page<Category> findAllCategory(int currentPage, int size, String sortField, String sortDirection);
    public void addNewCategory(String categoryName);
    public void updateCategoryByName(int categoryId, String categoryName);
    public void updateCategoryByState(int categoryId, boolean state);
    public Category findCategoryByName(String categoryName);
    public Category findCategoryById(int CategoryId);
}
