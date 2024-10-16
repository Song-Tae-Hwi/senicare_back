package com.project.senicare.service;

import org.springframework.http.ResponseEntity;

import com.project.senicare.dto.request.nurse.PatchNurseReqeustDto;
import com.project.senicare.dto.response.ResponseDto;
import com.project.senicare.dto.response.nurse.GetChargedCustomerResponseDto;
import com.project.senicare.dto.response.nurse.GetNurseListResponseDto;
import com.project.senicare.dto.response.nurse.GetNurseResponseDto;
import com.project.senicare.dto.response.nurse.GetSignInResponseDto;

public interface NurseService {

  ResponseEntity<? super GetNurseListResponseDto> getNureseList();

  ResponseEntity<? super GetNurseResponseDto> getNurse(String userId);

  ResponseEntity<? super GetSignInResponseDto> getSignIn(String userId);

  ResponseEntity<ResponseDto> patchNurse(PatchNurseReqeustDto dto, String userId);

  ResponseEntity<? super GetChargedCustomerResponseDto> getChargedCustomer(String nurseId);
}
