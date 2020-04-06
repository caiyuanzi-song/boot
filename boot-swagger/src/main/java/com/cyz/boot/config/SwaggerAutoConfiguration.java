package com.cyz.boot.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @Author:cyz
 * @Date:2020/4/6 9:58
 * @Description:
 */
@Configuration
@EnableSwagger2
public class SwaggerAutoConfiguration {


    /**
     * 创建API应用
     * apiInfo增加API相关信息
     * 通过select()函数返回一个ApiSelectorBuilder实例，用来控制哪些接口暴露给Swagger来展现
     * 本例采用指定扫描的包路径来定义指定要建立API的目录
     * @return
     */
    @Bean
    public Docket createRestApi(){
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                //控制暴露出去的路径下的实例
                //如果某个接口不想暴露，可以使用一下注释
                //@ApiIgnore 这样，该接口就不会暴露在swagger的页面下
                .apis(RequestHandlerSelectors.basePackage("com.cyz.boot.controller"))
                .paths(PathSelectors.any())
                .build();
    }

    private ApiInfo apiInfo(){
        return new ApiInfoBuilder()
                .title("接口文档")
                .version("1.0.0-SNAPSHOT")
                .build();
    }
}
