package com.example.json.Services;

import com.example.json.entities.categories.CategoryStatsDTO;
import com.example.json.entities.products.ProductWithoutBuyerDTO;

import java.util.List;

public interface ProductsService {
    List<ProductWithoutBuyerDTO> getProductsInPriceRangeForSell(
            float from, float to);

    List<CategoryStatsDTO> getCategoryStatistics();
}

