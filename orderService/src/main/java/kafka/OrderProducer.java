package kafka;
import dto.OrderEvent;
import org.apache.kafka.clients.admin.NewTopic;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.event.KafkaEvent;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

import java.util.logging.Logger;

@Service
public class OrderProducer {
    private  static final Logger LOGGER= (Logger) LoggerFactory.getLogger(OrderProducer.class);
    @Autowired
    private NewTopic topic;
    @Autowired
    private KafkaTemplate<String, OrderEvent> kafkaTemplate;

//    public OrderProducer(NewTopic topic, KafkaTemplate<String, KafkaEvent> kafkaTemplate) {
//        this.topic = topic;
//        this.kafkaTemplate = kafkaTemplate;
//    }
    public void sendMesage(OrderEvent event){

        LOGGER.info(String.format("Order event=>% s",event.toString()));

        Message<OrderEvent> message= MessageBuilder
                .withPayload(event)
                .setHeader(KafkaHeaders.TOPIC, topic.name()).build();
        kafkaTemplate.send(message);

    }

}

