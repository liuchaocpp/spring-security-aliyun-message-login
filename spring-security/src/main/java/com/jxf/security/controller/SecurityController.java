package com.jxf.security.controller;

import com.jxf.security.security.utils.R;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SecurityController {

    @GetMapping("/success")
    public R success(){
        System.out.println("success");
        return R.success().put("success","success");
    }

    @GetMapping("/error")
    public R error(){
        System.out.println("error");
        return R.error().put("error","error");
    }
}
