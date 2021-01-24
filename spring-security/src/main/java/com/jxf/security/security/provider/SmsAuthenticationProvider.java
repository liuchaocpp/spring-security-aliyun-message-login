package com.jxf.security.security.provider;

import com.jxf.security.security.service.CustomUserDetailService;
import com.jxf.security.security.utils.SmsAuthenticationToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import javax.annotation.PostConstruct;


@Component
public class SmsAuthenticationProvider implements AuthenticationProvider {

    @Autowired
    private CustomUserDetailService customUserDetailService;

    @Autowired
    private StringRedisTemplate redisTemplate;


    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        SmsAuthenticationToken authenticationToken = (SmsAuthenticationToken) authentication;
        String mobile = (String) authenticationToken.getPrincipal();
        String code = authenticationToken.getCode();
        checkSmsCode(mobile,code);

        UserDetails userDetails = customUserDetailService.loadUserByUsername(mobile);

        SmsAuthenticationToken authenticationResult = new SmsAuthenticationToken(userDetails, userDetails.getAuthorities());
        authenticationResult.setDetails(authenticationToken.getDetails());
        return authenticationResult;
    }

    private void checkSmsCode(String mobile, String code) {

        System.out.println("获取code");

        System.out.println("打印code========>"+code);
        String smsCode = (String) redisTemplate.opsForValue().get(mobile);
        if(smsCode == null) {
            throw new BadCredentialsException("验证码已经过期");
        }
        if(!code.equals(smsCode)) {
            throw new BadCredentialsException("输入的验证码不正确");
        }

    }


    @Override
    public boolean supports(Class<?> authentication) {
        return (SmsAuthenticationToken.class.isAssignableFrom(authentication));
    }
}
