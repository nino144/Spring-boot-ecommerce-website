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

import com.example.AmadoFurniture.Service.BrandService;
import com.example.AmadoFurniture.Service.Pagination;
import com.example.AmadoFurniture.model.Brand;

@Controller
public class BrandController {

    @Autowired
    private BrandService BrandService;

    public Page<Brand> brands;
    
    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/brand")
    public String getBrand(@RequestParam(name = "page", defaultValue = "0", required = false) Integer currentPage,
                           @RequestParam(name = "sortField", defaultValue = "brand_name") String sortField,
                           @RequestParam(name = "sortDir", defaultValue = "desc") String sortDir,
                           Model model){     

        brands = BrandService.findAllBrand(currentPage, 3, sortField, sortDir);
        int totalPages = brands.getTotalPages();
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
        model.addAttribute("brands", brands);
        return "brand";
    }

    @PostMapping("/brand")
    public String addAndEditBrand(@RequestParam(name = "add-brand",  required = false) String newBrandName,
                                  @RequestParam(name = "edit-brand",  required = false) String updatedBrandName,
                                  @RequestParam(name = "brand-id",  required = false) Integer brandId){
        if(newBrandName != null){
            String trimmed = newBrandName.trim();
            trimmed = trimmed.replaceAll("\\s+", " ");
            
            BrandService.addNewBrand(trimmed);
        }
        
        if(updatedBrandName != null && brandId !=null){
            //brandId = Integer.parseInt(brandId);
            String trimmed = updatedBrandName.trim();
            trimmed = trimmed.replaceAll("\\s+", " ");
            
            BrandService.updateBrandByName(brandId, trimmed);
        }

        return "redirect:/brand";
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/brand/statechange")
    public String changeBrandState(@RequestParam(name = "id") Integer brandId,
                                   @RequestParam(name = "state") boolean state){
        if(brandId != null){
            BrandService.updateBrandByState(brandId, !state);
        }
        return "redirect:/brand";
    }
}
