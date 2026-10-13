package com.KraivenRibeiro.Quotes_Of_The_Day.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Locale.Category;
import java.util.Set;

@Entity 
@Table(name = "quotes")
@Data 
@NoArgsConstructor 
@AllArgsConstructor 

public class Quote {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false, length = 1000)
    private String text;
    @ManyToOne
    @JoinColumn(name = "author_id")
    private Author author;
    @Column(length = 30)
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
        name = "quotes_categories",
        joinColumns = @JoinColumn(name = "quotes_id"),
        inverseJoinColumns = @JoinColumn(name = "categories_id")
    )
    private Set<Category> categories = new HashSet<>();

   
   @Column(name = "created_at", nullable = false, updatable = false)
   private LocalDateTime createdAt;
   @Column(name = "updated_at")
   private LocalDateTime updatedAt;
   @PrePersist
   protected void onCreate() {
    createdAt = LocalDateTime.now();
    updatedAt = LocalDateTime.now();
}
    @PreUpdate
    protected void onUpdate() {
    updatedAt = LocalDateTime.now();
    }
}

