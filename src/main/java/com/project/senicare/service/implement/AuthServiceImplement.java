package com.project.senicare.service.implement;

import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.project.senicare.common.util.AuthNumberCreator;
import com.project.senicare.dto.request.auth.IdCheckRequestDto;
import com.project.senicare.dto.request.auth.SignInRequestDto;
import com.project.senicare.dto.request.auth.SignUpRequestDto;
import com.project.senicare.dto.request.auth.TelAuthCheckDto;
import com.project.senicare.dto.request.auth.TelAuthRequestDto;
import com.project.senicare.dto.response.ResponseDto;
import com.project.senicare.dto.response.auth.SignInResponseDto;
import com.project.senicare.entity.NurseEntity;
import com.project.senicare.entity.TelAuthNumberEntity;
import com.project.senicare.provider.JwtProvider;
import com.project.senicare.provider.SmsProvider;
import com.project.senicare.repository.NurseRepository;
import com.project.senicare.repository.TelAuthNumberRepository;
import com.project.senicare.service.AuthService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthServiceImplement implements AuthService {

  private final SmsProvider smsProvider;
  private final JwtProvider jwtProvider;

  private final NurseRepository nurseRepository;
  private final TelAuthNumberRepository telAuthNumberRepository;

  private PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

  @Override
  public ResponseEntity<ResponseDto> idCheck(IdCheckRequestDto dto) {

    String userId = dto.getUserId();
    try {

      boolean isExistedId = nurseRepository.existsByUserId(userId);
      if (isExistedId)
        return ResponseDto.duplicatedUserId();
    } catch (Exception exception) {
      exception.printStackTrace();
      return ResponseDto.databaseError();
    }

    return ResponseDto.success();
  }

  @Override
  public ResponseEntity<ResponseDto> telAuth(TelAuthRequestDto dto) {

    String telNumber = dto.getTelNumber();

    try {

      boolean isExistedTelNumber = nurseRepository.existsByTelNumber(telNumber);
      if (isExistedTelNumber)
        return ResponseDto.duplicatedTelNumber();

    } catch (Exception exception) {
      exception.printStackTrace();
      return ResponseDto.databaseError();
    }

    String authNumber = AuthNumberCreator.number4();

    boolean isSendSuccess = smsProvider.sendMessage(telNumber, authNumber);
    if (!isSendSuccess)
      return ResponseDto.messageSendFail();

    try {

      TelAuthNumberEntity telAuthNumberEntity = new TelAuthNumberEntity(telNumber, authNumber);
      telAuthNumberRepository.save(telAuthNumberEntity);

    } catch (Exception exception) {
      exception.printStackTrace();
      return ResponseDto.databaseError();
    }
    return ResponseDto.success();
  }

  @Override
  public ResponseEntity<ResponseDto> telAuthCheck(TelAuthCheckDto dto) {

    String telNumber = dto.getTelNumber();
    String authNumber = dto.getAuthNumber();

    try {

      boolean isMatched = telAuthNumberRepository.existsByTelNumberAndAuthNumber(telNumber, authNumber);
      if (!isMatched)
        return ResponseDto.telAuthFail();

    } catch (Exception exception) {
      exception.printStackTrace();
      return ResponseDto.databaseError();
    }
    return ResponseDto.success();
  }

  @Override
  public ResponseEntity<ResponseDto> signUp(SignUpRequestDto dto) {

    String userId = dto.getUserId();
    String telNumber = dto.getTelNumber();
    String authNumber = dto.getAuthNumber();
    String password = dto.getPassword();

    try {
      boolean isExistedId = nurseRepository.existsByUserId(userId);
      if (isExistedId)
        return ResponseDto.duplicatedUserId();

      boolean isExistedTelNumber = nurseRepository.existsByTelNumber(telNumber);
      if (isExistedTelNumber)
        return ResponseDto.duplicatedTelNumber();

      boolean isMatched = telAuthNumberRepository.existsByTelNumberAndAuthNumber(telNumber, authNumber);
      if (!isMatched)
        return ResponseDto.telAuthFail();

      String encodedPassword = passwordEncoder.encode(password); // 패스워드 암호화
      dto.setPassword(encodedPassword); // 암호화한 패스워드를 dto에 넘김

      NurseEntity nurseEntity = new NurseEntity(dto);
      nurseRepository.save(nurseEntity);

    } catch (Exception exception) {
      exception.printStackTrace();
      return ResponseDto.databaseError();
    }

    return ResponseDto.success();
  }

  @Override
  public ResponseEntity<? super SignInResponseDto> signIn(SignInRequestDto dto) {

    String userId = dto.getUserId();
    String password = dto.getPassword();

    String accessToken = null;

    try {

      NurseEntity nurseEntity = nurseRepository.findByUserId(userId);
      if (nurseEntity == null)
        return ResponseDto.signInFail();

      String encodedPassword = nurseEntity.getPassword();
      boolean isMatched = passwordEncoder.matches(password, encodedPassword);
      if (!isMatched)
        return ResponseDto.signInFail();

      accessToken = jwtProvider.create(userId);
      if (accessToken == null)
        return ResponseDto.tokenCreateFail();

    } catch (Exception exception) {
      exception.printStackTrace();
      return ResponseDto.databaseError();
    }

    return SignInResponseDto.success(accessToken);
  }

}
