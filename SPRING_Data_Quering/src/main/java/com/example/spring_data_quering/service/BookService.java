package com.example.spring_data_quering.service;


import com.example.spring_data_quering.entity.Book;
import com.example.spring_data_quering.entity.EditionType;

import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;


public interface BookService {
    void seedBooks() throws IOException;

    List<Book> findAllBooksAfterYear(int year);

    List<String> findAllAuthorsWithBooksWithReleaseDateBeforeYear(int year);

    List<String> findAllBooksByAuthorFirstAndLastNameOrderByReleaseDate(String firstName, String lastName);

    List<String> findTitlesWithAgeRestriction(String ageRestriction);


    List<String> findTitlesByEditionAndCopies(EditionType editionType, int copies);

    List<String> findTitlesAndPriceLowerThanOrHigherThan(BigDecimal lowerBound, BigDecimal higherBound);

    List<String> findAllBooksNotReleasedIn(LocalDate startDate, LocalDate endDate);

    List<String> findAllBooksReleasedBefore(String year);

    List<String> findAllBooksTitleContaining(String input);

    List<String> findAllBooksByAuthorLastNameStartsWith(String input);

    int findAllBooksWhereTitleLengthLongerThan(int length);
}
