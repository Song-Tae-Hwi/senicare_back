package com.project.senicare.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.senicare.dto.request.auth.IdCheckRequestDto;
import com.project.senicare.dto.request.auth.SignInRequestDto;
import com.project.senicare.dto.request.auth.SignUpRequestDto;
import com.project.senicare.dto.request.auth.TelAuthCheckDto;
import com.project.senicare.dto.request.auth.TelAuthRequestDto;
import com.project.senicare.dto.response.ResponseDto;
import com.project.senicare.dto.response.auth.SignInResponseDto;
import com.project.senicare.service.AuthService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthController {

  private final AuthService authService;

  @PostMapping("/id-check")
  public ResponseEntity<ResponseDto> idCheck(
      @RequestBody @Valid IdCheckRequestDto requestBody) {
    ResponseEntity<ResponseDto> response = authService.idCheck(requestBody);
    return response;
  }

  @PostMapping("/tel-auth")
  public ResponseEntity<ResponseDto> telAuth(
      @RequestBody @Valid TelAuthRequestDto requestBody) {
    ResponseEntity<ResponseDto> response = authService.telAuth(requestBody);
    return response;
  }

  @PostMapping("/tel-auth-check")
  public ResponseEntity<ResponseDto> telAuthCheck(
      @RequestBody @Valid TelAuthCheckDto requestBody) {
    ResponseEntity<ResponseDto> response = authService.telAuthCheck(requestBody);
    return response;
  }

  @PostMapping("/sign-up")
  public ResponseEntity<ResponseDto> signUp(
      @RequestBody @Valid SignUpRequestDto requestBody) {
    ResponseEntity<ResponseDto> response = authService.signUp(requestBody);
    return response;
  }

  @PostMapping("/sign-in")
  public ResponseEntity<? super SignInResponseDto> signIn(
      @RequestBody @Valid SignInRequestDto requestBody) {
    ResponseEntity<? super SignInResponseDto> response = authService.signIn(requestBody);
    return response;
  }
}
