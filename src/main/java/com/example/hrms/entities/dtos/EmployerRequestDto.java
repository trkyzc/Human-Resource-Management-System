package com.example.hrms.entities.dtos;

import org.hibernate.validator.constraints.URL;
import com.example.hrms.validation.UniqueEmail;
import com.example.hrms.validation.UniqueUsername;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;



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
	
	public EmployerRequestDto() {

	}
	
	public EmployerRequestDto(String username, String email, String password, String repeatedPassword,
			String companyName, String webAddress, String phoneNumber) {
		super();
		this.username = username;
		this.email = email;
		this.password = password;
		this.repeatedPassword = repeatedPassword;
		this.companyName = companyName;
		this.webAddress = webAddress;
		this.phoneNumber = phoneNumber;
	}
	
	public String getUsername() {
		return username;
	}
	
	public void setUsername(String username) {
		this.username = username;
	}
	
	public String getEmail() {
		return email;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getPassword() {
		return password;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	
	public String getRepeatedPassword() {
		return repeatedPassword;
	}
	
	public void setRepeatedPassword(String repeatedPassword) {
		this.repeatedPassword = repeatedPassword;
	}
	
	public String getCompanyName() {
		return companyName;
	}
	
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
	
	public String getWebAddress() {
		return webAddress;
	}
	
	public void setWebAddress(String webAddress) {
		this.webAddress = webAddress;
	}
	
	public String getPhoneNumber() {
		return phoneNumber;
	}
	
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	
	


}
