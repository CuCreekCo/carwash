package co.cucreek.carwash.handlers;

import co.cucreek.carwash.exceptions.UserExistsException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebExceptionHandler;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.nio.charset.StandardCharsets;

/**
 * @author jljdavidson on 2/16/18.
 */
@Component
@Order(-2)
public class GlobalErrorHandler implements WebExceptionHandler {
    private static final Logger logger = LoggerFactory.getLogger(GlobalErrorHandler.class);

    public GlobalErrorHandler() {
        super();
        logger.info("Registered");
    }

    @Override
    public Mono<Void> handle(ServerWebExchange exchange, Throwable ex) {
        logger.info("Inside global error handler");
        logger.error(ex.getMessage(), ex);
        if (ex instanceof UserExistsException) {
            exchange.getResponse().setStatusCode(HttpStatus.CONFLICT);
            //TODO convert to JSON or map or something
            byte[] bytes = "Some text".getBytes(StandardCharsets.UTF_8);
            DataBuffer buffer = exchange.getResponse().bufferFactory().wrap(bytes);
            return exchange.getResponse().writeWith(Flux.just(buffer));
        }
        return Mono.error(ex);
    }
}
