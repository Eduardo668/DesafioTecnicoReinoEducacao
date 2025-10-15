package br.com.reinoeducacao.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI apiInfo() {
        return new OpenAPI().info(new Info()
                .title("Gerenciamento de milhas API - Reino Mundo Educação")
                .version("v1.0.0")
                .description("Documentação da API Gerenciamento de milhas")
                .contact(new Contact().name("Eduardo Paixão").email("eduardosehn20@gmail.com")));
    }
}

