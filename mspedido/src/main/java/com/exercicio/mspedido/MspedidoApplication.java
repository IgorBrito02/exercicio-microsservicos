package com.exercicio.mspedido;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class MspedidoApplication {

	public static void main(String[] args) {
		SpringApplication.run(MspedidoApplication.class, args);
	}

}
