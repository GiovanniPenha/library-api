package com.giovanni.libraryapi.controller;

import com.giovanni.libraryapi.entity.Author;
import com.giovanni.libraryapi.service.AuthorService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
