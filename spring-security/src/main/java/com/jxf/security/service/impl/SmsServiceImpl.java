package com.jxf.security.service.impl;

import com.aliyuncs.CommonRequest;
import com.aliyuncs.CommonResponse;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.http.MethodType;
import com.aliyuncs.profile.DefaultProfile;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jxf.security.security.utils.CustomException;
import com.jxf.security.security.utils.SmsCodeUtils;
import com.jxf.security.service.SmsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.concurrent.TimeUnit;

@Service
public class SmsServiceImpl implements SmsService {

    @Value("${sms.aliyun.accessKeyId}")
    private  String accessKeyId;

    @Value("${sms.aliyun.accessSecret}")
    private String accessSecret;

    @Autowired
    private StringRedisTemplate redisTemplate;

    @Override
    public void sendMessage(String phone) {
        DefaultProfile profile = DefaultProfile.getProfile("cn-hangzhou", accessKeyId, accessSecret);
        IAcsClient client = new DefaultAcsClient(profile);

        CommonRequest request = new CommonRequest();
        request.setSysMethod(MethodType.POST);
        request.setSysDomain("dysmsapi.aliyuncs.com");
        request.setSysVersion("2017-05-25");
        request.setSysAction("SendSms");
        request.putQueryParameter("RegionId", "cn-hangzhou");
        request.putQueryParameter("PhoneNumbers", phone); //获得参数中的电话号码
        request.putQueryParameter("SignName", "Nast自学java网站");//这个需要获取自己的签名
        request.putQueryParameter("TemplateCode", "SMS_200721365");//这个需要获取自己的模板code
        //获取code
        String code = SmsCodeUtils.generateCode();
        HashMap<Object, Object> codeMap = new HashMap<>();
        codeMap.put("code",code);
        try {
            String templateParam = new ObjectMapper().writeValueAsString(codeMap);
            System.out.println(templateParam);
            request.putQueryParameter("TemplateParam", templateParam);//这个需要获取自己的模板code
            CommonResponse response = client.getCommonResponse(request);
            System.out.println(response.getData());
            redisTemplate.opsForValue().set(phone,code,5, TimeUnit.HOURS);
        } catch (Exception e) {
            throw  CustomException.builder().code(400).msg("短信发送失败,请重试!").build();
        }
    }
}
