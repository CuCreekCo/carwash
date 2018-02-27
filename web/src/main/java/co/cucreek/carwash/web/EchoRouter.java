package co.cucreek.carwash.web;

import co.cucreek.carwash.handlers.EchoHandler;
import co.cucreek.carwash.handlers.ErrorHandler;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.RequestPredicates;
import org.springframework.web.reactive.function.server.RouterFunction;

import static org.springframework.web.reactive.function.server.RequestPredicates.*;
import static org.springframework.web.reactive.function.server.RouterFunctions.nest;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

/**
 * @author jljdavidson on 2/16/18.
 */
public class EchoRouter {

    private static final String API_PATH = "/echo";

    static RouterFunction<?> doRoute(final EchoHandler echoHandler, final ErrorHandler errorHandler) {
        return
            nest(accept(MediaType.APPLICATION_JSON),
                route(POST(API_PATH), echoHandler::echo)
                    .andRoute(GET(API_PATH), echoHandler::echo)
            ).andOther(route(RequestPredicates.all(), errorHandler::notFound));
    }

}
