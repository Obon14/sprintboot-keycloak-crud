package com.example.keycloak.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.OAuthFlow;
import io.swagger.v3.oas.models.security.OAuthFlows;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .addSecurityItem(new SecurityRequirement().addList("Keycloak"))
                .components(new io.swagger.v3.oas.models.Components()
                        .addSecuritySchemes("Keycloak",
                                new SecurityScheme()
                                        .type(SecurityScheme.Type.OAUTH2)
                                        .description("Keycloak OAuth")
                                        .flows(new OAuthFlows()
                                                .authorizationCode(new OAuthFlow()
                                                        .authorizationUrl("http://localhost:8080/realms/spring-realm/protocol/openid-connect/auth")
                                                        .tokenUrl("http://localhost:8080/realms/spring-realm/protocol/openid-connect/token")
                                                ))))
                .info(new Info().title("Book API")
                        .description("Spring Boot + Keycloak + Swagger")
                        .version("1.0"));
    }
}
