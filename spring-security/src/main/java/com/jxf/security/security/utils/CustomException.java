package com.jxf.security.security.utils;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CustomException extends RuntimeException {

    private Integer code;
    private String msg;
}
