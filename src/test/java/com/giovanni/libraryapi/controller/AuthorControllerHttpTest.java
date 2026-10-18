package com.giovanni.libraryapi.controller;

import com.giovanni.libraryapi.exception.AuthorNotFoundException;
import com.giovanni.libraryapi.service.AuthorService;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.verifyNoInteractions;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

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

}
