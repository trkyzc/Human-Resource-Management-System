package com.example.hrms.entities.dtos;

import java.util.List;

import com.example.hrms.validation.UniqueEmail;
import com.example.hrms.validation.UniqueUsername;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
public class CandidateRequestDto {
	
	@NotNull(message="İsim alanı boş bırakılamaz")
	@NotBlank(message="İsim alanı boş bırakılamaz")
	private String firstName;
	
	@NotNull(message="Soyisim alanı boş bırakılamaz")
	@NotBlank(message="Soyisim alanı boş bırakılamaz")
	private String lastName;
	
	@NotNull(message="Kimlik numarası alanı boş bırakılamaz")
	@NotBlank(message="Kimlik numarası alanı boş bırakılamaz")
	@Pattern(regexp = "\\d{11}", message = "Geçersiz TC Kimlik numarası! Lütfen 11 haneli bir numara girin.")
	private String identityNumber;
	
	@NotNull(message="Doğum yılı alanı boş bırakılamaz")
	private int birthYear;
	
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
	
	@NotNull(message="Okul bilgisi alanı boş bırakılamaz")
	private SchoolDto schoolDto;

}
