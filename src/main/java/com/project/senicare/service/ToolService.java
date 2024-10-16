package com.project.senicare.service;

import org.springframework.http.ResponseEntity;

import com.project.senicare.dto.request.tool.PatchToolRequestDto;
import com.project.senicare.dto.request.tool.PostToolRequestDto;
import com.project.senicare.dto.response.ResponseDto;
import com.project.senicare.dto.response.tool.GetToolListResponseDto;
import com.project.senicare.dto.response.tool.GetToolResponseDto;

public interface ToolService {

  ResponseEntity<ResponseDto> postTool(PostToolRequestDto dto);

  ResponseEntity<? super GetToolListResponseDto> getToolList();

  ResponseEntity<? super GetToolResponseDto> getTool(Integer toolNumber);

  ResponseEntity<ResponseDto> patchTool(Integer toolNumber, PatchToolRequestDto dto);

  ResponseEntity<ResponseDto> deleteTool(Integer toolNumber);

}