package com.giovanni.libraryapi.dto;

import com.giovanni.libraryapi.entity.Book;
import com.giovanni.libraryapi.entity.Genre;

public class BookResponseDTO {

    private Long id;
    private String title;
    private String isbn;
    private Genre genre;
    private Long authorId;
    private String authorName;

    public Long getId() {
        return id;
    }

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

    public String getAuthorName() {
        return authorName;
    }

    public void setId(Long id) {
        this.id = id;
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

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }

    public BookResponseDTO(Long id, String title, String isbn, Genre genre, Long authorId, String authorName) {
        this.id = id;
        this.title = title;
        this.isbn = isbn;
        this.genre = genre;
        this.authorId = authorId;
        this.authorName = authorName;
    }

    public static BookResponseDTO fromEntity(Book book){
        return  new BookResponseDTO(

            book.getId(),
            book.getTitle(),
            book.getIsbn(),
            book.getGenre(),
            book.getAuthor().getId(),
            book.getAuthor().getName()
        );
    }


}
