package com.project.senicare.dto.request.auth;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

// 아이디 중복확인 Requet Body Dto

@Getter
@Setter
@NoArgsConstructor
public class IdCheckRequestDto {

  @NotBlank
  private String userId;
}
