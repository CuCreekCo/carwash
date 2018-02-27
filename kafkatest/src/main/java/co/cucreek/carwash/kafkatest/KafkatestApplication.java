package co.cucreek.carwash.kafkatest;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.annotation.KafkaListeners;
import org.springframework.kafka.core.KafkaTemplate;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

@SpringBootApplication
public class KafkatestApplication {

	public static Logger logger = LoggerFactory.getLogger(KafkatestApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(KafkatestApplication.class, args);
	}

	@KafkaListener(topics = "myTopic")
	public void listen(ConsumerRecord<?, ?> consumerRecord) {
		logger.info(consumerRecord.toString());
	}
}
