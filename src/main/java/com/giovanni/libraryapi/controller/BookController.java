package com.giovanni.libraryapi.controller;

import com.giovanni.libraryapi.dto.BookRequestDTO;
import com.giovanni.libraryapi.dto.BookResponseDTO;
import com.giovanni.libraryapi.entity.Book;
import com.giovanni.libraryapi.service.BookService;
import jakarta.validation.Valid;
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
    public ResponseEntity<BookResponseDTO> saveBook(@RequestBody @Valid BookRequestDTO book){

        Book savedBook = bookService.saveBook(book);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(BookResponseDTO.fromEntity(savedBook));
    }

    @GetMapping("/{id}")
    public ResponseEntity<BookResponseDTO> findById(@PathVariable Long id){

        Book book = bookService.findById(id);

        return ResponseEntity.ok(BookResponseDTO.fromEntity(book));
    }

    @GetMapping
    public ResponseEntity<List<BookResponseDTO>> findAll(){

        List<Book> books = bookService.findAll();

        List<BookResponseDTO> response = books.stream()
                .map(BookResponseDTO::fromEntity)
                .toList();

        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id){

        bookService.deleteById(id);

        return  ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<BookResponseDTO> update(@PathVariable Long id, @RequestBody @Valid BookRequestDTO book){

        Book updateBook = bookService.update(id, book);

        return ResponseEntity.ok(BookResponseDTO.fromEntity(updateBook));
    }
}
