package com.example.inflearn_app.controller.calculator;

import com.example.inflearn_app.dto.calculator.request.CalculatorAddRequest;
import com.example.inflearn_app.dto.calculator.request.CalculatorMultiplyRequest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CalculatorController {

  @GetMapping("/add") // GET/add
  public int addTwoNumber(CalculatorAddRequest request) {
    return request.getNum1() + request.getNum2();
  }

  @PostMapping("/multiply") // POST/multiply

  public int multiplyTwoNumbers(@RequestBody CalculatorMultiplyRequest request) {
    return request.getNum1() * request.getNum2();
  }
}
