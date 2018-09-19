package com.prueba.administradortarea.exception;


public class ApiException  {

    private static final long serialVersionUID = 1L;

    private String code = "internal_error";
    private String description;

    public ApiException() {
        this("internal_error", "Internal server error");
    }


    public ApiException(String code, String description) {
        this.code = code;
        this.description = description;
    }

    public ApiException(String description) {
        this.description = description;
    }

    public ApiException(String description, Throwable cause) {
        this.description = description;
    }

    public ApiException(String code, String description, Integer statusCode) {
        this.code = code;
        this.description = description;
    }

    public ApiException(String code, String description, Throwable cause) {
        this.code = code;
        this.description = description;
    }

    public ApiException(String code, String description, Integer statusCode, Throwable cause) {
        this.code = code;
        this.description = description;
    }

    public String getCode() {
        return code;
    }

    public String getDescription() {
        return description;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public void setDescription(String description) {
        this.description = description;
    }

}
