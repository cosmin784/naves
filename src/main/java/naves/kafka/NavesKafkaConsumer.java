package naves.kafka;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class NavesKafkaConsumer {

	private static final Logger logger = LoggerFactory.getLogger(NavesKafkaConsumer.class);
	
	@KafkaListener(topics = "naves", groupId = "1")
    public void consume(String message) {
		logger.info("Nave: " + message);
    }
}
