package com.tju.practical.exception;

/**
 * Created by Administrator on 2016/8/20.
 */
public class ErrorRet {

    private String message;
    private int statusCode;

    public ErrorRet(int statusCode, String message) {
        this.statusCode = statusCode;
        this.message = message;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    public String getMessage() {

        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public static ErrorRet create(int statusCode, String message) {
        return new ErrorRet(statusCode, message);
    }
}
