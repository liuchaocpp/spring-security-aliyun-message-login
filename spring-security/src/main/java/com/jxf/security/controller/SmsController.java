package com.jxf.security.controller;


import com.jxf.security.security.utils.R;
import com.jxf.security.service.SmsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/sms")
@CrossOrigin
public class SmsController {


    @Autowired
    private SmsService smsService;

    @GetMapping("/code/{mobile}")
    public R sendMessage(@PathVariable String mobile){
        smsService.sendMessage(mobile);
        return R.success();
    }
}
