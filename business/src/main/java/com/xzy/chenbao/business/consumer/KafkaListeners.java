package com.xzy.chenbao.business.consumer;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;

import java.util.Optional;

/**
 * Created by xiongzhanyuan on 2017/2/21.
 */
public class KafkaListeners {
    @KafkaListener(topics = {"chenbao"})
    public void listen(ConsumerRecord<?, ?> record) {
        Optional<?> kafkaMessage = Optional.ofNullable(record.value());
        if (kafkaMessage.isPresent()) {
            Object message = kafkaMessage.get();
            System.out.println(message);
        }
    }
}
