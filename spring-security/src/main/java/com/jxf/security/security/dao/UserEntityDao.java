package com.jxf.security.security.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.jxf.security.security.entity.UserEntity;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserEntityDao extends BaseMapper<UserEntity> {
}
