package com.giovanni.libraryapi.controller;

import com.giovanni.libraryapi.dto.BookRequestDTO;
import com.giovanni.libraryapi.entity.Author;
import com.giovanni.libraryapi.entity.Book;
import com.giovanni.libraryapi.entity.Genre;
import com.giovanni.libraryapi.exception.BookNotFoundException;
import com.giovanni.libraryapi.service.BookService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(BookController.class)

public class BookControllerHttpTest {
    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private BookService bookService;

    @Test
    void deveRetornar201AoCadastrarLivro() throws Exception {

        Author author = new Author();
        author.setName("Machado de Assis");

        Book book = new Book();
        book.setTitle("Dom Casmurro");
        book.setIsbn("9781234567890");
        book.setGenre(Genre.ROMANCE);
        book.setAuthor(author);

        when(bookService.saveBook(any(BookRequestDTO.class)))
                .thenReturn(book);

        mockMvc.perform(post("/books")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                {
                                    "title": "Dom Casmurro",
                                    "isbn": "9781234567890",
                                    "genre": "ROMANCE",
                                    "authorId": 1
                                }
                                """))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.title").value("Dom Casmurro"))
                .andExpect(jsonPath("$.authorName").value("Machado de Assis"));
    }

    @Test
    void deveRetornar200AoBuscarLivroPorId() throws Exception {
        Author author = new Author();
        author.setName("Machado de Assis");

        Book book = new Book();
        book.setTitle("Dom Casmurro");
        book.setIsbn("9781234567890");
        book.setGenre(Genre.ROMANCE);
        book.setAuthor(author);

        when(bookService.findById(1L))
                .thenReturn(book);

        mockMvc.perform(get("/books/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.title").value("Dom Casmurro"))
                .andExpect(jsonPath("$.authorName").value("Machado de Assis"));
    }

    @Test
    void deveRetornar200AoListarLivros() throws Exception {
        Author author = new Author();
        author.setName("Machado de Assis");

        Book book = new Book();
        book.setTitle("Dom Casmurro");
        book.setIsbn("9781234567890");
        book.setGenre(Genre.ROMANCE);
        book.setAuthor(author);

        when(bookService.findAll()).thenReturn(List.of(book));

        mockMvc.perform(get("/books"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].title").value("Dom Casmurro"))
                .andExpect(jsonPath("$[0].authorName").value("Machado de Assis"));
    }

    @Test
    void deveRetornar404AoBuscarLivroInexistente() throws Exception {
        when(bookService.findById(999L))
                .thenThrow(new BookNotFoundException("Livro não encontrado."));

        mockMvc.perform(get("/books/999"))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.status").value(404))
                .andExpect(jsonPath("$.message").value("Livro não encontrado."));
    }

    @Test
    void deveRetornar204AoExcluirLivro() throws Exception {
        mockMvc.perform(delete("/books/1"))
                .andExpect(status().isNoContent());

        verify(bookService).deleteById(1L);
    }

    @Test
    void deveRetornar400AoCadastrarLivroComTituloVazio() throws Exception {
        mockMvc.perform(post("/books")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                            {
                                "title": "",
                                "isbn": "9781234567890",
                                "genre": "ROMANCE",
                                "authorId": 1
                            }
                            """))
                .andExpect(status().isBadRequest());

        verifyNoInteractions(bookService);
    }

    @Test
    void deveRetornar200AoAtualizarLivro() throws Exception {
        Author author = new Author();
        author.setName("Machado de Assis");

        Book book = new Book();
        book.setTitle("Dom Casmurro - Edição Revisada");
        book.setIsbn("9781234567890");
        book.setGenre(Genre.ROMANCE);
        book.setAuthor(author);

        when(bookService.update(any(Long.class), any(BookRequestDTO.class)))
                .thenReturn(book);

        mockMvc.perform(put("/books/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                            {
                                "title": "Dom Casmurro - Edição Revisada",
                                "isbn": "9781234567890",
                                "genre": "ROMANCE",
                                "authorId": 1
                            }
                            """))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.title").value("Dom Casmurro - Edição Revisada"))
                .andExpect(jsonPath("$.authorName").value("Machado de Assis"));
    }
}
