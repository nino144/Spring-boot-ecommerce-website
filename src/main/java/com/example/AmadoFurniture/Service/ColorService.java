package com.example.AmadoFurniture.Service;

import com.example.AmadoFurniture.model.Color;
import java.util.List;

import org.springframework.data.domain.Page;

public interface ColorService {
    List<Color> getAllColor();
    public Page<Color> findAllColor(int currentPage, int size, String sortField, String sortDirection);
    public void addNewColor(String colorName);
    public void updateColorByState(int colorId, boolean state);
    public Color findColorByName(String ColorName);


}
