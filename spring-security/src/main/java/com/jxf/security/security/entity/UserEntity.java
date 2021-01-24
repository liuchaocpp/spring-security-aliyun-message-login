package com.jxf.security.security.entity;


import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("user")
public class UserEntity {
    private Long userId;
    private String username;
    private String password;
    private String mobile;
}
