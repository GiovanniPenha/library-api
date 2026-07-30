package com.giovanni.libraryapi.service;

import com.giovanni.libraryapi.entity.Book;
import com.giovanni.libraryapi.exception.IsbnAlreadyExistsException;
import com.giovanni.libraryapi.repository.BookRepository;
import org.springframework.stereotype.Service;

@Service
public class BookService {

    private final BookRepository bookRepository;

    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public Book saveBook(Book book){
        if (bookRepository.existsByIsbn(book.getIsbn())){
            throw new IsbnAlreadyExistsException("Já existe um livro cadastrado com o ISBN informado");
        }
        return bookRepository.save(book);
    }
}
