package it.airlab.component;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.util.Base64;

import org.springframework.stereotype.Component;

@Component
public class Token {
	
	public String tokenProfilo(String email) {
		LocalDateTime now = LocalDateTime.now();
		Instant instant = now.toInstant(OffsetDateTime.now().getOffset());
		long timestep = instant.getEpochSecond();
		String emailCodificato = Base64.getEncoder().encodeToString(email.getBytes());
		System.out.println(emailCodificato + "_" + timestep + "_profilo");
		return emailCodificato + "_" + timestep + "_profilo";
	}
}
