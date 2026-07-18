package com.giovanni.libraryapi.service;

import com.giovanni.libraryapi.entity.Author;
import com.giovanni.libraryapi.repository.AuthorRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class AuthorService {

    private final AuthorRepository repository;

    public AuthorService(AuthorRepository repository) {
        this.repository = repository;
    }

    public Author saveAuthor(Author author){
        return repository.save(author);
    }

    public List<Author> getAllAuthors(){
        return repository.findAll();
    }

    public Author getAuthorById(Long id){
        return repository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Autor não encontrado"
                ));
    }
}
