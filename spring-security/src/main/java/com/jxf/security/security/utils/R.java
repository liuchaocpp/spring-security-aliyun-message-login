package com.jxf.security.security.utils;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashMap;
import java.util.Map;

@Data
public class R<T> {
    private Integer code;
    private String message;
    private Map<String,T> data=new HashMap<>();

    public static R success(){
        R r = new R();
        r.setCode(200);
        r.setMessage("success");
        return r;
    }

    public static R error(){
        R r = new R();
        r.setCode(400);
        r.setMessage("error");
        return r;
    }

    public static R success(Integer code,String msg){
        R r = new R();
        r.setCode(code);
        r.setMessage(msg);
        return r;
    }

    public static R error(Integer code,String msg){
        R r = new R();
        r.setCode(code);
        r.setMessage(msg);
        return r;
    }

    public  R put(String key,T val){
        this.data.put(key,val);
        return this;
    }
}
