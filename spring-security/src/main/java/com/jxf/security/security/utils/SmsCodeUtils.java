package com.jxf.security.security.utils;


import java.util.Random;

public class SmsCodeUtils {

    public static String generateCode(){
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < 4; i++) {
            stringBuilder.append(new Random().nextInt(10));
        }
        return stringBuilder.toString();
    }
}

