package com.jxf.security.security.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.jxf.security.security.entity.UserEntity;

public interface UserEntityService extends IService<UserEntity> {
    UserEntity getUserEntityByMobile(String mobile);
}
