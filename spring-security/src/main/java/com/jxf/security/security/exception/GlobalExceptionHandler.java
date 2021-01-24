package com.jxf.security.security.exception;


import com.jxf.security.security.utils.CustomException;
import com.jxf.security.security.utils.R;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    public R commonException(Exception e){
        return R.error(500,"操作出现异常");
    }


    @ExceptionHandler(CustomException.class)
    public R customException(CustomException e){
        return R.error(e.getCode(),e.getMsg());
    }
}
