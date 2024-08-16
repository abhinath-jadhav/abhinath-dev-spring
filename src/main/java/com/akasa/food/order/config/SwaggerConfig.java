package com.akasa.food.order.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.VendorExtension;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;


import static springfox.documentation.builders.PathSelectors.regex;

import java.util.ArrayList;

@Configuration
//@EnableSwagger2
@EnableWebMvc
public class SwaggerConfig {

    @Bean
    public Docket swagger() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(metaInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.akasa.food.order"))
                .paths(PathSelectors.any())
                .build();

    }

    private ApiInfo metaInfo() {

        ApiInfo apiInfo = new ApiInfo(
                "Akasa Food Order API's",
                "Api to handle backed of Akasa Air food order app",
                "1.0",
                "Terms of Service",
                new Contact("Abhinath Jadhav", "",
                        "abhinath.jadhav95@gmail.com"),
                "Apache License Version 2.0",
                "https://www.apache.org/licesen.html", new ArrayList<VendorExtension>()
        );

        return apiInfo;
    }
}