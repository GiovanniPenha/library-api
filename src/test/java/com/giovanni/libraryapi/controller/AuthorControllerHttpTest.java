package com.giovanni.libraryapi.controller;

import com.giovanni.libraryapi.entity.Author;
import com.giovanni.libraryapi.exception.AuthorNotFoundException;
import com.giovanni.libraryapi.service.AuthorService;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.Mockito.verifyNoInteractions;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;

@WebMvcTest(AuthorController.class)
public class AuthorControllerHttpTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private AuthorService service;

    @Test
    void deveRetornar201AoCadastrarAutor() throws Exception{
        mockMvc.perform(post("/authors")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                {
                                    "name": "Clarice Lispector"
                                }
                                """))
                        .andExpect(status().isCreated());
    }


    @Test
    void deveRetornar400QuandoNomeDoAutorEstiverVazio() throws Exception {
        mockMvc.perform(post("/authors")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                {
                                    "name": ""
                                }
                                """))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.message").value("must not be blank"));

        verifyNoInteractions(service);
    }


    @Test
    void deveRetornar404QuandoAutorNaoExiste() throws Exception {
        when(service.getAuthorById(999L))
                .thenThrow(new AuthorNotFoundException("Autor não encontrado"));

        mockMvc.perform(get("/authors/999"))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.status").value(404))
                .andExpect(jsonPath("$.message").value("Autor não encontrado"));
    }


    @Test
    void deveRetornarListaDeAutores() throws Exception {
        Author author = new Author();
        author.setName("Machado de Assis");

        when(service.getAllAuthors()).thenReturn(List.of(author));

        mockMvc.perform(get("/authors"))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].name").value("Machado de Assis"));
    }


    @Test
    void deveRetornar200AoBuscarAutorPorId() throws Exception {
        Author author = new Author();
        author.setName("Machado de Assis");

        when(service.getAuthorById(1L)).thenReturn(author);

        mockMvc.perform(get("/authors/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Machado de Assis"));
    }

}
