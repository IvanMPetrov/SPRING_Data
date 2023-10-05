package com.example.spring_data.Service;

import com.example.spring_data.Models.Author;
import com.example.spring_data.Models.Book;
import com.example.spring_data.Models.Category;
import com.example.spring_data.Repositories.AuthorRepository;
import com.example.spring_data.Repositories.BookRepository;
import com.example.spring_data.Repositories.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

@Service
public class SeedServiceImpl implements SeedService {

    private static final String RESOURCE_PATH = "src\\main\\resources\\files";
    private static final String AUTHORS_FILE_PATH = RESOURCE_PATH + "\\authors.txt";
    private static final String CATEGORIES_FILE_PATH = RESOURCE_PATH + "\\categories.txt";
    private static final String BOOKS_FILE_PATH = RESOURCE_PATH + "\\books.txt";

    @Autowired
    public SeedServiceImpl(AuthorRepository authorRepository, CategoryRepository categoryRepository, BookRepository bookRepository) {
        this.authorRepository = authorRepository;
        this.categoryRepository = categoryRepository;
        this.bookRepository = bookRepository;
    }

    private final AuthorRepository authorRepository;
    private final CategoryRepository categoryRepository;

    private final BookRepository bookRepository;


    @Override
    public void SeedAuthors() throws IOException {

        Files.readAllLines(Path.of(AUTHORS_FILE_PATH)).stream()
                .filter(e -> !e.isBlank())
                .map(e -> e.split(" "))
                .map(name -> new Author(name[0], name[1]))
                .forEach(authorRepository::save);
    }

    @Override
    public void SeedCategories() throws IOException {

        Files.readAllLines(Path.of(CATEGORIES_FILE_PATH)).stream()
                .filter(e -> !e.isBlank())
                .map(Category::new)
                .forEach(categoryRepository::save);
    }

    @Override
    public void SeedBooks() throws IOException {
        Files.readAllLines(Path.of(BOOKS_FILE_PATH)).stream()
                .filter(el -> !el.isBlank())
                .map(this::createBookObject)
                .forEach(bookRepository::save);


    }

    private Book createBookObject(String info) {
        String[] infoParts = info.split(" ");

        return null;
    }


}
