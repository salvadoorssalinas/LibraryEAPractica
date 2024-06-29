package com.library.platform.upc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class Practica2Application {

    public static void main(String[] args) {
        SpringApplication.run(Practica2Application.class, args);
    }

}
