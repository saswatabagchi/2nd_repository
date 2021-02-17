package com.techprimers.kafka.springbootkafkaproducerexample.config;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Component
public class KafkaListenerService implements KafkaListenerServiceInterface{
	
	private String msg;

	@Autowired
    private ApplicationEventPublisher applicationEventPublisher;
	 
	public void setMsg(String msg) {
		this.msg = msg;
	}

	

	@KafkaListener(topics = "TestListener", group = "group_id")
    public void consume(String message) {
		System.out.println(" listener event started. ");
		 Event event = new Event(this, message);
	        applicationEventPublisher.publishEvent(event);
       // setMsg(message);
    }

	@Override
	public String getMsg() {
		
		return msg;
	}


}
