package com.example.AmadoFurniture.Service;

import com.example.AmadoFurniture.Repository.ColorRepository;
import com.example.AmadoFurniture.model.Color;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;


public class ColorServiceImpl implements ColorService {

    @Autowired
    private ColorRepository ColorRepository;
    
    @Override
    public List<Color> getAllColor() {
        return (List<Color>) ColorRepository.findAll();
    }

    @Override
    public Page<Color> findAllColor(int currentPage, int size, String sortField, String sortDirection) {
        Sort sort = sortDirection.equalsIgnoreCase(Sort.Direction.ASC.name()) ?
                    Sort.by(sortField).ascending() :Sort.by(sortField).descending();
        
        Pageable pageable = PageRequest.of(currentPage, size, sort);
        return ColorRepository.findAllColor(pageable);
    }

    @Override
    public void addNewColor(String colorName) {
        if (ColorRepository.existsByColorName(colorName)) {
            throw new IllegalArgumentException("Color with name " + colorName + " already exists.");
        }
        Color color = new Color();
        color.setColor(colorName);
        color.setActive(true);      
        ColorRepository.save(color);
    }

    @Override
    public void updateColorByState(int colorId, boolean state) {
        Optional<Color> optionalColor = ColorRepository.findById(colorId);

        if (optionalColor.isPresent()) {
            Color color = optionalColor.get();
            color.setActive(state);
            ColorRepository.save(color);
        }
        else{
            throw new IllegalArgumentException("Not exists.");
        }
    }

    @Override
    public Color findColorByName(String colorName) {
       return ColorRepository.findColorByName(colorName);
    }
    
}
