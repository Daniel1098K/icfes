package com.parcial.saberpro.config;

import com.parcial.saberpro.entity.Rol;
import com.parcial.saberpro.entity.Usuario;
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
        // Verificar si ya existe el usuario admin
        if (!usuarioRepository.existsByUsername("admin")) {
            Usuario admin = Usuario.builder()
                .username("admin")
                .password(passwordEncoder.encode("admin123"))
                .nombreCompleto("Administrador del Sistema")
                .email("admin@uts.edu.co")
                .rol(Rol.ADMIN)
                .activo(true)
                .build();
            
            usuarioRepository.save(admin);
            log.info("✓ Usuario admin creado exitosamente");
            log.info("  Username: admin");
            log.info("  Password: admin123");
        }
        
        // Crear usuario estudiante de prueba
        if (!usuarioRepository.existsByUsername("estudiante")) {
            Usuario estudiante = Usuario.builder()
                .username("estudiante")
                .password(passwordEncoder.encode("est123"))
                .nombreCompleto("Juan Pérez Estudiante")
                .email("estudiante@uts.edu.co")
                .rol(Rol.ESTUDIANTE)
                .activo(true)
                .build();
            
            usuarioRepository.save(estudiante);
            log.info("✓ Usuario estudiante creado exitosamente");
            log.info("  Username: estudiante");
            log.info("  Password: est123");
        }
        
        log.info("===========================================");
        log.info("Base de datos inicializada correctamente");
        log.info("===========================================");
    }
}
