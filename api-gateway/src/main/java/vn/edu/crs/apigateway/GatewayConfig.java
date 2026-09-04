package vn.edu.crs.apigateway;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.reactive.CorsWebFilter;
import org.springframework.web.cors.reactive.UrlBasedCorsConfigurationSource;

@Configuration
public class GatewayConfig {

    @Bean
    public RouteLocator customRouteLocator(RouteLocatorBuilder builder) {
        return builder.routes()
                .route("auth-service", r -> r.path("/api/auth/**")
                        .filters(f -> f.rewritePath("/api/auth/(?<segment>.*)", "/auth/${segment}"))
                        .uri("http://localhost:8085"))
                .route("auth-service-apikeys-list", r -> r.path("/api/api-keys")
                        .filters(f -> f.rewritePath("/api/api-keys", "/api-keys"))
                        .uri("http://localhost:8085"))
                .route("auth-service-apikeys-detail", r -> r.path("/api/api-keys/**")
                        .filters(f -> f.rewritePath("/api/api-keys/(?<segment>.*)", "/api-keys/${segment}"))
                        .uri("http://localhost:8085"))
                .route("course-service-list", r -> r.path("/api/courses")
                        .filters(f -> f.rewritePath("/api/courses", "/courses"))
                        .uri("http://localhost:8084"))
                .route("course-service-detail", r -> r.path("/api/courses/**")
                        .filters(f -> f.rewritePath("/api/courses/(?<segment>.*)", "/courses/${segment}"))
                        .uri("http://localhost:8084"))
                .route("registration-service-list", r -> r.path("/api/registrations")
                        .filters(f -> f.rewritePath("/api/registrations", "/registrations"))
                        .uri("http://localhost:8083"))
                .route("registration-service-detail", r -> r.path("/api/registrations/**")
                        .filters(f -> f.rewritePath("/api/registrations/(?<segment>.*)", "/registrations/${segment}"))
                        .uri("http://localhost:8083"))
                .route("course-service-partner", r -> r.path("/api/public/courses")
                        .filters(f -> f.rewritePath("/api/public/courses", "/courses"))
                        .uri("http://localhost:8084"))
                .build();
    }

    @Bean
    public CorsWebFilter corsWebFilter() {
        CorsConfiguration corsConfig = new CorsConfiguration();
        corsConfig.addAllowedOrigin("http://localhost:5173");
        corsConfig.addAllowedMethod("*");
        corsConfig.addAllowedHeader("*");
        corsConfig.setAllowCredentials(true);
        
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", corsConfig);
        
        return new CorsWebFilter(source);
    }
}
