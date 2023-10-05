package com.example.spring_data_quering.service;


import com.example.spring_data_quering.entity.Author;
import com.example.spring_data_quering.entity.AuthorsNamesWithTotalCopies;

import java.io.IOException;
import java.util.List;


public interface AuthorService {
    void seedAuthors() throws IOException;

    Author getRandomAuthor();

    List<String> getAllAuthorsOrderByCountOfTheirBooks();

    List<String> findAllWhereNameEndsWith(String input);


    List<AuthorsNamesWithTotalCopies> getWithTotalCopies();
}
