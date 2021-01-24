package com.jxf.security.security.filter;


import com.jxf.security.security.entity.UserEntity;
import com.jxf.security.security.utils.JwtUtils;
import com.jxf.security.security.utils.SmsAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Component
public class TokenAuthenticationFilter extends OncePerRequestFilter {


    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String token = request.getHeader("token");
        System.out.println(token);
        if(token!=null){
            //如果有token 说明登陆过 直接生成一个authentication
            UserEntity userEntity = JwtUtils.parseToken(token);
            //从token中获取用户 通过用户可以查询权限列表,放入这个集合;
            //TODO
            List<? extends GrantedAuthority> authorities=new ArrayList<>();
            SmsAuthenticationToken smsAuthenticationToken = new SmsAuthenticationToken(userEntity, authorities);
            //然后放入SecurityContext中 后续filter 可以直接获得
            SecurityContextHolder.getContext().setAuthentication(smsAuthenticationToken);
        }
        filterChain.doFilter(request,response);
    }
}
