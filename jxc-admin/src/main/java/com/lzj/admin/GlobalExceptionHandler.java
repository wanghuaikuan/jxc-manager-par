package com.lzj.admin;

import com.lzj.admin.exceptions.ParamsException;
import com.lzj.admin.model.RespBean;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;



@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(AccessDeniedException.class)
    public String accessDeniedException(AccessDeniedException e){
        System.out.println("accessDeniedException");
        return "403";
    }
    @ExceptionHandler(ParamsException.class)
    @ResponseBody
    public RespBean paramsExceptionHandler(ParamsException e){
        System.out.println("参数异常");
        return  RespBean.error(e.getMsg());
    }
    @ExceptionHandler(Exception.class)
    @ResponseBody
    public RespBean exceptionHandler(Exception e){
       e.printStackTrace();
        return  RespBean.error(e.getMessage());
    }

}
