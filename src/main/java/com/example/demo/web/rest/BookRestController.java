package com.example.demo.web.rest;

import com.example.demo.model.Book;
import com.example.demo.model.dto.BookDto;
import com.example.demo.service.BookService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins ="https://react-181534.herokuapp.com/")
@RequestMapping("/api")
public class BookRestController {
    private final BookService bookService;

    public BookRestController(BookService bookService) {
        this.bookService = bookService;
    }
    @GetMapping({"/","books"})
    public List<Book> findAll(){
        return bookService.findAll();
    }
    @PostMapping("/add")
    public ResponseEntity<Book> save(@RequestBody BookDto bookDto){
        return this.bookService.addBook(bookDto).map(book -> ResponseEntity.ok().body(book))
                .orElseGet(() -> ResponseEntity.badRequest().build());
    }
    @GetMapping("/books/{id}")
    public ResponseEntity<Book> getBook(@PathVariable Long id){
        return this.bookService.getBookById(id).map(book->ResponseEntity.ok().body(book))
                .orElseGet(()-> ResponseEntity.badRequest().build());
    }
    @PutMapping("/edit/{id}")
    public ResponseEntity<Book> save(@PathVariable Long id, @RequestBody BookDto bookDto){
        return this.bookService.editBook(id,bookDto).map(book -> ResponseEntity.ok().body(book))
                .orElseGet(() -> ResponseEntity.badRequest().build());
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Book> delete(@PathVariable Long id){
        this.bookService.deleteBook(id);
        if(this.bookService.findBookById(id).isEmpty())
            return ResponseEntity.ok().build();
        return ResponseEntity.badRequest().build();
    }
    @PutMapping("/mark/{id}")
    public ResponseEntity<Book> mark(@PathVariable Long id){
        return this.bookService.markAsTaken(id).map(book -> ResponseEntity.ok().body(book))
                .orElseGet(() -> ResponseEntity.badRequest().build());
    }
}
