package GatewayConfiguration;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GatewayConfigUser {
    @Bean
    public RouteLocator customRouteLocator(RouteLocatorBuilder builder){
        return builder.routes()
//                .route("user", r -> r.path("/user")
//                        .uri("http://localhost:8081/"))
//                .route("userAdd", r -> r.path("/user/add")
//                        .uri("http://localhost:8081/"))
//                .route("userUpdate", r -> r.path("/user/{id}/update")
//                        .uri("http://localhost:8081/"))
//                .route("userDelete", r -> r.path("/user/{id}/delete")
//                        .uri("http://localhost:8081/"))
//                .build();

                .route("user-service", r -> r.path("/user/**")
                        .uri("http://localhost:8081"))
                .build();
    }
}
