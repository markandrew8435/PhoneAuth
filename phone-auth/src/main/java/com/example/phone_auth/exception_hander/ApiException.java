package com.example.phone_auth.exception_hander;

import lombok.Setter;

@Setter
public class ApiException extends Exception {
    public String message;
    public ApiException(String string) {
        super(string);
        this.message = string;
    }
}
