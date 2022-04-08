package com.example.demo.service.impl;

import com.example.demo.model.Author;
import com.example.demo.model.dto.BookDto;
import com.example.demo.model.exceptions.AuthorNotFoundException;
import com.example.demo.model.exceptions.BookNotFoundException;
import com.example.demo.repository.AuthorRepository;
import com.example.demo.repository.BookRepository;
import com.example.demo.service.BookService;
import org.springframework.stereotype.Service;
import com.example.demo.model.Book;
import java.util.List;
import java.util.Optional;

@Service
public class BookServiceImpl implements BookService {
    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;
    public BookServiceImpl(BookRepository bookRepository, AuthorRepository authorRepository) {
        this.bookRepository = bookRepository;
        this.authorRepository = authorRepository;
    }

    @Override
    public List<Book> findAll() {
        return bookRepository.findAll();
    }

    @Override
    public Optional<Book> editBook(Long id, BookDto bookDto) {
        Book book=this.bookRepository.findById(id).orElseThrow(()->new BookNotFoundException(id));
        book.setName(bookDto.getName());
        book.setCategory(bookDto.getCategory());
        Author author1=authorRepository.findById(bookDto.getAuthor()).orElseThrow(()->new AuthorNotFoundException(id));
        book.setAuthor(author1);
        book.setAvailableCopies(bookDto.getAvailableCopies());
        bookRepository.save(book);
        return Optional.of(book);
    }

    @Override
    public Optional<Book> addBook(BookDto bookDto) {
        Author author=this.authorRepository.findById(bookDto.getAuthor()).orElseThrow(()-> new AuthorNotFoundException(bookDto.getAuthor()));
        Book book=new Book(bookDto.getName(),bookDto.getCategory(),author,bookDto.getAvailableCopies());
        this.bookRepository.save(book);
        return Optional.of(book);
    }

    @Override
    public Optional<Book> getBookById(Long id) {
        Book book=bookRepository.getById(id);
        return Optional.of(book);
    }

    @Override
    public void deleteBook(Long id) {
        this.bookRepository.deleteById(id);
    }

    @Override
    public Optional<Book> findBookById(Long id) {
        return this.bookRepository.findById(id);
    }

    @Override
    public Optional<Book> markAsTaken(Long id) {
        Book book=this.bookRepository.findById(id).orElseThrow(()->new BookNotFoundException(id));
        int copies=book.getAvailableCopies();
        if(copies>0) {
            book.setAvailableCopies(copies - 1);
        }
        this.bookRepository.save(book);
        return Optional.of(book);
    }
}
