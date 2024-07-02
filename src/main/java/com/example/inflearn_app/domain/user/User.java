package com.example.inflearn_app.domain.user;

import com.example.inflearn_app.domain.user.loanhistory.UserLoanHistory;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

@Entity
public class User {

  @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
  private final List<UserLoanHistory> userLoanHistories = new ArrayList<>();

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id = null;

  @Column(nullable = false, length = 10)
  private String name;
  private Integer age;

  // ========== Constructor ==========
  protected User() {
  }

  public User(String name, Integer age) {
    if (name == null || name.isBlank()) {
      throw new IllegalArgumentException(String.format("잘못된 name(%s)이 들어왔습니다", name));
    }
    this.name = name;
    this.age = age;
  }

  // ========== Getter ==========
  public Long getId() {
    return id;
  }

  public String getName() {
    return name;
  }

  public Integer getAge() {
    return age;
  }

  // ========== Function ==========
  public void updateName(String name) {
    this.name = name;
  }

  public void loanBook(String bookName) {
    this.userLoanHistories.add(new UserLoanHistory(this, bookName));
  }

  public void returnBook(String bookName) {
    UserLoanHistory targetHistory = this.userLoanHistories.stream()
        .filter(history -> history.getBookName().equals(bookName))
        .findFirst()
        .orElseThrow(IllegalArgumentException::new);
    targetHistory.doReturn();
  }
}
