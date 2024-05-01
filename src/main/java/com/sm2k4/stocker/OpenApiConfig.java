package com.sm2k4.stocker;

import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.servers.Server;

@Configuration
public class OpenApiConfig {

    @Value("${stocker.dev.url}")
    private String devUrl;

    @Value("${stocker.prod.url}")
    private String prodUrl;

    @Bean
    public OpenAPI myOpenAPI() {
        Server devServer = new Server();
        devServer.setUrl(devUrl);
        devServer.setDescription("Server URL in Development environment");

        Server prodServer = new Server();
        prodServer.setUrl(prodUrl);
        prodServer.setDescription("Server URL in Production environment");

        License mitLicense = new License().name("MIT License").url("https://github.com/2k4sm/stocker/blob/main/LICENSE");

        Info info = new Info()
                .title("Stock Management API")
                .version("0.1")
                .description("This API exposes endpoints to manage stock trading operations")
                .license(mitLicense);

        return new OpenAPI().info(info).servers(List.of(devServer,prodServer));
    }
}
