package kafka;


import dto.OrderEvent;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.util.logging.Logger;

@Service
@Slf4j
public class OrderConsumer {
    private static final Logger LOGGER= (Logger) LoggerFactory.getLogger(OrderConsumer.class);

    @KafkaListener(topics = "${spring.kafka.topic.name}", groupId = "${spring.kafka.consumer.group-id }")
    public void consume(OrderEvent orderEvent){

        LOGGER.info(String.format("order event is received in stock servoce"));
        // save the order event in database

    }
}
