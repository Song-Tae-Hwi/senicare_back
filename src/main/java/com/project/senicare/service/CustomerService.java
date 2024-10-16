package com.project.senicare.service;

import org.springframework.http.ResponseEntity;

import com.project.senicare.dto.request.customer.PatchCustomerRequestDto;
import com.project.senicare.dto.request.customer.PostCareRecordRequestDto;
import com.project.senicare.dto.request.customer.PostCustomerRequestDto;
import com.project.senicare.dto.response.customer.GetCustomerListResponseDto;
import com.project.senicare.dto.response.customer.GetCustomerResponseDto;
import com.project.senicare.dto.response.customer.GetCareRecordListResponseDto;
import com.project.senicare.dto.response.ResponseDto;

public interface CustomerService {

  ResponseEntity<ResponseDto> postCustomer(PostCustomerRequestDto dto);

  ResponseEntity<? super GetCustomerListResponseDto> getCustomerList();

  ResponseEntity<? super GetCustomerResponseDto> getCustomer(Integer customerNumber);

  ResponseEntity<ResponseDto> patchCustomer(PatchCustomerRequestDto dto, Integer customerNumber, String userId);

  ResponseEntity<ResponseDto> deleteCustomer(Integer customerNumber, String userId);

  ResponseEntity<ResponseDto> postCareRecord(PostCareRecordRequestDto dto, Integer customerNumber, String userId);

  ResponseEntity<? super GetCareRecordListResponseDto> getCareRecordList(Integer customerNumber);

}
