package com.snhu.sslserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

@SpringBootApplication
@RestController
public class SslServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(SslServerApplication.class, args);
	}
	//FIXME: Add route to enable check sum return of static data example:  String data = "Hello World Check Sum!";
	
	@GetMapping("/hash") 
	public String getChecksum() {
		String name = "Armon Wilson";
		String data = "Hello World Check Sum!";
		try {
			MessageDigest md = MessageDigest.getInstance("SHA-256"); 
			md.update(data.getBytes());
			byte[] digest = md.digest();

			StringBuilder hexString = new StringBuilder();
			for (byte b : digest) {
				hexString.append(String.format("%02x", b));
			}
			return name + " " + data + " " + hexString.toString();
		} 
		catch (NoSuchAlgorithmException e) {
			e.printStackTrace();

			return "Error calculating checksum";
		}
	}
}
