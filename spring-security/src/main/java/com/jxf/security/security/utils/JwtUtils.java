package com.jxf.security.security.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jxf.security.security.entity.UserEntity;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import javax.jws.soap.SOAPBinding;
import java.util.Date;
import java.util.HashMap;

public class JwtUtils {

    private static final String SUBJECT="admin";
    private static final String SECRET="secret";
    private static final Integer EXPIRE_TIME=1000*60*60;

    public static String generateToken(UserEntity userEntity){
        String userString=null;
        try {
            userString=new ObjectMapper().writeValueAsString(userEntity);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return Jwts.builder().setSubject(SUBJECT)
                .claim("user",userString)
                .setIssuedAt(new Date())
                .setExpiration(new Date(new Date().getTime()+EXPIRE_TIME))
                .signWith(SignatureAlgorithm.HS256,SECRET)
                .compact();
    }

    public static UserEntity parseToken(String token){
        String userString = (String) Jwts.parser().setSigningKey(SECRET).parseClaimsJws(token).getBody().get("user");
        UserEntity userEntity=null;
        try {
            userEntity = new ObjectMapper().readValue(userString, UserEntity.class);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return userEntity;
    }
}
