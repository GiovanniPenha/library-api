package com.giovanni.libraryapi.repository;

import com.giovanni.libraryapi.entity.Author;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthorRepository extends JpaRepository<Author, Long> {
}
