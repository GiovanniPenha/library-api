package com.giovanni.libraryapi.controller;

import com.giovanni.libraryapi.entity.Book;
import com.giovanni.libraryapi.service.BookService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/books")
public class BookController {

    private final BookService bookService;

    public BookController(BookService bookService){
        this.bookService = bookService;
    }

    @PostMapping
    public ResponseEntity<Book> saveBook(@RequestBody Book book){

        Book savedBook = bookService.saveBook(book);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(savedBook);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Book> findById(@PathVariable Long id){

        Book book = bookService.findById(id);

        return ResponseEntity.ok(book);
    }

    @GetMapping
    public ResponseEntity<List<Book>> findAll(){

        List<Book> books = bookService.findAll();

        return ResponseEntity.ok(books);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id){

        bookService.deleteById(id);

        return  ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Book> update(@PathVariable Long id, @RequestBody Book book){

        Book updateBook = bookService.update(id, book);

        return ResponseEntity.ok(updateBook);
    }
}
