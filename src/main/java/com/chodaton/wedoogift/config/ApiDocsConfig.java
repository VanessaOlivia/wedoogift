package com.chodaton.wedoogift.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApiDocsConfig {

    @Bean
    OpenAPI openApi(){
        return new OpenAPI().info(new Info().title("WEDOOGIFT")
                .contact(new Contact().email("vanessachodaton@yahoo.fr").name("CHODATON Vanessa"))
                .version("1")
                .summary("GLADY REST server")
                .description(""));

    }


}