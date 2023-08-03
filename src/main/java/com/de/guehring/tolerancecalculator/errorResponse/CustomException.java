package com.de.guehring.tolerancecalculator.errorResponse;

public class CustomException extends RuntimeException{
    private Integer errorCode;
    public CustomException(Integer errorCode, String message){
        super(message);
        this.errorCode=errorCode;
    }

    public Integer getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(Integer errorCode) {
        this.errorCode = errorCode;
    }
}
