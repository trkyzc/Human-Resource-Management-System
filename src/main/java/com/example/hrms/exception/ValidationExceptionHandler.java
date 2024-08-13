package com.example.hrms.exception;

import java.net.http.HttpHeaders;
import java.util.HashMap;
import java.util.Map;

import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.example.hrms.core.utilities.results.ErrorDataResult;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;


//@ControllerAdvice  //@ControllerAdvice anotasyonu ile işaretlenen sınıflar, uygulama genelinde tüm controller'lar için istisnaları yakalamak ve işlemek için kullanılır.
public class ValidationExceptionHandler {
	
	
//	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,  //ex doğrulama sırasında oluşan tüm hataları içerir.
//			HttpHeaders headers, HttpStatus status, WebRequest request) {
//		Map<String, String> errors = new HashMap<>();
//		ex.getBindingResult().getAllErrors().forEach((error) -> {
//			String fieldName = ((FieldError) error).getField(); // Her bir hata field error olarak değerlendirilir.
//			String errorMessage = error.getDefaultMessage(); // Hatanın mesajını alır.
//			errors.put(fieldName, errorMessage); // Map'e ekler.
//		});
//		return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
//	}
	
	
//	@ExceptionHandler(MethodArgumentNotValidException.class) //Sistemde bir exception oluşursa alttaki metodu çağır.Sonra oraya da hataları parametre olarak geçtim. 
//	@ResponseStatus(HttpStatus.BAD_REQUEST) //Bu metod default olarak bad request(500 hatası) dönsün.
//	public ErrorDataResult<Object> handleValidationException(MethodArgumentNotValidException exceptions){ //Bütün metodlarımızı buradan geçirmiş olacağız.
//		Map<String,String> validationErrors = new HashMap<String, String>(); //Map=Dictionary  //Anahtar değerim hangi alan mesela email alanı.Hata string mesela email alanı formata uygun değil.Dict oluşturduk.
//		for(FieldError fieldError :  exceptions.getBindingResult().getFieldErrors()) { //Alanlarda oluşan (şuan user) alanlar için tüm hataları oku liste dönüyor.
//			validationErrors.put(fieldError.getField(), fieldError.getDefaultMessage());
//		}
//		
//		ErrorDataResult<Object> errors = new ErrorDataResult<Object>(validationErrors, "Doğrulama hataları");
//		return errors;
//				
//	}
	
	
	@ResponseStatus(HttpStatus.BAD_REQUEST) //Bu metod çalıştığında bad request döndür.
	@ExceptionHandler(MethodArgumentNotValidException.class) //MethodArgumentNotValidException türünde hata olduğunda bu metodu çalıştır.(@Valid hatası)
	public  ErrorDataResult<Object>  handleValidationExceptions(MethodArgumentNotValidException exception){ //exception doğrulama sırasında oluşan tüm hataları içerir.
		Map<String, String> errors = new HashMap<>();
		exception.getBindingResult().getAllErrors().forEach((error) -> {
			String fieldName = ((FieldError) error).getField(); // Her bir hata field error olarak değerlendirilir.
			String errorMessage = error.getDefaultMessage(); // Hatanın mesajını alır.
			errors.put(fieldName, errorMessage); // Map'e ekler.
		});
		return new ErrorDataResult<Object>(errors, "Doğrulama hataları");
	}
	
	
}
