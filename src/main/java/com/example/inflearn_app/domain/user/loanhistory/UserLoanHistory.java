package com.example.inflearn_app.domain.user.loanhistory;

import com.example.inflearn_app.domain.user.User;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

@Entity
public class UserLoanHistory {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id = null;

  @ManyToOne
  private User user;
  private String bookName;
  private boolean isReturn;

  protected UserLoanHistory() {
  }

  public UserLoanHistory(User user, String bookName) {
    this.user = user;
    this.bookName = bookName;
    this.isReturn = false;
  }

  public String getBookName() {
    return bookName;
  }

  public void doReturn() {
    this.isReturn = true;
  }
}
