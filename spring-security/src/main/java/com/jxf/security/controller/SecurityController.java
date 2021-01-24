package com.jxf.security.controller;

import com.jxf.security.security.utils.R;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SecurityController {

    @GetMapping("/success")
    public R successLogin(){
        System.out.println("success");
        return R.success().put("success","success");
    }
}
