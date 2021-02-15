package com.bharath.springweb;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

import com.bharath.springweb.entities.Product;



@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductrestapiApplicationTests {

	@Test
	public void contextLoads() {
		RestTemplate restTemplate = new RestTemplate();
		String resourceUrl
		  = "http://localhost:8090/products";
		//Product product = restTemplate.getForObject(resourceUrl + "/1", Product.class);
		ResponseEntity<Product> entity = restTemplate.getForEntity(resourceUrl + "/1", Product.class);
		System.out.println("the response is " + entity.getHeaders().getContentType()) ;
		//assertThat(response.getStatusCode(), equalTo(HttpStatus.OK));

	}

}
