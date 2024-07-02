package com.example.inflearn_app.domain.user.loanhistory;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserLoanHistoryRepository extends JpaRepository<UserLoanHistory, Long> {

  // select * from user_loan_history where book_name = ? and is_return = ?
  boolean existsByBookNameAndIsReturn(String name, boolean isReturn);

  Optional<UserLoanHistory> findByUserIdAndBookName(long userId, String bookName);
}
