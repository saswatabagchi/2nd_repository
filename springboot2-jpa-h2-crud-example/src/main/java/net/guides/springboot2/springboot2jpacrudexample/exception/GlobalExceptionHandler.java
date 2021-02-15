package net.guides.springboot2.springboot2jpacrudexample.exception;

import java.net.http.HttpHeaders;
import java.util.Date;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

//summery 
//@ControllerAdvice is common for all controller
//always extends ResponseEntityExceptionHandler
//any exception handling class marked with @ExceptionHandler
//return type is ResponseEntity<?> and return with a custom class ErrorDetails like eg ResponseEntity<>(errorDetails, HttpStatus.INTERNAL_SERVER_ERROR)
// to get the error message Exception ex or ResourceNotFoundException ex then after ex.getMessage()
// to get only the uri part? -> 1) One can get request URI and client information from WebRequest using webRequest.getDescription(true).
//For Spring @valid validation errors, it will throw handleMethodArgumentNotValid
//The ambiguity is because you have the same method - @ExceptionHandler in both the classes - ResponseEntityExceptionHandler, MethodArgumentNotValidException. 
//You need to write the overridden method as follows to get around this issue or exclude ResponseEntityExceptionHandler
@ControllerAdvice
//public class GlobalExceptionHandler extends ResponseEntityExceptionHandler  {
public class GlobalExceptionHandler   {
	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<?> resourceNotFoundException(ResourceNotFoundException ex, WebRequest request) {
		ErrorDetails errorDetails = new ErrorDetails(new Date(), ex.getMessage(), request.getDescription(false));
		return new ResponseEntity<>(errorDetails, HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(Exception.class)
	public ResponseEntity<?> globleExcpetionHandler(Exception ex, WebRequest request) {
		ErrorDetails errorDetails = new ErrorDetails(new Date(), ex.getMessage(), request.getDescription(false));
		return new ResponseEntity<>(errorDetails, HttpStatus.INTERNAL_SERVER_ERROR);
	}
	@ExceptionHandler(MethodArgumentNotValidException.class)
	//@ResponseStatus(code = HttpStatus.BAD_REQUEST)
	//@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
            HttpHeaders headers,
            HttpStatus status,
            WebRequest request)  {
		ErrorDetails errorDetails = new ErrorDetails(new Date(), ex.getMessage(), "Validation failed");
		return new ResponseEntity<>(errorDetails, HttpStatus.BAD_REQUEST);
	}
}
