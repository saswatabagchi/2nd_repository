package com.techprimers.kafka.springbootkafkaproducerexample.config;

import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

@Component
public class EventListener implements ApplicationListener<Event> {
	public static String msg;
	//public static boolean transfer = true;
	public static int transfer = 0;
    @Override
    //public synchronized void onApplicationEvent(Event event) {
    public  void onApplicationEvent(Event event) {
    	msg= event.getMessage();
    	//notifyAll();
        System.out.println("Received spring custom event - " + event.getMessage());
    }
}