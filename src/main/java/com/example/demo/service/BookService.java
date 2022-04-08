package com.example.demo.service;

import com.example.demo.model.Book;
import com.example.demo.model.dto.BookDto;

import java.util.List;
import java.util.Optional;

public interface BookService {
    List<Book> findAll();
    Optional<Book> editBook(Long id, BookDto book);
    Optional<Book> addBook(BookDto book);
    Optional<Book> getBookById(Long id);
    void deleteBook(Long id);
    Optional<Book> findBookById(Long id);
    Optional<Book> markAsTaken(Long id);
}
