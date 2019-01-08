package com.shtel.secure;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import springfox.documentation.swagger2.annotations.EnableSwagger2;
import tk.mybatis.spring.annotation.MapperScan;

@SpringBootApplication
@ServletComponentScan
@EnableSwagger2
@MapperScan("com.shtel.secure.*.*.model.mapper")
public class SecureApplication extends SpringBootServletInitializer {

    public static void main(String[] args) {
        SpringApplication.run(SecureApplication.class, args);
    }

    /**
     * javaee应用服务器配置，使用Tomcat加载jsp
     */
    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return builder.sources(this.getClass());
    }
}

