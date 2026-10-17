package com.giovanni.libraryapi.controller;

import com.giovanni.libraryapi.service.AuthorService;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.verifyNoInteractions;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

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

}
