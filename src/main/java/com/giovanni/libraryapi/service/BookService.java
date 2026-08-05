package com.giovanni.libraryapi.service;

import com.giovanni.libraryapi.entity.Author;
import com.giovanni.libraryapi.entity.Book;
import com.giovanni.libraryapi.exception.AuthorNotFoundException;
import com.giovanni.libraryapi.exception.BookNotFoundException;
import com.giovanni.libraryapi.exception.IsbnAlreadyExistsException;
import com.giovanni.libraryapi.repository.AuthorRepository;
import com.giovanni.libraryapi.repository.BookRepository;
import org.springframework.stereotype.Service;

@Service
public class BookService {

    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;

    public BookService(BookRepository bookRepository,
                       AuthorRepository authorRepository) {
        this.bookRepository = bookRepository;
        this.authorRepository = authorRepository;
    }

    public Book saveBook(Book book){
        if (bookRepository.existsByIsbn(book.getIsbn())){
            throw new IsbnAlreadyExistsException("Já existe um livro cadastrado com o ISBN informado");
        }

        Author author = authorRepository.findById(book.getAuthor().getId())
                .orElseThrow(() -> new AuthorNotFoundException("Autor com ID informado não foi encontrado."));

        book.setAuthor(author);
        return bookRepository.save(book);
    }

    public Book findById(Long id) {
        return bookRepository.findById(id)
            .orElseThrow(() ->
                    new BookNotFoundException("Livro não encontrado."));
    }
}
