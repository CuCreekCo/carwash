package co.cucreek.carwash;

import co.cucreek.carwash.handlers.EchoHandler;
import co.cucreek.carwash.handlers.ErrorHandler;
import co.cucreek.carwash.handlers.UserHandler;
import co.cucreek.carwash.web.MainRouter;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.HttpHandler;
import org.springframework.http.server.reactive.ReactorHttpHandlerAdapter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.reactive.CorsWebFilter;
import org.springframework.web.cors.reactive.UrlBasedCorsConfigurationSource;
import org.springframework.web.reactive.config.WebFluxConfigurer;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebExceptionHandler;
import org.springframework.web.server.adapter.WebHttpHandlerBuilder;
import org.springframework.web.util.pattern.PathPatternParser;
import reactor.core.publisher.Mono;
import reactor.ipc.netty.NettyContext;
import reactor.ipc.netty.http.server.HttpServer;

import java.util.Arrays;

/**
 * @author jljdavidson on 2/9/18.
 */
@Configuration
public class WebApplicationConfig implements WebFluxConfigurer {

    @Bean
    public RouterFunction<?> mainRouterFunction(final UserHandler userHandler,
                                                             final EchoHandler echoHandler,
                                                             final ErrorHandler errorHandler) {
        return MainRouter.doRoute(userHandler, echoHandler, errorHandler);
    }

    @Bean
    CorsWebFilter corsFilter() {
        CorsConfiguration config = new CorsConfiguration();
        config.applyPermitDefaultValues();
        config.setAllowedOrigins(Arrays.asList("http://localhost:4200"));
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource(new PathPatternParser());
        source.registerCorsConfiguration("/**", config);
        return new CorsWebFilter(source);
    }
}
