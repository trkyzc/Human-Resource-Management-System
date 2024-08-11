package com.example.hrms.security;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.codec.Base64;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

//Service

@Service
public class JwtProvider {
	
	@Value("${jwt.key}")
	private String SECRET;  //base64 formatındaki secret key
	
	
	
	public String generateToken(String username) {
	    Map<String, Object> claims = new HashMap<>();
	    return Jwts.builder()
	    		.setClaims(claims)
	    		.setSubject(username)
	    		.setIssuedAt(new Date(System.currentTimeMillis()))
	    		.setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 )) //1 saat
	    		.signWith(getSignKey(),SignatureAlgorithm.HS256)  //belirtilen algoritmayı ve anahtarı kullanarak JWT'yi imzalar. 
	    		.compact();
    }

	
	
	public boolean validateToken(String token, UserDetails userDetails) {
		
		String userName = extractUsername(token);
		Date expirationDate = expirationDate(token);
		return expirationDate.after(new Date()) && userDetails.getUsername().equals(userName);
		
	}

	private Key getSignKey() {
		
		byte[] keyBytes = Decoders.BASE64.decode(SECRET); //secretı base64 den çözüp byte dizisine dönüştürür.
		Key key = Keys.hmacShaKeyFor(keyBytes);  //Bu bayt dizisini kullanarak HMAC-SHA algoritmasını destekleyen bir anahtar oluşturur.
		return key;
	}

	private Date expirationDate(String token) {
		
		return Jwts.parserBuilder()  
				.setSigningKey(getSignKey())  //JWT'nin imzasını doğrulamak için kullanılacak olan gizli anahtarı (SecretKey) ayarlar. 
				.build()
				.parseClaimsJws(token)  //JWT'yi ayrıştırır ve JWT'nin içeriğini temsil eden Claims nesnesini döndürür.
				.getBody()
				.getExpiration();
	}

	public String extractUsername(String token) {
		
		return Jwts.parserBuilder()
				.setSigningKey(getSignKey())
				.build()
				.parseClaimsJws(token)
				.getBody()
				.getSubject();
	}

	

}


//signWith(): 
//Bu metod, belirtilen algoritmayı ve anahtarı kullanarak JWT'yi imzalar. 
//Token oluşturulduğunda, bu imza token'ın içeriğinin değiştirilmediğinden emin olmanızı sağlar. 
//Yani, bir kullanıcı token'ı ele geçirse bile, imza olmadan token'ın içeriğini değiştiremeyecek ve bu değişiklikler imza doğrulamasını geçmeyecektir.

//Eğer birisi, token'ı değiştirebilir ve imzayı yeniden oluşturabilirse, bu kişi yetkileri genişletebilir veya kısıtlamaları kaldırabilir. 
//Örneğin, normalde sadece kullanıcı erişimi olan bir token, değiştirilerek admin erişimi kazanabilir.

