package co.cucreek.carwash.web;

import co.cucreek.carwash.handlers.ErrorHandler;
import co.cucreek.carwash.handlers.UserHandler;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.RequestPredicates;
import org.springframework.web.reactive.function.server.RouterFunction;

import static org.springframework.web.reactive.function.server.RequestPredicates.*;
import static org.springframework.web.reactive.function.server.RouterFunctions.nest;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

/**
 * @author jljdavidson on 2/16/18.
 */
public class UserRouter {

    private static final String API_PATH = "/user";
    private static final String REGISTER_PATH = "/register";

    static RouterFunction<?> doRoute(final UserHandler userHandler, final ErrorHandler errorHandler) {
        return nest(path(API_PATH),
                nest(accept(MediaType.APPLICATION_JSON),
                route(POST(REGISTER_PATH), userHandler::register))
                    .andOther(route(RequestPredicates.all(), errorHandler::notFound)));
    }

}
