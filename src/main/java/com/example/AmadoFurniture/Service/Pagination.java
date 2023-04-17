package com.example.AmadoFurniture.Service;

import java.util.Arrays;
import java.util.List;


public class Pagination {
    int totalPages;
    int currentPage;
    
    public Pagination(){}

    public Pagination(int totalPages, int currentPage) {
        this.totalPages = totalPages;
        this.currentPage = currentPage;
    }
        
    public List<Integer> getPagination() {
        int maxPages = Math.min(totalPages, 5);
        int midPoint = (int) Math.floor(maxPages / 2.0);
        int startPage = Math.max(0, currentPage - midPoint);
        int endPage = Math.min(totalPages - 1, startPage + maxPages - 1);
        startPage = endPage - maxPages + 1;
        return Arrays.asList(startPage, endPage);
    }
}
