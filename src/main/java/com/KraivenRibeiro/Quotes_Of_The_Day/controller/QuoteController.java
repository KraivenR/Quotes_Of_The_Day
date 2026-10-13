package com.KraivenRibeiro.Quotes_Of_The_Day.controller;

import com.KraivenRibeiro.Quotes_Of_The_Day.model.Quote;
import com.KraivenRibeiro.Quotes_Of_The_Day.repository.QuoteRepository;
import lombok.RequiredArgsConstructor;

import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.config.Task;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

@RestController
@RequestMapping("/api/quotes")
@RequiredArgsConstructor
public class QuoteController {
    
    private final QuoteRepository quoteRepository;

    @GetMapping
    public ResponseEntity<Page<Quote>> getAllQuotes( 
            @RequestParam(defaultValue = "0") int page,
             @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "createdAt") String sortBy,
            @RequestParam(defaultValue = "DESC") String direction) {
        Sort.Direction sortDirection = direction.equalsIgnoreCase("ASC") ?
            Sort.Direction.ASC : Sort.Direction.DESC;
                
        Pageable pageable = PageRequest.of(page, size, Sort.by(sortDirection, sortBy));
        Page<Quote> quotes = quoteRepository.findAll(pageable);

        return ResponseEntity.ok(quotes);

} 
    // Get a quote by ID
    @GetMapping("/{id}")
    public ResponseEntity<Quote> getQuoteById(@PathVariable Long id) {
        return quoteRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Quote> createQuote(@RequestBody Quote quote) {
        Quote savedQuote = quoteRepository.save(quote);
    
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(quote);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Quote> updateQuote(
            @PathVariable Long id,
            @RequestBody Quote quoteDetails) {

                 return quoteRepository.findById(id)
                .map(quote -> {
                    quote.setText(quoteDetails.getText());
                    quote.setAuthor(quoteDetails.getAuthor());
                    quote.setCategories(quoteDetails.getCategories());

                    Quote updatedQuote = quoteRepository.save(quote);

                    return ResponseEntity.ok(updatedQuote);
                })
                .orElse(ResponseEntity.notFound().build());
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteQuote(@PathVariable Long id) {

        if (quoteRepository.existsById(id)) {
            quoteRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.notFound().build();
    }

        @GetMapping("/search")
    public ResponseEntity<List<Quote>> searchQuotes(
            @RequestParam String text) {

        List<Quote> quotes =
                quoteRepository.findByTextContainingIgnoreCase(text);

        return ResponseEntity.ok(quotes);
    }
}

