package com.bharath.springweb;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;

@SpringBootApplication
public class ProductrestapiApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProductrestapiApplication.class, args);
		System.out.println("start");
	}
	
	//@Bean(name="entityManagerFactory")
	//public LocalSessionFactoryBean sessionFactory() {
	//    LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();
	    //return sessionFactory;
	//} 
}
