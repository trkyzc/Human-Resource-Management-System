package com.example.hrms.exception;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import com.example.hrms.core.utilities.results.DataResult;
import com.example.hrms.core.utilities.results.ErrorDataResult;

import jakarta.persistence.EntityNotFoundException;

@RestControllerAdvice   
public class GlobalExceptionHandler {
	
    @ResponseStatus(HttpStatus.BAD_REQUEST) //Bu metod çalıştığında bad request döndür.
    @ExceptionHandler(MethodArgumentNotValidException.class)  //MethodArgumentNotValidException türünde hata olduğunda bu metodu çalıştır.(@Valid hatası)
    public ErrorDataResult<Object> handleValidationExceptions(MethodArgumentNotValidException exception){  //exception doğrulama sırasında oluşan tüm hataları içerir
        Map<String,String> errorMap=new HashMap<>();
        exception.getBindingResult().getAllErrors().forEach((error)->{
            String fieldName=((FieldError)error).getField();    // Her bir hata field error olarak değerlendirilir.
            String errorMessage=error.getDefaultMessage();      // Hatanın mesajını alır.
            errorMap.put(fieldName,errorMessage);               // Map'e ekler.
        });
        return new ErrorDataResult<Object>(errorMap, "Doğrulama hataları");
    }
        
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler({EntityNotFoundException.class})
	public ErrorDataResult<Object> handleEntityNotFoundException(EntityNotFoundException exception,
			WebRequest request) {
		Map<String, Object> body = new HashMap<>();
		body.put("message", exception.getMessage());
		body.put("path", ((ServletWebRequest) request).getRequest().getRequestURI());
		return new ErrorDataResult<Object>(body, "EntityNotFound hataları");
	}
    
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler({MethodArgumentTypeMismatchException.class})  //request parametrelerindeki type mismatch
    public ErrorDataResult<Object> handleMethodArgumentTypeMismatchException(MethodArgumentTypeMismatchException exception, WebRequest request) {
    	
    	Map<String, Object> body = new HashMap<>();
    	body.put("message", exception.getMessage());
    	body.put("path", ((ServletWebRequest) request).getRequest().getRequestURI());
    	return new ErrorDataResult<Object>(body, "MethodArgumentTypeMismatch hataları");
    	
    }
    
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler({HttpMessageNotReadableException.class})  //ör: form içindeki typemismatch
    public ErrorDataResult<Object> handleHttpMessageNotReadableException(HttpMessageNotReadableException exception, WebRequest request){
    	Map<String,Object> body = new HashMap<>();
    	body.put("message", exception.getMessage());
    	body.put("path", ((ServletWebRequest) request).getRequest().getRequestURI());
    	return new ErrorDataResult<Object>(body, "HttpMessageNotReadable hataları");
    }
    
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler({Exception.class})
	public ErrorDataResult<Object> handleAll(Exception exception, WebRequest request) {
		Map<String, Object> body = new HashMap<>();
		body.put("message", exception.getMessage());
		body.put("path", ((ServletWebRequest) request).getRequest().getRequestURI());
		return new ErrorDataResult<Object>(body, "Genel hatalar");
	}
}