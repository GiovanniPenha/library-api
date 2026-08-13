package com.giovanni.libraryapi.dto;

import com.giovanni.libraryapi.entity.Genre;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

public class BookRequestDTO {

    @NotBlank(message = "O título é obrigatório.")
    @Size(max = 255, message = "O título deve ter no máximo 255 caracteres.")
    private String title;

    @NotBlank(message = "O ISBN é obrigatório.")
    @Size(max = 20, message = "O ISBN deve ter no máximo 20 caracteres.")
    private String isbn;

    @NotNull(message = "O gênero é obrigatório.")
    private Genre genre;

    @NotNull(message = "O autor é obrigatório.")
    @Positive(message = "O ID do autor deve ser positivo.")
    private Long authorId;


    public String getTitle() {
        return title;
    }

    public String getIsbn() {
        return isbn;
    }

    public Genre getGenre() {
        return genre;
    }

    public Long getAuthorId() {
        return authorId;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public void setGenre(Genre genre) {
        this.genre = genre;
    }

    public void setAuthorId(Long authorId) {
        this.authorId = authorId;
    }
}
