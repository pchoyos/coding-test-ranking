package com.idealista.infrastructure.utils;

import java.io.Serializable;

public class CustomResponse<T> implements Serializable {

    private int code;
    private String description;
    private T data = null;

    public int getCode() { return code; }
    public void setCode(int code) { this.code = code; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public T getData() { return data; }
    public void setData(T data) { this.data = data; }

}
