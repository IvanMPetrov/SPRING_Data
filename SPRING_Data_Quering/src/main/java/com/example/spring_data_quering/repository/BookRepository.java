package com.example.spring_data_quering.repository;

import com.example.spring_data_quering.entity.AgeRestriction;
import com.example.spring_data_quering.entity.Book;
import com.example.spring_data_quering.entity.EditionType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {

    List<Book> findAllByReleaseDateAfter(LocalDate releaseDateAfter);

    List<Book> findAllByReleaseDateBefore(LocalDate releaseDateBefore);

    List<Book> findAllByAuthor_FirstNameAndAuthor_LastNameOrderByReleaseDateDescTitle(String author_firstName, String author_lastName);

    List<Book> findByAgeRestriction(AgeRestriction restriction);

    List<Book> findByEditionTypeAndCopiesLessThan(EditionType editionType, int copies);

    List<Book> findByPriceLessThanOrPriceGreaterThanOrderByPrice(BigDecimal lowerBound, BigDecimal higherBound);

    List<Book> findAllByReleaseDateAfterOrReleaseDateBefore(LocalDate startDate, LocalDate endDate);

    List<Book> findByTitleContaining(String input);

    List<Book> findByAuthorLastNameStartingWith(String input);

    @Query("SELECT COUNT(*) FROM Book b WHERE LENGTH(b.title) > :titleLength")
    int findByTitleLengthGreaterThan(@Param("titleLength") int length);
}
