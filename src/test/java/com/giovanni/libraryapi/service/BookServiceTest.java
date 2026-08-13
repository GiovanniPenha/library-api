package com.giovanni.libraryapi.service;

import com.giovanni.libraryapi.entity.Book;
import com.giovanni.libraryapi.exception.BookNotFoundException;
import com.giovanni.libraryapi.repository.AuthorRepository;
import com.giovanni.libraryapi.repository.BookRepository;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.InjectMocks;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class BookServiceTest {

    @Mock
    BookRepository bookRepository;

    @Mock
    AuthorRepository authorRepository;

    @InjectMocks
    BookService bookService;

    @Test
    void deveEncontrarLivroPorId(){
        Book book = new Book();
        book.setTitle("Dom Casmurro");

        when(bookRepository.findById(1L))
                .thenReturn(Optional.of(book));

        Book result = bookService.findById(1L);

        assertEquals("Dom Casmurro", result.getTitle());
    }

    @Test
    void deveLancarExcecaoQuandoLivroNaoExiste(){
        when(bookRepository.findById(999L)).thenReturn(Optional.empty());

        assertThrows(
                BookNotFoundException.class,
                () -> bookService.findById(999L)
        );
    }
}
