package br.com.fiap.apisecurity;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class ApiSecurityApplication {

    public static void main(String[] args) {
        SpringApplication.run(ApiSecurityApplication.class, args);
    }

}
