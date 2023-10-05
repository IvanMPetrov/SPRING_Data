package com.example.spring_data_quering;

import com.example.spring_data_quering.entity.Book;
import com.example.spring_data_quering.service.AuthorService;
import com.example.spring_data_quering.service.BookService;
import com.example.spring_data_quering.service.CategoryService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Scanner;

@Component
public class CommandLineRunnerImpl implements CommandLineRunner {

    private final CategoryService categoryService;
    private final AuthorService authorService;
    private final BookService bookService;

    public CommandLineRunnerImpl(CategoryService categoryService, AuthorService authorService, BookService bookService) {
        this.categoryService = categoryService;
        this.authorService = authorService;
        this.bookService = bookService;
    }

    @Override
    public void run(String... args) throws Exception {
//        seedData();
        Scanner scanner = new Scanner(System.in);



//        10
//        this.authorService.getWithTotalCopies()
//                .forEach(a -> System.out.println(
//                        a.getFirstName() + " " + a.getLastName() + " - " + a.getTotalCopies()));


//        09
//        int length = Integer.parseInt(scanner.nextLine());
//        System.out.println(this.bookService.findAllBooksWhereTitleLengthLongerThan(length));


//        08
//        String input = scanner.nextLine();
//        this.bookService.findAllBooksByAuthorLastNameStartsWith(input).forEach(System.out::println);


//        07
//        String input = scanner.nextLine();
//        this.bookService.findAllBooksTitleContaining(input).forEach(System.out::println);


//        06
//        String input = scanner.nextLine();
//        this.authorService.findAllWhereNameEndsWith(input).forEach(System.out::println);


//        05
//        String year = scanner.nextLine();
//        this.bookService.findAllBooksReleasedBefore(year).forEach(System.out::println);


//        04
//        int year = Integer.parseInt(scanner.nextLine());
//        LocalDate startDate = LocalDate.of(year,1,1);
//        LocalDate endDate = LocalDate.of(year,12,31);
//        this.bookService.findAllBooksNotReleasedIn(startDate,endDate).forEach(System.out::println);


//        03
//        this.bookService.findTitlesAndPriceLowerThanOrHigherThan(BigDecimal.valueOf(5),BigDecimal.valueOf(45))
//                .forEach(System.out::println);


//        02
//        this.bookService.findTitlesByEditionAndCopies(EditionType.GOLD,5000).forEach(System.out::println);


//        01
//        String ageRestriction = scanner.nextLine();
//        this.bookService.findTitlesWithAgeRestriction(ageRestriction).forEach(System.out::println);


    }

    private void printALlBooksByAuthorNameOrderByReleaseDate(String firstName, String lastName) {
        bookService
                .findAllBooksByAuthorFirstAndLastNameOrderByReleaseDate(firstName, lastName)
                .forEach(System.out::println);
    }

    private void printAllAuthorsAndNumberOfTheirBooks() {
        authorService
                .getAllAuthorsOrderByCountOfTheirBooks()
                .forEach(System.out::println);
    }

    private void printAllAuthorsNamesWithBooksWithReleaseDateBeforeYear(int year) {
        bookService
                .findAllAuthorsWithBooksWithReleaseDateBeforeYear(year)
                .forEach(System.out::println);
    }

    private void printAllBooksAfterYear(int year) {
        bookService
                .findAllBooksAfterYear(year)
                .stream()
                .map(Book::getTitle)
                .forEach(System.out::println);
    }

    private void seedData() throws IOException {
        categoryService.seedCategories();
        authorService.seedAuthors();
        bookService.seedBooks();
    }
}
