package com.example.inflearn_app.service.book;

import com.example.inflearn_app.domain.book.Book;
import com.example.inflearn_app.domain.book.BookRepository;
import com.example.inflearn_app.domain.user.User;
import com.example.inflearn_app.domain.user.UserRepository;
import com.example.inflearn_app.domain.user.loanhistory.UserLoanHistoryRepository;
import com.example.inflearn_app.dto.book.request.BookCreateRequest;
import com.example.inflearn_app.dto.book.request.BookLoanRequest;
import com.example.inflearn_app.dto.book.request.BookReturnRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class BookService {

  private final UserRepository userRepository;
  private final UserLoanHistoryRepository userLoanHistoryRepository;
  private final BookRepository bookRepository;

  public BookService(
      UserRepository userRepository,
      UserLoanHistoryRepository userLoanHistoryRepository,
      BookRepository bookRepository
  ) {
    this.userRepository = userRepository;
    this.userLoanHistoryRepository = userLoanHistoryRepository;
    this.bookRepository = bookRepository;
  }

  @Transactional
  public void saveBook(BookCreateRequest request) {
    bookRepository.save(new Book(request.getName()));
  }

  @Transactional
  public void loanBook(BookLoanRequest request) {
    // 1. 책 정보 가져오기
    Book book = bookRepository.findByName(request.getBookName())
        .orElseThrow(IllegalArgumentException::new);

    // 2. 대출중인지 확인
    // 3. 대출 중이라면 예외 발생
    if (userLoanHistoryRepository.existsByBookNameAndIsReturn(book.getName(), false)) {
      throw new IllegalArgumentException("대출중인 책입니다");
    }

    // 4. 유저 정보 가져오기
    User user = userRepository.findByName(request.getUserName())
        .orElseThrow(IllegalArgumentException::new);

    // 5. 유저정보와 책 정보를 기반으로 UserLoanHistory를 저장
    // userLoanHistoryRepository.save(new UserLoanHistory(user, book.getName()));
    user.loanBook(book.getName());
  }

  @Transactional
  public void returnBook(BookReturnRequest request) {
    User user = userRepository.findByName(request.getUserName())
        .orElseThrow(IllegalArgumentException::new);

    //    UserLoanHistory history = userLoanHistoryRepository
    //        .findByUserIdAndBookName(user.getId(), request.getBookName())
    //        .orElseThrow(IllegalArgumentException::new);
    //
    //    history.doReturn();

    user.returnBook(request.getBookName());
  }

}
