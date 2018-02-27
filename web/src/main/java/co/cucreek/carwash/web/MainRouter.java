package co.cucreek.carwash.web;

import co.cucreek.carwash.handlers.ErrorHandler;
import co.cucreek.carwash.handlers.UserHandler;
import co.cucreek.carwash.handlers.EchoHandler;
import org.springframework.web.reactive.function.server.RouterFunction;

/**
 * @author jljdavidson on 2/16/18.
 */
public class MainRouter {
    public static RouterFunction<?> doRoute(final UserHandler userHandler,
                                                         final EchoHandler echoHandler,
                                                         final ErrorHandler errorHandler) {
        return UserRouter
                .doRoute(userHandler, errorHandler)
                .andOther(EchoRouter.doRoute(echoHandler, errorHandler));
    }
}
