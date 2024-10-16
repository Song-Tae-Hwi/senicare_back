package com.project.senicare.service;

import org.springframework.http.ResponseEntity;

import com.project.senicare.dto.request.auth.IdCheckRequestDto;
import com.project.senicare.dto.request.auth.SignInRequestDto;
import com.project.senicare.dto.request.auth.SignUpRequestDto;
import com.project.senicare.dto.request.auth.TelAuthCheckDto;
import com.project.senicare.dto.request.auth.TelAuthRequestDto;
import com.project.senicare.dto.response.ResponseDto;
import com.project.senicare.dto.response.auth.SignInResponseDto;

public interface AuthService {

  ResponseEntity<ResponseDto> idCheck(IdCheckRequestDto dto);

  ResponseEntity<ResponseDto> telAuth(TelAuthRequestDto dto);

  ResponseEntity<ResponseDto> telAuthCheck(TelAuthCheckDto dto);

  ResponseEntity<ResponseDto> signUp(SignUpRequestDto dto);

  ResponseEntity<? super SignInResponseDto> signIn(SignInRequestDto dto);
}
