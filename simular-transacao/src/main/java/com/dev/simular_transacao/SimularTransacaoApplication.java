package com.dev.simular_transacao;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class SimularTransacaoApplication {

	public static void main(String[] args) {
		SpringApplication.run(SimularTransacaoApplication.class, args);
	}

}
