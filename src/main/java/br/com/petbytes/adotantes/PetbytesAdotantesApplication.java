package br.com.petbytes.adotantes;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class PetbytesAdotantesApplication {

	public static void main(String[] args) {
		SpringApplication.run(PetbytesAdotantesApplication.class, args);
	}

}
