package com.projetofinal.zeldaservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.http.HttpHeaders;


@SpringBootApplication
public class ZeldaServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(ZeldaServiceApplication.class, args);
    }

}
