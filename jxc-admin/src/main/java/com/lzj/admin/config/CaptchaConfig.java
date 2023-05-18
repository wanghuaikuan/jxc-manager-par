package com.lzj.admin.config;

import com.google.code.kaptcha.impl.DefaultKaptcha;
import com.google.code.kaptcha.util.Config;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.beans.factory.annotation.Value;

import java.util.Properties;

@Configuration
@PropertySource(value = {"classpath:captcha.properties"})
public class CaptchaConfig {

    @Value("${kaptcha.border}")
    private String border;
    @Value("${kaptcha.border.color}")
    private String borderColor;
    @Value("${kaptcha.textproducer.font.size}")
    private String fontSize;
    @Value("${kaptcha.textproducer.font.color}")
    private String fontColor;
    @Value("${kaptcha.img.width}")
    private String imageWidth;
    @Value("${kaptcha.img.height}")
    private String imageHeight;
    @Value("${kaptcha.session.key}")
    private String sessionKey;
    @Value("${kaptcha.textproducer.char.length}")
    private String charLength;
    @Value("${kaptcha.textproducer.font.names}")
    private String fontNames;
    /**
     * 自定义验证码生成器
     */
    @Bean(name = "captchaProducer")
    public DefaultKaptcha getKaptchaBean(){
        DefaultKaptcha defaultKaptcha=new DefaultKaptcha();
        Properties properties=new Properties();
        properties.setProperty("kaptcha.border",border);
        properties.setProperty("kaptcha.border.color",borderColor);
        properties.setProperty("kaptcha.textproducer.font.size",fontSize);
        properties.setProperty("kaptcha.textproducer.font.color",fontColor);
        properties.setProperty("kaptcha.img.width",imageWidth);
        properties.setProperty("kkaptcha.img.height",imageHeight);
        properties.setProperty("kaptcha.session.key",sessionKey);
        properties.setProperty("kaptcha.textproducer.char.length",charLength);
        properties.setProperty("kaptcha.textproducer.font.names",fontNames);
        defaultKaptcha.setConfig(new Config(properties));
        return defaultKaptcha;
    }
}
