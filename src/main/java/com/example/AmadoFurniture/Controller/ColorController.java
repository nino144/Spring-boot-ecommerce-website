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

import com.example.AmadoFurniture.Service.ColorService;
import com.example.AmadoFurniture.Service.Pagination;
import com.example.AmadoFurniture.model.Color;

@Controller
public class ColorController {

    @Autowired
    private ColorService ColorService;

    public Page<Color> colors;
    
    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/color")
    public String getColor(@RequestParam(name = "page", defaultValue = "0", required = false) Integer currentPage,
                           @RequestParam(name = "sortField", defaultValue = "color_id") String sortField,
                           @RequestParam(name = "sortDir", defaultValue = "desc") String sortDir,
                           Model model){     

            
        colors = ColorService.findAllColor(currentPage, 3, sortField, sortDir);
        int totalPages = colors.getTotalPages();
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
        model.addAttribute("colors", colors);

        return "color";
    }

    @PostMapping("/color")
    public String addColor(@RequestParam(name = "add-color",  required = false) String newColorName){
        if(newColorName != null){
            ColorService.addNewColor(newColorName);
        }
       
        return "redirect:/color";
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/color/statechange")
    public String changeColorState(@RequestParam(name = "id") Integer colorId,
                                   @RequestParam(name = "state") boolean state){
        if(colorId != null){
            ColorService.updateColorByState(colorId, !state);
        }
        
        return "redirect:/color";
    }
}
