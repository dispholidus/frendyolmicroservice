package com.kemalo.Gateway;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GatewayConfig {
    @Value("${product.url}")
    private String PRODUCT_URL ;
    @Value("${order.url}")
    private String ORDER_URL;
    @Value("${user.url}")
    private String USER_URL;

    @Bean
    public RouteLocator routeLocator (RouteLocatorBuilder builder){
        return builder.routes()
                .route(r-> r.path("/api/v1/orders/**")
                        .and().method("GET", "POST")
                        .uri(ORDER_URL))
                .route(r-> r.path("/api/v1/product/**")
                        .and().method("GET", "POST","PUT","DELETE")
                        .uri(PRODUCT_URL))
                .route(r-> r.path("/api/v1/user/**")
                        .and().method("GET", "POST","DELETE")
                        .uri(USER_URL))
                .build();
    }
}
