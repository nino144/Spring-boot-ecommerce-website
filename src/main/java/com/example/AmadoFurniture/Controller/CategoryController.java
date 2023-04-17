package com.example.AmadoFurniture.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.AmadoFurniture.Service.CategoryService;
import com.example.AmadoFurniture.Service.Pagination;
import com.example.AmadoFurniture.model.Category;

@Controller
public class CategoryController {

    @Autowired
    private CategoryService CategoryService;

    public Page<Category> categories;
    
    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/category")
    public String getCategory(@RequestParam(name = "page", defaultValue = "0", required = false) Integer currentPage,
                              @RequestParam(name = "sortField", defaultValue = "category_name") String sortField,
                              @RequestParam(name = "sortDir", defaultValue = "desc") String sortDir,
                              Model model){     

        categories= CategoryService.findAllCategory(currentPage, 3, sortField, sortDir);
        int totalPages = categories.getTotalPages();
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
        model.addAttribute("categories", categories);
        return "category";
    }

    @PostMapping("/category")
    public String addAndEditCategory(@RequestParam(name = "add-brand",  required = false) String newCategoryName,
                                     @RequestParam(name = "edit-brand",  required = false) String updatedCategoryName,
                                     @RequestParam(name = "brand-id",  required = false) Integer categoryId){
        if(newCategoryName != null ){
            String trimmed = newCategoryName.trim();
            trimmed = trimmed.replaceAll("\\s+", " ");
            
            CategoryService.addNewCategory(trimmed);
        }
        
        if(updatedCategoryName != null && categoryId != null){
            String trimmed = updatedCategoryName.trim();
            trimmed = trimmed.replaceAll("\\s+", " ");
            
            CategoryService.updateCategoryByName(categoryId, trimmed);
        }
        return "redirect:/category";
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/category/statechange")
    public String changeCategoryState(@RequestParam(name = "id") Integer categoryId,
                                   @RequestParam(name = "state") boolean state){
        if(categoryId != null){
            CategoryService.updateCategoryByState(categoryId, !state);
        }
        return "redirect:/category";
    }
}
