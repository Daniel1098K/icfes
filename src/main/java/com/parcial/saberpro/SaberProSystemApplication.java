package com.parcial.saberpro;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SaberProSystemApplication {
    
    public static void main(String[] args) {
        SpringApplication.run(SaberProSystemApplication.class, args);
        System.out.println("\n========================================");
        System.out.println("Sistema Saber Pro UTS iniciado correctamente");
        System.out.println("Accede a: http://localhost:8100");
        System.out.println("========================================\n");
    }
}
