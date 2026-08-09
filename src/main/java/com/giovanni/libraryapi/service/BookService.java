package com.giovanni.libraryapi.service;

import com.giovanni.libraryapi.dto.BookRequestDTO;
import com.giovanni.libraryapi.entity.Author;
import com.giovanni.libraryapi.entity.Book;
import com.giovanni.libraryapi.exception.AuthorNotFoundException;
import com.giovanni.libraryapi.exception.BookNotFoundException;
import com.giovanni.libraryapi.exception.IsbnAlreadyExistsException;
import com.giovanni.libraryapi.repository.AuthorRepository;
import com.giovanni.libraryapi.repository.BookRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {

    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;

    public BookService(BookRepository bookRepository,
                       AuthorRepository authorRepository) {
        this.bookRepository = bookRepository;
        this.authorRepository = authorRepository;
    }

    public Book saveBook(BookRequestDTO book){
        if (bookRepository.existsByIsbn(book.getIsbn())){
            throw new IsbnAlreadyExistsException("Já existe um livro cadastrado com o ISBN informado");
        }

        Author author = authorRepository.findById(book.getAuthorId())
                .orElseThrow(() -> new AuthorNotFoundException("Autor com ID informado não foi encontrado."));

        Book newBook = new Book(
                book.getTitle(),
                book.getIsbn(),
                book.getGenre(),
                author
        );
        return bookRepository.save(newBook);
    }

    public Book findById(Long id) {
        return bookRepository.findById(id)
            .orElseThrow(() ->
                    new BookNotFoundException("Livro não encontrado."));
    }

    public List<Book> findAll(){
        return bookRepository.findAll();
    }

    public void deleteById(Long id){
        Book book = findById(id);
        bookRepository.delete(book);
    }

    public Book update(Long id, BookRequestDTO book){
        Book existingBook = findById(id);

        if(!existingBook.getIsbn().equals(book.getIsbn())){
            if(bookRepository.existsByIsbn(book.getIsbn())){
                throw new IsbnAlreadyExistsException("Já existe um livro cadastrado com o ISBN informado");
            }
        }

        Author author = authorRepository.findById(book.getAuthorId())
                .orElseThrow(() ->
                        new AuthorNotFoundException("Autor não encontrado"));

        existingBook.setTitle(book.getTitle());
        existingBook.setIsbn(book.getIsbn());
        existingBook.setGenre(book.getGenre());
        existingBook.setAuthor(author);

        return bookRepository.save(existingBook);
    }
}
