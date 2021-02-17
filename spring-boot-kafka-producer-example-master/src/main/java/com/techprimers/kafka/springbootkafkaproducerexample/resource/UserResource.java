package com.techprimers.kafka.springbootkafkaproducerexample.resource;

import com.techprimers.kafka.springbootkafkaproducerexample.config.EventListener;
import com.techprimers.kafka.springbootkafkaproducerexample.config.KafkaListenerServiceInterface;
import com.techprimers.kafka.springbootkafkaproducerexample.model.User;

import java.util.concurrent.ThreadLocalRandom;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("kafka")
public class UserResource {

    @Autowired
    private KafkaTemplate<String, User> kafkaTemplate;
    //KafkaListenerServiceInterface imp;
    private static final String TOPIC = "TestTopic";
    
                
    @GetMapping("/publish/{name}")
    //public synchronized String post(@PathVariable("name") final String name) throws InterruptedException {
    public  String post(@PathVariable("name") final String name) throws InterruptedException {

        kafkaTemplate.send(TOPIC, new User(name, "Technology", 12000L));
        
        //@KafkaListener(topics = "TestTopic", group = "group_id")
        //KafkaListenerServiceInterface imp;
        //wait();
        //Both producer and listener are being implemented in same application context 
        try {
            Thread.sleep(ThreadLocalRandom.current().nextInt(1000, 2000));
        } catch (InterruptedException e)  {
            Thread.currentThread().interrupt(); 
            System.out.println("Thread interrupted" + e); 
        }
        //System.out.println(EventListener.msg);
        if (EventListener.msg != null) {
        	return "the msg set is " + EventListener.msg;
        }else {
        	return "Not able to reach kafka topic " + TOPIC ;
        }
        
        //return imp.getMsg();
    }
    
   
}
