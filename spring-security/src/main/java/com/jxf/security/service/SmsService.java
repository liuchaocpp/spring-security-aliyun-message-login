package com.jxf.security.service;

import com.baomidou.mybatisplus.extension.service.IService;

public interface SmsService  {
    void sendMessage(String mobile);
}
