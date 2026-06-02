// src/main/java/com/transfert/TransfertEtudiantApplication.java
package com.transfert;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync
public class TransfertEtudiantApplication {
    public static void main(String[] args) {
        SpringApplication.run(TransfertEtudiantApplication.class, args);
    }
}