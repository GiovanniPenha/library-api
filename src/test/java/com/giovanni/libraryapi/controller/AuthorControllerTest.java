package com.giovanni.libraryapi.controller;

import com.giovanni.libraryapi.entity.Author;
import com.giovanni.libraryapi.service.AuthorService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)

public class AuthorControllerTest {

    @Mock
    private AuthorService service;

    @InjectMocks AuthorController controller;


    @Test
    void deveListarAutores() {
        Author author = new Author();
        author.setName("Machado de Assis");

        when(service.getAllAuthors()).thenReturn(List.of(author));

        List<Author> authors = controller.getAll();

        assertEquals(1, authors.size());
        assertEquals("Machado de Assis", authors.get(0).getName());
        verify(service).getAllAuthors();
    }


    @Test
    void deveCadastrarAutor() {
        Author author = new Author();
        author.setName("Clarice Lispector");

        when(service.saveAuthor(author)).thenReturn(author);

        Author savedAuthor = controller.save(author);

        assertEquals("Clarice Lispector", savedAuthor.getName());
        verify(service).saveAuthor(author);
    }


    @Test
    void deveBuscarAutorPorId() {
        Author author = new Author();
        author.setName("Machado de Assis");

        when(service.getAuthorById(1L)).thenReturn(author);

        Author foundAuthor = controller.getAuthorById(1L);

        assertEquals("Machado de Assis", foundAuthor.getName());
        verify(service).getAuthorById(1L);
    }

}
