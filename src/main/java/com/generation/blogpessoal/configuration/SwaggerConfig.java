package com.generation.blogpessoal.configuration;

import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.responses.ApiResponse;
import io.swagger.v3.oas.models.responses.ApiResponses;
import org.springdoc.core.customizers.OpenApiCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {
    @Bean
    public OpenAPI springBlogPessoalOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Projeto Blog Pessoal")
                        .description("Projeto Blog Pessoal - Olavo Pessôa")
                        .version("v0.0.1")
                        .license(new License()
                                .name("Olavo Pessôa")
                                .url("https://www.linkedin.com/in/olavo-pess%C3%B4a/"))
                        .contact(new Contact()
                                .name("Olavo Pessôa")
                                .url("https://www.linkedin.com/in/olavo-pess%C3%B4a/")
                                .email("olavotp@gmail.com")))
                .externalDocs(new ExternalDocumentation().description("Github").url("https://github.com/Olavo5"));
    }

    private ApiResponse createApiResponse(String message) {
        return new ApiResponse().description(message);
    }

    @Bean
    public OpenApiCustomizer customerGlobalHeaderOpenApiCustomiser() {
        return openApi -> {
            openApi.getPaths().values().forEach(pathItem -> pathItem.readOperations().forEach(operation -> {
                ApiResponses apiResponses = operation.getResponses();

                apiResponses.addApiResponse("200", createApiResponse("Sucesso!"));
                apiResponses.addApiResponse("201", createApiResponse("Objeto Persistido!"));
                apiResponses.addApiResponse("204", createApiResponse("Objeto Excluído!"));
                apiResponses.addApiResponse("400", createApiResponse("Erro na Requisição!"));
                apiResponses.addApiResponse("401", createApiResponse("Acesso Não Autorizado!"));
                apiResponses.addApiResponse("403", createApiResponse("Acesso Proibido!"));
                apiResponses.addApiResponse("404", createApiResponse("Objeto Não encontrado!"));
                apiResponses.addApiResponse("500", createApiResponse("Erro na Aplicação!"));
            }));
        };
    }
}
