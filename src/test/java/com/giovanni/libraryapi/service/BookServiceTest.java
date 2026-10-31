package com.giovanni.libraryapi.service;

import com.giovanni.libraryapi.dto.BookRequestDTO;
import com.giovanni.libraryapi.entity.Author;
import com.giovanni.libraryapi.entity.Book;
import com.giovanni.libraryapi.entity.Genre;
import com.giovanni.libraryapi.exception.AuthorNotFoundException;
import com.giovanni.libraryapi.exception.BookNotFoundException;
import com.giovanni.libraryapi.exception.IsbnAlreadyExistsException;
import com.giovanni.libraryapi.repository.AuthorRepository;
import com.giovanni.libraryapi.repository.BookRepository;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.InjectMocks;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;

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

    @Test
    void deveSalvarLivro(){
        Author author = mock(Author.class);

        BookRequestDTO bookRequest = new BookRequestDTO();
        bookRequest.setTitle("Dom Casmurro");
        bookRequest.setIsbn("123456789");
        bookRequest.setGenre(Genre.ROMANCE);
        bookRequest.setAuthorId(1L);

        when(bookRepository.existsByIsbn(bookRequest.getIsbn()))
                .thenReturn(false);

        when(authorRepository.findById(1L))
                .thenReturn(Optional.of(author));

        Book savedBook = new Book(
                "Dom Casmurro",
                "123456789",
                Genre.ROMANCE,
                author
        );

        when(bookRepository.save(any(Book.class)))
                .thenReturn(savedBook);

        Book result = bookService.saveBook(bookRequest);
        assertEquals("Dom Casmurro", result.getTitle());

    }

    @Test
    void deveLancarExcecaoQuandoIsbnJaExiste(){
        BookRequestDTO bookRequest = new BookRequestDTO();
        bookRequest.setTitle("Dom Casmurro");
        bookRequest.setIsbn("123456789");
        bookRequest.setGenre(Genre.ROMANCE);
        bookRequest.setAuthorId(1L);

        when(bookRepository.existsByIsbn(bookRequest.getIsbn()))
                .thenReturn(true);

        assertThrows(
                IsbnAlreadyExistsException.class,
                () -> bookService.saveBook(bookRequest)
        );
    }

    @Test
    void DeveLancarExcecaoQuandoAutorNaoExiste(){
        BookRequestDTO bookRequest = new BookRequestDTO();
        bookRequest.setTitle("Dom Casmurro");
        bookRequest.setIsbn("987654321");
        bookRequest.setGenre(Genre.ROMANCE);
        bookRequest.setAuthorId(999L);

        when(bookRepository.existsByIsbn(bookRequest.getIsbn()))
                .thenReturn(false);

        when(authorRepository.findById(999L))
                .thenReturn(Optional.empty());

        assertThrows(
                AuthorNotFoundException.class,
                () -> bookService.saveBook(bookRequest)
        );
    }

    @Test
    void deveListarTodosOsLivros() {
        Book book1 = new Book();
        book1.setTitle("Dom Casmurro");

        Book book2 = new Book();
        book2.setTitle("Memórias Póstumas de Brás Cubas");

        when(bookRepository.findAll())
                .thenReturn(List.of(book1, book2));

        List<Book> result = bookService.findAll();

        assertEquals(2, result.size());
        assertEquals("Dom Casmurro", result.get(0).getTitle());
        assertEquals("Memórias Póstumas de Brás Cubas", result.get(1).getTitle());
    }

    @Test
    void deveExcluirLivro() {
        Book book = new Book();
        book.setTitle("Dom Casmurro");

        when(bookRepository.findById(1L))
                .thenReturn(Optional.of(book));

        bookService.deleteById(1L);

        verify(bookRepository).delete(book);
    }

    @Test
    void deveAtualizarLivro() {
        Author author = mock(Author.class);

        Book existingBook = new Book(
                "Dom Casmurro",
                "123456789",
                Genre.ROMANCE,
                author
        );

        BookRequestDTO bookRequest = new BookRequestDTO();
        bookRequest.setTitle("Dom Casmurro - Edição Revisada");
        bookRequest.setIsbn("123456789");
        bookRequest.setGenre(Genre.ROMANCE);
        bookRequest.setAuthorId(1L);

        when(bookRepository.findById(1L))
                .thenReturn(Optional.of(existingBook));

        when(authorRepository.findById(1L))
                .thenReturn(Optional.of(author));

        when(bookRepository.save(any(Book.class)))
                .thenReturn(existingBook);

        Book result = bookService.update(1L, bookRequest);

        assertEquals("Dom Casmurro - Edição Revisada", result.getTitle());
        verify(bookRepository).save(existingBook);
    }
}
