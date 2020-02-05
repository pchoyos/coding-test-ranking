package com.idealista.infrastructure.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@Slf4j
@Component
public class ResponseManager {
  
    @Autowired
    private ObjectMapper objectMapper;
 
    public <T> CustomResponse<T> response(ResponseCode code, String descripcion, T data ){
        CustomResponse<T> customResponse = new CustomResponse<>();
        customResponse.setCode(code.getCode());
        customResponse.setDescription(descripcion);
        customResponse.setData(data);
        return customResponse;
    }

    public <T> CustomResponse<T> response( T data ){
        return response(ResponseCode.OK_CODE,ResponseCode.OK_CODE.getDescription(),data);
    }

    public <T> CustomResponse<T> response( ResponseCode code ){
        return response(code,code.getDescription(),null);
    }

    /********************
     *     PRINT RAW    *
     ********************/
    public <T> void print(HttpServletResponse httpResponse, ResponseCode code, String description, T data) {
        CustomResponse r = response(code,description,data);
        printJsonResponse(httpResponse,r);
    }

    public <T> void print(HttpServletResponse httpResponse, T data) {
        CustomResponse r = response(data);
        printJsonResponse(httpResponse,r);
    }

    public <T> void print(HttpServletResponse httpResponse, ResponseCode code) {
        CustomResponse r = response(code);
        printJsonResponse(httpResponse,r);
    }

    private void printJsonResponse(HttpServletResponse httpResponse, CustomResponse r){
        try{
            httpResponse.setHeader("Content-Type", "application/json; charset=UTF-8");
            PrintWriter writer = httpResponse.getWriter();
            objectMapper.writeValue(writer, r);
            writer.flush();
        }catch (IOException e){
            log.error("printKoResponse() devolvio una IOExcepcion - " , e);
        }
    }

}







