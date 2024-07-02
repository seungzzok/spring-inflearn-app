package com.example.inflearn_app.service.user;

import com.example.inflearn_app.domain.user.User;
import com.example.inflearn_app.domain.user.UserRepository;
import com.example.inflearn_app.dto.user.request.UserCreateRequest;
import com.example.inflearn_app.dto.user.request.UserUpdateRequest;
import com.example.inflearn_app.dto.user.response.UserResponse;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserServiceV2 {

  private final UserRepository userRepository;

  public UserServiceV2(UserRepository userRepository) {
    this.userRepository = userRepository;
  }

  // 아래에 있는 함수가 시작될 때 start transaction을 해줌
  // 함수가 예외 없이 잘 끝났다면 commit
  // 문제가 발생한다면 rollback
  @Transactional
  public void saveUser(UserCreateRequest request) {
    userRepository.save(new User(request.getName(), request.getAge()));
  }

  @Transactional(readOnly = true)
  public List<UserResponse> getUsers() {
    return userRepository.findAll()
        .stream()
        .map(UserResponse::new)
        .collect(
            Collectors.toList());
  }

  @Transactional
  public void updateUser(UserUpdateRequest request) {
    // select * from user where id = ?;
    // Optional<User>
    User user = userRepository.findById(request.getId())
        .orElseThrow(IllegalArgumentException::new);

    user.updateName(request.getName());
  }

  @Transactional
  public void deleteUser(String name) {
    // SELECT * FROM user WHERE name = ?
    User user = userRepository.findByName(name)
        .orElseThrow(IllegalArgumentException::new);

    userRepository.delete(user);
  }
}
