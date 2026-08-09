package com.giovanni.libraryapi.dto;

import com.giovanni.libraryapi.entity.Genre;

public class BookRequestDTO {

    private String title;
    private String isbn;
    private Genre genre;
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
