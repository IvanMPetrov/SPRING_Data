package com.example.spring_data_quering.service;


import com.example.spring_data_quering.entity.Category;

import java.io.IOException;
import java.util.Set;


public interface CategoryService {
    void seedCategories() throws IOException;

    Set<Category> getRandomCategories();
}
