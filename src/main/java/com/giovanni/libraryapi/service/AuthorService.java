package com.giovanni.libraryapi.service;

import com.giovanni.libraryapi.entity.Author;
import com.giovanni.libraryapi.repository.AuthorRepository;
import org.springframework.stereotype.Service;

@Service
public class AuthorService {

    private final AuthorRepository repository;

    public AuthorService(AuthorRepository repository) {
        this.repository = repository;
    }

    public Author saveAuthor(Author author){
        return repository.save(author);
    }
}
