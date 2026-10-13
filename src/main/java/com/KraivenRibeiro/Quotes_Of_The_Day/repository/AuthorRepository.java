package com.KraivenRibeiro.Quotes_Of_The_Day.repository;

import com.KraivenRibeiro.Quotes_Of_The_Day.model.Author;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface AuthorRepository extends JpaRepository<Author, Long> {

    // Find an author by their exact name
    Optional<Author> findByNameIgnoreCase(String name);

    // Search authors by part of their name
    List<Author> findByNameContainingIgnoreCase(String name);
}