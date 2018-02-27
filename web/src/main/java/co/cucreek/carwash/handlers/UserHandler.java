package co.cucreek.carwash.handlers;

import co.cucreek.carwash.domain.model.User;
import co.cucreek.carwash.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

/**
 * @author jljdavidson on 2/6/18.
 */
@Component
public class UserHandler {

    private final UserService userService;
    private final ErrorHandler errorHandler;

    @Autowired
    public UserHandler(UserService userService, ErrorHandler errorHandler) {
        this.userService = userService;
        this.errorHandler = errorHandler;
    }

    public Mono<ServerResponse> register(ServerRequest request) {
        return request.bodyToMono(User.class)
                .map(userService::registerUser)
                .flatMap(user -> ServerResponse.ok().body(user, User.class))
                .onErrorResume(errorHandler::throwableError);
    }
}
