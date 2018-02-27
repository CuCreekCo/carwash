package co.cucreek.carwash;

import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.StreamsConfig;
import org.apache.kafka.streams.kstream.KStream;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.Environment;
import org.springframework.kafka.annotation.KafkaStreamsDefaultConfiguration;

import java.util.HashMap;
import java.util.Map;

/**
 * @author jljdavidson on 2/10/18.
 */

//@Configuration
//@EnableKafka
//@EnableKafkaStreams
public class KafkaStreamsConfig {

    private final Environment environment;

    @Autowired
    public KafkaStreamsConfig(Environment environment) {
        this.environment = environment;
    }

    @Bean(name = KafkaStreamsDefaultConfiguration.DEFAULT_STREAMS_CONFIG_BEAN_NAME)
    public StreamsConfig kStreamsConfigs() {
        Map<String, Object> props = new HashMap<>();
        props.put(StreamsConfig.APPLICATION_ID_CONFIG, "testStreams");
        return new StreamsConfig(props);
    }

    @Bean
    public KStream<Integer, String> defaultTopicStream(StreamsBuilder kStreamBuilder) {
        return kStreamBuilder.stream(environment.getProperty("spring.kafka.template.default-topic"));
    }

}