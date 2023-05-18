package com.lzj.generator;

import com.baomidou.mybatisplus.core.exceptions.MybatisPlusException;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.generator.FastAutoGenerator;
import com.baomidou.mybatisplus.generator.config.OutputFile;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;
import java.util.Collections;
import java.util.Scanner;

/**
 * 乐字节  踏实教育 用心服务
 *
 * @author
 * @version 1.0
 */
public class CodeGenerator {
    /**
     * <p>
     * 读取控制台内容
     * </p>
     */
    public static String scanner(String tip) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("请输入" + tip + "：");
        if (scanner.hasNext()) {
            String ipt = scanner.next();
            if (StringUtils.isNotBlank(ipt)) {
                return ipt;
            }
        }
        throw new MybatisPlusException("请输入正确的" + tip + "！");
    }

    public static void main(String[] args) {


        FastAutoGenerator.create("jdbc:mysql://localhost:3306/lzj_jxc?useUnicode=true&characterEncoding=UTF-8&serverTimezone=Asia/Shanghai","root","root")
                .globalConfig(builder -> builder.outputDir(System.getProperty("user.dir")+"/jxc-admin/src/main/java")
                        .commentDate("yyyy-MM-dd hh:mm:ss")
                        .author("王怀宽")
                        .enableSwagger()
                        .disableOpenDir())
                .packageConfig(builder -> builder.parent("com.lzj.admin")
                        .entity("pojo")
                        .pathInfo(Collections.singletonMap(OutputFile.xml,System.getProperty("user.dir")+"/jxc-admin/src/main/resources/mapper")))
                .strategyConfig(builder -> {
                    builder.addInclude(scanner("表名，用多个英文逗号分割").split(","))
                            .addTablePrefix("t_")
                            .entityBuilder()
                            .enableLombok()
                            .enableTableFieldAnnotation()
                            .controllerBuilder()
                            .serviceBuilder()
                            .mapperBuilder()
                            .enableBaseResultMap()
                            .enableBaseColumnList()
                            .build();
                })
                .templateEngine(new FreemarkerTemplateEngine())
                .execute();

    }

}
