package com.giovanni.libraryapi.service;

import com.giovanni.libraryapi.entity.Author;
import com.giovanni.libraryapi.exception.AuthorNotFoundException;
import com.giovanni.libraryapi.repository.AuthorRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)

public class AuthorServiceTest {

    @Mock
    private AuthorRepository repository;

    @InjectMocks
    private AuthorService service;

    @Test
    void deveSalvarAuthor(){
        Author author = new Author();
        author.setName("Clarice Lispector");

        when(repository.save(author)).thenReturn(author);

        Author savedAuthor = service.saveAuthor(author);

        assertEquals("Clarice Lispector", savedAuthor.getName());
        verify(repository).save(author);

    }

    @Test
    void deveEncontrarAuthorPorId(){
        Author author = new Author();
        author.setName("Machado de Assis");

        when(repository.findById(1L)).thenReturn(Optional.of(author));

        Author foundAuthor = service.getAuthorById(1L);

        assertEquals("Machado de Assis", foundAuthor.getName());
        verify(repository).findById(1L);
    }

    @Test
    void deveLancarExcecaoQuandoAuthorNaoExiste(){
        when(repository.findById(999L)).thenReturn(Optional.empty());

        assertThrows(
                AuthorNotFoundException.class,
                () -> service.getAuthorById(999L)
        );

        verify(repository).findById(999L);
    }
}
