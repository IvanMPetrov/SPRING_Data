package com.example.spring_data.Service;

import java.io.IOException;

public interface SeedService {

    void SeedAuthors() throws IOException;

    void SeedCategories() throws IOException;

    void SeedBooks() throws IOException;


   default void SeedAll() throws IOException {
       SeedAuthors();
       SeedCategories();
       SeedBooks();
   }
}
