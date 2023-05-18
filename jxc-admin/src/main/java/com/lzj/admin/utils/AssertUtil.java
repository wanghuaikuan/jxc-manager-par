package com.lzj.admin.utils;
import com.lzj.admin.exceptions.ParamsException;

public class AssertUtil {


    public  static void isTrue(Boolean flag,String msg) throws ParamsException {
        if(flag){
            throw  new ParamsException(msg);
        }
    }



}

