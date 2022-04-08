package com.example.demo.model.bootstrap;

import com.example.demo.model.Country;
import com.example.demo.service.AuthorService;
import com.example.demo.service.BookService;
import com.example.demo.service.CountryService;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class DataInitializer {
    private final AuthorService authorService;
    private final BookService bookService;
    private final CountryService countryService;

    public DataInitializer(AuthorService authorService, BookService bookService, CountryService countryService) {
        this.authorService = authorService;
        this.bookService = bookService;
        this.countryService = countryService;
    }
    @PostConstruct
    public void initData(){
        Country country=this.countryService.save("Macedonia","Europe");
        this.authorService.save("name","surname",country);
        this.authorService.save("name1","surname1",country);
    }
}
