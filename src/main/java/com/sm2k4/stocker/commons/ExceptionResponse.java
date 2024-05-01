package com.sm2k4.stocker.commons;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ExceptionResponse {
    private String message;
    private Integer errorCode;
    private String status;
}
