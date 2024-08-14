package com.example.hrms.validation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;


@Constraint(validatedBy = UniqueUsernameValidator.class)  //bu annotation kullanıldığında hangi class'ın çalışacağını belirtir.
@Target({ElementType.FIELD})  //bu anotasyon bir sınıftaki bir alan (field) üzerine uygulanabilir.
@Retention(RetentionPolicy.RUNTIME) //bu annotation'ın ne zaman çalışacağını belirtir.
public @interface UniqueUsername {
	
	String message() default "Bu kullanıcı adı zaten kullanılıyor"; //eğer hata mesajı belirtilmezse default olarak bu mesajı verir.

	Class<?>[] groups() default {};  //validation işlemlerini gruplandırmak için kullanılır.

	Class<? extends Payload>[] payload() default {};  //validation işlemlerine ek bilgi eklemek için kullanılır.

}
