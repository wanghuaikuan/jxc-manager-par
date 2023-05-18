package com.lzj.admin.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfig;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;

import javax.annotation.PostConstruct;
import java.util.Arrays;
import java.util.List;

public class ClassPathIdsLoader {



    @Autowired
    private FreeMarkerConfigurer freeMarkerConfigurer;

    private static final String SECURITY_TLD = "security.tld";
   final List<String> classPathTlds;
    public ClassPathIdsLoader(String...classPathTlds){
        super();
        if(classPathTlds==null||classPathTlds.length<=0){
            this.classPathTlds= Arrays.asList(SECURITY_TLD);
        }else {
            this.classPathTlds=Arrays.asList(classPathTlds);
        }
    }
    @PostConstruct
    public void loadClassPathTlds(){
        freeMarkerConfigurer.getTaglibFactory().setClasspathTlds(classPathTlds);
    }

}
