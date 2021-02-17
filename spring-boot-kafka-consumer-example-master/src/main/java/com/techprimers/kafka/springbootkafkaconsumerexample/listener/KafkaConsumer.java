package com.techprimers.kafka.springbootkafkaconsumerexample.listener;

import com.techprimers.kafka.springbootkafkaconsumerexample.model.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class KafkaConsumer {

	@Autowired
    private KafkaTemplate<String, String> kafkaTemplate;
  
    private static final String TOPIC = "TestListener";
    
    @KafkaListener(topics = "TestTopic", group = "group_id")
    public void consume(String message) {
        System.out.println("Consumed message: " + message);
        message = message + " our first microservice fully build on kafka!!";
        kafkaTemplate.send(TOPIC, message );
    }


    @KafkaListener(topics = "Kafka_Example_json", group = "group_json",
            containerFactory = "userKafkaListenerFactory")
    public void consumeJson(User user) {
        System.out.println("Consumed JSON Message: " + user);
    }
}
