package com.KraivenRibeiro.Quotes_Of_The_Day.repository;

import com.KraivenRibeiro.Quotes_Of_The_Day.model.Quote;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface QuoteRepository extends JpaRepository<Quote, Long> {

    // Search quotes containing specific text
    List<Quote> findByTextContainingIgnoreCase(String text);

    // Find quotes by author ID
    List<Quote> findByAuthor_Id(Long authorId);

    // Find a quote by ID with its author
    Optional<Quote> findById(Long id);

    // Find quotes belonging to a category
    List<Quote> findByCategories_Id(Long categoryId);

    // Custom JPQL query: search quote text
    @Query("SELECT q FROM Quote q WHERE LOWER(q.text) LIKE LOWER(CONCAT('%', :text, '%'))")
    List<Quote> searchQuotesByText(@Param("text") String text);
}
