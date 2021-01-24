package com.jxf.security.security.handler;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.jxf.security.security.entity.CustomUserDetails;
import com.jxf.security.security.entity.UserEntity;
import com.jxf.security.security.utils.JwtUtils;
import com.jxf.security.security.utils.R;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@Component
public class CustomSuccessHandler implements AuthenticationSuccessHandler {

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();
        UserEntity userEntity = userDetails.getUserEntity();
        String token = JwtUtils.generateToken(userEntity);
        String result = new ObjectMapper().writeValueAsString(R.success().put("token",token));
        System.out.println(token);
        response.setStatus(HttpStatus.OK.value());
        response.setContentType("application/json;charset=UTF-8");
        PrintWriter writer = response.getWriter();
        writer.write(result);
        writer.flush();
        writer.close();
    }
}
