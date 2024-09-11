package com.project.senicare.common.util;

import java.util.*;

public class AuthNumberCreator {

  // 0-9의 4자리 인증번호
  public static String number4() {

    String authNumber = "";

    Random random = new Random();
    for (int count = 0; count < 4; count++) // 랜덤 4개의 숫자 붙이기
      authNumber += random.nextInt(10);

    return authNumber;
  }
}
