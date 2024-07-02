package com.example.inflearn_app.service.user;

import com.example.inflearn_app.dto.user.request.UserCreateRequest;
import com.example.inflearn_app.dto.user.request.UserUpdateRequest;
import com.example.inflearn_app.dto.user.response.UserResponse;
import com.example.inflearn_app.repository.user.UserJdbcRepository;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class UserService {

  private final UserJdbcRepository userJdbcRepository;

  // 생성자
  public UserService(UserJdbcRepository userJdbcRepository) {
    this.userJdbcRepository = userJdbcRepository;
  }

  // Create 함수
  public void saveUser(UserCreateRequest request) {
    userJdbcRepository.saveUser(request.getName(), request.getAge());
  }

  // Read 함수
  public List<UserResponse> getUsers() {
    return userJdbcRepository.getUsers();
  }

  // Update 함수
  public void updateUser(UserUpdateRequest request) {
    boolean isUserNotExist = userJdbcRepository.isUserNotExist(request.getId());
    if (isUserNotExist) {
      throw new IllegalArgumentException();
    }

    userJdbcRepository.updateUserName(request.getName(), request.getId());
  }

  // Delete 함수
  public void deleteUser(String name) {
    boolean isUserNotExist = userJdbcRepository.isUserNotExist(name);
    if (isUserNotExist) {
      throw new IllegalArgumentException();
    }

    userJdbcRepository.deleteUser(name);
  }

}
