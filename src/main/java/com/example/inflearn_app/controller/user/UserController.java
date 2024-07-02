package com.example.inflearn_app.controller.user;

import com.example.inflearn_app.dto.user.request.UserCreateRequest;
import com.example.inflearn_app.dto.user.request.UserUpdateRequest;
import com.example.inflearn_app.dto.user.response.UserResponse;
import com.example.inflearn_app.service.user.UserServiceV2;
import java.util.List;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

  private final UserServiceV2 userService;

  public UserController(UserServiceV2 userService) {
    this.userService = userService;
  }

  @PostMapping("/user") // POST/user
  public void saveUser(@RequestBody UserCreateRequest request) {
    userService.saveUser(request);
  }

  @GetMapping("/user")
  public List<UserResponse> getUsers() {
    return userService.getUsers();
  }

  @PutMapping("/user")
  public void updateUser(@RequestBody UserUpdateRequest request) {
    userService.updateUser(request);
  }

  @DeleteMapping("/user")
  public void deleteUser(@RequestParam String name) {
    userService.deleteUser(name);
  }
}
