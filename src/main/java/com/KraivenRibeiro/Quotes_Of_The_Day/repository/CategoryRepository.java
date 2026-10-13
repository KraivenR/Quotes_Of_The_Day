package com.KraivenRibeiro.Quotes_Of_The_Day.repository;

import com.KraivenRibeiro.Quotes_Of_The_Day.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CategoryRepository extends JpaRepository<Category, Long> {

    // Find a category by its exact name
    Optional<Category> findByNameIgnoreCase(String name);

    // Search categories by part of their name
    List<Category> findByNameContainingIgnoreCase(String name);

    // Check whether a category already exists
    boolean existsByNameIgnoreCase(String name);
}
