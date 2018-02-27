package co.cucreek.carwash.service;

import co.cucreek.carwash.handlers.ErrorHandler;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

import java.util.concurrent.Callable;

/**
 * @author jljdavidson on 2/15/18.
 */
@Component
public class AsyncJpaWrapper {

    public <T> Mono<T> async(Callable<T> callable) {
        return Mono.fromCallable(callable)
                .subscribeOn(Schedulers.elastic())
                .publishOn(Schedulers.parallel());
    }
}
