package com.parcial.saberpro.config;

import com.parcial.saberpro.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class DataInitializer implements CommandLineRunner {
    
    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;
    
    @Override
    public void run(String... args) {
        // Se ha vaciado este método para evitar la creación de datos de prueba.
        // Hibernate se encargará de crear el esquema de la base de datos.
        log.info("DataInitializer ejecutado, pero no se insertaron datos de prueba.");
    }
}
