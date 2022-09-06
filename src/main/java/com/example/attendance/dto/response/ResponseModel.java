package com.example.attendance.dto.response;

public class ResponseModel {

    private String code;

    private String message;

    private Object object;

    public ResponseModel(Response response, Object object) {
        this.code = response.getResponseCode();
        this.message = response.getResponseMessage();
        this.object = object;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getObject() {
        return object;
    }

    public void setObject(Object object) {
        this.object = object;
    }

}
