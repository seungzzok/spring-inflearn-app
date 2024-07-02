package com.example.inflearn_app.repository.user;

import com.example.inflearn_app.dto.user.response.UserResponse;
import java.util.List;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class UserJdbcRepository {

  private final JdbcTemplate jdbcTemplate;

  // 생성자
  public UserJdbcRepository(JdbcTemplate jdbcTemplate) {
    this.jdbcTemplate = jdbcTemplate;
  }

  // 에러 확인
  public boolean isUserNotExist(long id) {
    String readSql = "SELECT * FROM user WHERE id = ?";
    return jdbcTemplate
        .query(readSql, (rs, rowNum) -> 0, id)
        .isEmpty();
  }

  public boolean isUserNotExist(String name) {
    String readSql = "SELECT * FROM user WHERE name = ?";
    return jdbcTemplate
        .query(readSql, (rs, rowNum) -> 0, name)
        .isEmpty();
  }

  // Create 함수
  public void saveUser(String name, Integer age) {
    String sql = "insert into user (name, age) values (?, ?)";
    jdbcTemplate.update(sql, name, age);
  }

  // Read 함수
  public List<UserResponse> getUsers() {
    String sql = "select * from user";
    return jdbcTemplate.query(sql, (rs, rowNum) -> {
      long id = rs.getLong("id");
      String name = rs.getString("name");
      int age = rs.getInt("age");
      return new UserResponse(id, name, age);
    });
  }

  // Update 함수
  public void updateUserName(String name, long id) {
    String sql = "update user set name = ? where id = ?";
    jdbcTemplate.update(sql, name, id);
  }

  // Delete 함수
  public void deleteUser(String name) {
    String sql = "delete from user where name = ?";
    jdbcTemplate.update(sql, name);
  }
}
