package com.example.hrms.entities.dtos;

import org.hibernate.validator.constraints.URL;

import com.example.hrms.validation.UniqueEmail;
import com.example.hrms.validation.UniqueUsername;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
public class EmployerRequestDto {
	
	@NotBlank(message = "Kullanıcı adı alanı boş bırakılamaz!")
	@NotNull(message = "Kullanıcı adı alanı boş bırakılamaz!")
	@Pattern(regexp = "^[a-zA-Z0-9]{2,}$", message = "Geçersiz kullanıcı adı! Lütfen en az 2 karakterden oluşan bir kullanıcı adı girin.")
	@UniqueUsername
	private String username;
	
	@NotBlank(message = "Email alanı boş bırakılamaz!")
	@NotNull(message = "Email alanı boş bırakılamaz!")
	@Email(message = "Geçersiz email adresi! Lütfen geçerli bir email adresi girin.")
	@UniqueEmail
	private String email;
	
	@NotBlank(message = "Şifre alanı boş bırakılamaz!")
	@NotNull(message = "Şifre alanı boş bırakılamaz!")
	@Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}$", message = "Geçersiz şifre! Lütfen en az bir büyük harf, bir küçük harf, bir rakam ve bir özel karakter içeren en az 8 karakterli bir şifre girin.")
	private String password;
	
	@NotBlank(message = "Şifre tekrarı alanı boş bırakılamaz!")
	@NotNull(message = "Şifre tekrarı alanı boş bırakılamaz!")
	private String repeatedPassword;
	
	@NotBlank(message = "Şirket adı alanı boş bırakılamaz!")
	@NotNull(message = "Şirket adı alanı boş bırakılamaz!")
	private String companyName;
	
	@NotBlank(message = "Web adresi alanı boş bırakılamaz!")
	@NotNull(message = "Web adresi alanı boş bırakılamaz!")
	@URL(message = "Geçersiz web adresi! Lütfen geçerli bir web adresi girin.")
	private String webAddress;
	
	@NotBlank(message = "Telefon numarası alanı boş bırakılamaz!")
	@NotNull(message = "Telefon numarası alanı boş bırakılamaz!")
	@Pattern(regexp = "^\\+90\\d{10}$", message = "Geçersiz telefon numarası! Lütfen '+90' ile başlayan ve 10 haneli bir numara girin.")
	private String phoneNumber;


}
