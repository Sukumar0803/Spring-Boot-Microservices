package com.microservices.customer.config;

import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {
    @Bean
    public OpenAPI springCustomerOpenAPI() {
        return new OpenAPI()
                .info(new Info().title("Customer Service API")
                        .description("Customer Service API's List")
                        .version("v0.0.1")
                        .contact(new Contact().name("Sukumar").email("kalpamsukumar@gmail.com"))
                        .license(new License().name("Apache 2.0").url("https://springdoc.org")))
                .externalDocs(new ExternalDocumentation().description("Customer Service REST API Documentation")
                        .url("https://github.com/Sukumar0803"));
    }
}
