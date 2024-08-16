package com.akasa.food.order.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springdoc.core.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Configuration
//@EnableSwagger2
@EnableWebMvc
public class SwaggerConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Akasa Food Order API's")
                        .version("1.0.0")
                        .description("Api to handle backed of Akasa Air food order app")
                        .contact(getContact())

                        .license(new License().name("pache License Version 2.0")
                                .url("https://www.apache.org/licesen.html") ))

                .addSecurityItem(new SecurityRequirement().addList("bearer-key"))
                .components(new io.swagger.v3.oas.models.Components()
                        .addSecuritySchemes("bearer-key", new SecurityScheme()
                                .name("bearer-key")
                                .type(SecurityScheme.Type.HTTP)
                                .scheme("bearer")
                                .bearerFormat("JWT")));
    }

   Contact getContact(){
       Contact contact = new Contact();
       contact.setEmail("abhinath.jadhav95@gmail.com");
       contact.setName("Abhinath Jadhav");
       return contact;
   }


}