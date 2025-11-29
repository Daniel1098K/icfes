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
        try {
            log.info("===========================================");
            log.info("Iniciando configuración de base de datos...");
            log.info("===========================================");
            
            // Verificar conexión a la base de datos
            long totalUsuarios = usuarioRepository.count();
            log.info("Conexión exitosa a PostgreSQL. Usuarios existentes: {}", totalUsuarios);
            
            // Crear usuario admin
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
                log.info("✓ Usuario ADMIN creado exitosamente");
                log.info("  Username: admin");
                log.info("  Password: admin123");
            } else {
                log.info("✓ Usuario admin ya existe");
            }
            
            // Crear usuario estudiante
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
                log.info("✓ Usuario ESTUDIANTE creado exitosamente");
                log.info("  Username: estudiante");
                log.info("  Password: est123");
            } else {
                log.info("✓ Usuario estudiante ya existe");
            }
            
            // Crear usuario docente
            if (!usuarioRepository.existsByUsername("docente")) {
                Usuario docente = Usuario.builder()
                    .username("docente")
                    .password(passwordEncoder.encode("doc123"))
                    .nombreCompleto("María García Docente")
                    .email("docente@uts.edu.co")
                    .rol(Rol.DOCENTE)
                    .activo(true)
                    .build();
                
                usuarioRepository.save(docente);
                log.info("✓ Usuario DOCENTE creado exitosamente");
                log.info("  Username: docente");
                log.info("  Password: doc123");
            } else {
                log.info("✓ Usuario docente ya existe");
            }
            
            // Mostrar todos los usuarios
            totalUsuarios = usuarioRepository.count();
            log.info("===========================================");
            log.info("Base de datos inicializada correctamente");
            log.info("Total de usuarios en la base de datos: {}", totalUsuarios);
            log.info("===========================================");
            
        } catch (Exception e) {
            log.error("ERROR al inicializar la base de datos: {}", e.getMessage());
            log.error("Verifica la conexión a PostgreSQL");
            e.printStackTrace();
        }
    }
}
