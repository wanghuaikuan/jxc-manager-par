package com.lzj.admin.model;



import java.time.LocalDateTime;

public class CaptchaImageModel {



    private String code;
    private LocalDateTime expireTime;

    public CaptchaImageModel(String code,int expireAfterSections) {

        this.code = code;
        this.expireTime = LocalDateTime.now().plusSeconds(expireAfterSections);
    }
    public String getCode(){
        return code;
    }

    public boolean isExpired() {
        return LocalDateTime.now().isAfter(expireTime);
    }

}
