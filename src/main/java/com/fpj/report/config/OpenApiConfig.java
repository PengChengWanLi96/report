package com.fpj.report.config;


import com.fpj.report.utils.IPUtil;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author fangpengjun
 * @date 2025/3/30
 */
@Slf4j
@Configuration
@OpenAPIDefinition(info = @Info(
        title = "API 文档",
        version = "1.0",
        description = "Spring Boot 3.x 集成 OpenAPI 示例"
))
public class OpenApiConfig {

    @Value("${server.port}")
    private String port;

    @PostConstruct
    public void printSwagger() {
        String serverIp = IPUtil.getServerIP();
        String serverUrl = String.format("%s:%s", serverIp, port);
        String swaggerUrl = String.format("http://%s/swagger-ui.html", serverUrl);
        String openApiUrl = String.format("http://%s/v3/api-docs", serverUrl);
        String knife4jUrl = String.format("http://%s/doc.html", serverUrl);
        log.info("swagger doc url: {}", swaggerUrl);
        log.info("openAPI doc url: {}", openApiUrl);
        log.info("knife4j doc url: {}", knife4jUrl);
    }

    // 自定义安全方案（如 JWT）
    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .components(new Components()
                        .addSecuritySchemes("BearerAuth", new SecurityScheme()
                                .type(SecurityScheme.Type.HTTP)
                                .scheme("bearer")
                                .bearerFormat("JWT")
                        )
                )
                .addSecurityItem(new SecurityRequirement().addList("BearerAuth"));
    }
}