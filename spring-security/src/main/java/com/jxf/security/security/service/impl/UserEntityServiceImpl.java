package com.jxf.security.security.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jxf.security.security.dao.UserEntityDao;
import com.jxf.security.security.entity.UserEntity;
import com.jxf.security.security.service.UserEntityService;
import org.springframework.stereotype.Service;

@Service
public class UserEntityServiceImpl extends ServiceImpl<UserEntityDao, UserEntity> implements UserEntityService {

    @Override
    public UserEntity getUserEntityByMobile(String mobile) {
       return baseMapper.selectOne(new QueryWrapper<UserEntity>().eq("mobile",mobile));
    }
}
