package com.giovanni.libraryapi.controller;

import com.giovanni.libraryapi.entity.Author;
import com.giovanni.libraryapi.service.AuthorService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/authors")
public class AuthorController {

    private final AuthorService service;

    public AuthorController(AuthorService service) {
        this.service = service;
    }

    @PostMapping
    public Author save(@RequestBody Author author){
        return service.saveAuthor(author);
    }

    @GetMapping
    public List<Author> getAll(){
        return service.getAllAuthors();
    }

    @GetMapping("/{id}")
    public Author getAuthorById(@PathVariable Long id){
        return service.getAuthorById(id);
    }

}
