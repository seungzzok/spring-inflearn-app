package com.example.inflearn_app.domain.book;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Book {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id = null;

  @Column(nullable = false)
  private String name;

  protected Book() {
  }

  public Book(String name) {
    if (name == null || name.isBlank()) {
      throw new IllegalArgumentException("Book name cannot be null");
    }
    this.name = name;
  }

  public Long getId() {
    return id;
  }

  public String getName() {
    return name;
  }
}
