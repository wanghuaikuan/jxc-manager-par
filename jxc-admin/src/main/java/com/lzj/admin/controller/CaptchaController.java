package com.lzj.admin.controller;

import com.google.code.kaptcha.impl.DefaultKaptcha;
import com.lzj.admin.model.CaptchaImageModel;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.image.BufferedImage;

@RestController
public class CaptchaController {
    @Resource
    public DefaultKaptcha defaultKaptcha;
    @RequestMapping(value = "/image",method = RequestMethod.GET)
     public void kaptcha(HttpSession session, HttpServletResponse response) throws Exception{
         response.setDateHeader("Expires",0);
         response.setHeader("Cache-Control","mo-store,no-cache,must-revalidate");
         response.addHeader("Cache-Control","post-check=0,pre-check=0");
         response.setHeader("Pragma","no-cache");
         response.setContentType("/image/jpeg");



         String capText=defaultKaptcha.createText();
         session.setAttribute("captcha_key",new CaptchaImageModel(capText,2*60));
        ServletOutputStream out=response.getOutputStream();
        BufferedImage bufferedImage=defaultKaptcha.createImage(capText);
        ImageIO.write(bufferedImage,"jpg",out);
        out.flush();
     }
}
