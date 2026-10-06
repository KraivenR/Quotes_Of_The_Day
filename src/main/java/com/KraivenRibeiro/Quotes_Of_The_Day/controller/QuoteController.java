package com.KraivenRibeiro.Quotes_Of_The_Day.controller;

import com.KraivenRibeiro.Quotes_Of_The_Day.model.Quote;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

@RestController
@RequestMapping("/api/quotes")
public class QuoteController {

    private final List<Quote> quotes = new ArrayList<>();
    private final AtomicLong idCounter = new AtomicLong(1);

    @GetMapping
    public ResponseEntity<List<Quote>> getAllQuotes() {
        return ResponseEntity.ok(quotes);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Quote> getQuoteById(@PathVariable Long id) {
        return quotes.stream()
                .filter(quote -> quote.getId().equals(id))
                .findFirst()
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Quote> createQuote(@RequestBody Quote quote) {
        quote.setId(idCounter.getAndIncrement());
        quotes.add(quote);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(quote);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Quote> updateQuote(
            @PathVariable Long id,
            @RequestBody Quote updatedQuote) {

        for (int i = 0; i < quotes.size(); i++) {
            if (quotes.get(i).getId().equals(id)) {
                updatedQuote.setId(id);
                quotes.set(i, updatedQuote);

                return ResponseEntity.ok(updatedQuote);
            }
        }

        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteQuote(@PathVariable Long id) {

        boolean removed = quotes.removeIf(
                quote -> quote.getId().equals(id)
        );

        return removed
                ? ResponseEntity.noContent().build()
                : ResponseEntity.notFound().build();
    }
}