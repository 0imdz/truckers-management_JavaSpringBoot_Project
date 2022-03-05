package com.eep;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan({"com.eep.*"})//se escanean todos los paquetes
public class EjercicioCamionerosCompletoApplication {

	public static void main(String[] args) {
		SpringApplication.run(EjercicioCamionerosCompletoApplication.class, args);
	}

}
