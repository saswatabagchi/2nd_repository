package com.bharath.springboot;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpHeaders;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;


import com.fasterxml.jackson.core.json.JsonReadContext;

@RestController
public class HelloController {
	
	@RequestMapping(value = "/test/{id}", method = RequestMethod.GET)
	public String contextLoads(@PathVariable("id") int id) {
		RestTemplate restTemplate = new RestTemplate();
		String resourceUrl
		  = "http://localhost:8090/products/";
		//Product product = restTemplate.getForObject(resourceUrl + "/1", Product.class);
		System.out.println("the response is " + resourceUrl + id) ;
		try {
			ResponseEntity<Product> entity = restTemplate.getForEntity(resourceUrl  + id, Product.class);
			System.out.println("the response is " + entity.getHeaders().getContentType()) ;
			return  entity.getBody().getName();
		}catch (HttpClientErrorException e) {
			System.out.println("the error is" + e.getMessage());
			return "product not found";
		}
	}
	
	@RequestMapping(value = "/client/{id}", method = RequestMethod.GET)
	public String contextLoadsClients(@PathVariable("id") int id) {
		RestTemplate restTemplate = new RestTemplate();
		String resourceUrl
		  = "http://localhost:8090/products/" + id;
		System.out.println("the response is " + resourceUrl ) ;
		HttpClient httpClient = HttpClient.newBuilder()
	            .version(HttpClient.Version.HTTP_1_1)
	            .connectTimeout(Duration.ofSeconds(10))
	            .build();
		HttpRequest request = HttpRequest.newBuilder()
                .GET()
                .uri(URI.create(resourceUrl))
                .setHeader("User-Agent", "Java 11 HttpClient Bot") // add request header
                .build();
		try {
			HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
			// print response headers
	        HttpHeaders headers = response.headers();
	        headers.map().forEach((k, v) -> System.out.println(k + ":" + v));
            
	        Map<String, String> env = System.getenv();
	        for (String envName : env.keySet()) {
	            System.out.format("%s=%s%n", envName, env.get(envName));
	        }

	
	        // print status code
	        System.out.println(response.statusCode());
			return response.body();
		} catch (IOException | InterruptedException e) {
			
			System.out.println("the exception is "+ e);
			//e.printStackTrace();
			return "connection failed please try latter!!!";
		}		

	}
	@RequestMapping(value = "/book/{id}", method = RequestMethod.GET)
	public String bookSearch(@PathVariable("id") String id) {
		//RestTemplate restTemplate = new RestTemplate();
		String resourceUrl
		  = "https://www.googleapis.com/books/v1/volumes?q=isbn:" + id;
		System.out.println("the response is " + resourceUrl ) ;
		HttpClient httpClient = HttpClient.newBuilder()
	            .version(HttpClient.Version.HTTP_1_1)
	            .connectTimeout(Duration.ofSeconds(10))
	            .build();
		HttpRequest request = HttpRequest.newBuilder()
                .GET()
                .uri(URI.create(resourceUrl))
                //.setHeader("User-Agent", "Java 11 HttpClient Bot") // add request header
                .build();
		try {
			HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
			// print response headers
	        //HttpHeaders headers = response.headers();
	        //headers.map().forEach((k, v) -> System.out.println(k + ":" + v));
	        // print status code
	        System.out.println(response.statusCode());
			return response.body();
		} catch (IOException | InterruptedException e) {
			
			System.out.println("the exception is "+ e);
			//e.printStackTrace();
			return "connection failed please try latter!!!";
		}		

	}
	
	
	@RequestMapping("/hello")
	public JSONObject hello(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		JSONParser parser = new JSONParser();
		JSONParser parser1 = new JSONParser();
		try {
			JSONObject jsonObject = (JSONObject)parser.parse(req.getReader());
			String name = (String) jsonObject.get("name");
			long age = (Long) jsonObject.get("age");
	        //System.out.println(name);
	        JSONObject obj = new JSONObject();
	        obj.put("name", name );
	        //obj.put("age", age );
	        JSONArray list = new JSONArray();
	        JSONArray msg = (JSONArray) jsonObject.get("messages");
	        int i = 0;
	        //while (i< msg.size()) {
	        	//JSONObject jsonList = (JSONObject)parser.parse((String) msg.get(i));
            	//System.out.println(i);
            	//i++;
            //}
            Iterator<JSONObject> iterator = msg.iterator();
            while (iterator.hasNext()) {
            	JSONObject jsonObjectList = (JSONObject)parser1.parse(iterator.next().toString());
            	//System.out.println();
            	String msgVal = (String ) jsonObjectList.get("name");
            	System.out.println(msgVal);
            	if ( msgVal.equals("msg 1")   ) {
            		JSONObject objList = new JSONObject();
            		objList.put("name_is", msgVal+"20%" );
            		//list.add(msg.get(i));
            		list.add(objList);
            	}
             i++;
            }
            obj.put("msg", list);
	        return obj ;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
		
		
		//BufferedReader br = req.getReader();
		 //StringBuilder body = new StringBuilder(" ");
		 //String str;
		 //while((str =br.readLine())!= null)
         //    body.append(str);
	    //responseBody = new Gson().fromJson(jsonReader, Map.class);
		//System.out.println("reqest body is " + body);
		
	}

	

}
