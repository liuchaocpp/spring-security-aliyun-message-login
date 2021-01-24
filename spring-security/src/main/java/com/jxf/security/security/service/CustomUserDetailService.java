package com.jxf.security.security.service;


import com.jxf.security.security.entity.CustomUserDetails;
import com.jxf.security.security.entity.UserEntity;
import com.jxf.security.security.utils.CustomException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;



@Component
public class CustomUserDetailService implements UserDetailsService {


    @Autowired
    private UserEntityService userEntityService;
    
    @Override
    public UserDetails loadUserByUsername(String mobile) throws UsernameNotFoundException {
        UserEntity userEntity = userEntityService.getUserEntityByMobile(mobile);
        if(userEntity==null){
            throw new UsernameNotFoundException("账号不存在");
        }
        CustomUserDetails customUserDetails = new CustomUserDetails();
        customUserDetails.setUserEntity(userEntity);
        return customUserDetails;
    }
}
