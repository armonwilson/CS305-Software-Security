package com.snhu.sslserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

@SpringBootApplication
public class ServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(ServerApplication.class, args);
	}

}

@RestController
class ServerController{
//FIXME:  Add hash function to return the checksum value for the data string that should contain your name.  
	
	private static String ALGORITHM = "SHA-256";
	
    @RequestMapping("/hash")
    public String myHash() throws NoSuchAlgorithmException {
    	String data = "This is Armon Wilson";
        MessageDigest md = MessageDigest.getInstance(ALGORITHM);
        byte[] hashBytes = md.digest(data.getBytes(StandardCharsets.UTF_8));
        String checksum = bytesToHex(hashBytes);
        return "<p>data: " + data + "</p>" +
        "<p>algorithm: " + ALGORITHM + "</p>" +
        "<p>checksum: " + checksum + "</p>";
    }
    
    public String bytesToHex(byte[] hash) {
    	StringBuilder hexString = new StringBuilder(2*hash.length);
    	for (byte b: hash) {
    		String hex = Integer.toHexString(0xff & b);
    		if (hex.length() == 1) {
    			hexString.append('0');    		
    		}
    		hexString.append(hex);
    	}
    	return hexString.toString();
    }
}
