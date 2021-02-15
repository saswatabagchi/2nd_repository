package com.bharath.springweb.controllers;

import java.util.List;
import java.util.NoSuchElementException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.bharath.springweb.entities.Product;
import com.bharath.springweb.repos.ProductRepository;



@RestController
public class ProductRestController {

	
	
	@Autowired
	ProductRepository repository;
	
	
	
	private static final Logger LOGGER = LoggerFactory.getLogger(ProductRestController.class);

	@RequestMapping(value = "/products/", method = RequestMethod.GET)
	public List<Product> getProducts() {
		System.out.println(repository.findAll());
		return repository.findAll();
	}

	@RequestMapping(value = "/products/{id}", method = RequestMethod.GET)
	//public ResponseEntity<Product> getProduct(@PathVariable("id") int id) {
	public  ResponseEntity<Product> getProduct(@PathVariable("id") int id) throws com.bharath.springweb.controllers.ResourceNotFoundException {
		LOGGER.info("Finding product by ID:"+id);
			HttpStatus status = HttpStatus.OK;
			Product product = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Sorry Product not found for this id :: " + id));
			return ResponseEntity.ok().body(product);

	}

	@RequestMapping(value = "/products/", method = RequestMethod.POST)
	public Product createProduct(@RequestBody Product product) {
		return repository.save(product);
	}

	@RequestMapping(value = "/products/", method = RequestMethod.PUT)
	public Product updateProduct(@RequestBody Product product) {
		return repository.save(product);
	}

	@RequestMapping(value = "/products/{id}", method = RequestMethod.DELETE)
	public void deleteProduct(@PathVariable("id") int id) {
		repository.deleteById(id);
	}

}
