package com.niit.SpringCloudAPIGateway.config;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {
    @Bean
    public RouteLocator myRoutes(RouteLocatorBuilder routeLocatorBuilder){
        return routeLocatorBuilder.routes()
                .route(p->p
                        .path("/userservice/**")
                        .uri("http://authentication-service1:8098/"))
                .route(p->p
                        .path("/trackapp/v1/**")
                        .uri("http://track-service:8095/"))
                .build();
    }
}
