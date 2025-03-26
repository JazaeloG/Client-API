package com.generation.clientapi.config;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("API de gestion de Clientes")
                        .version("1.0")
                        .description("CRUD de clientes")
                        .contact(new Contact()
                                .name("Jazael Galindo")
                                .email("jazaelgalindo.contact@gmail.com")));
    }
}