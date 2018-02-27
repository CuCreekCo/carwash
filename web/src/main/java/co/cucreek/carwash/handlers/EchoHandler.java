package co.cucreek.carwash.handlers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

/**
 * @author jljdavidson on 2/6/18.
 */
@Component
public class EchoHandler {

    private final KafkaTemplate kafkaTemplate;

    @Autowired
    public EchoHandler(KafkaTemplate kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public Mono<ServerResponse> echo(ServerRequest request) {
        return ServerResponse.ok().body(
                request.bodyToMono(String.class).doOnSuccess(
                        s -> kafkaTemplate.sendDefault(s)
                                .addCallback(System.out::println, Throwable::printStackTrace)), String.class);
    }
}
