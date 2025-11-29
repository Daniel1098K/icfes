package com.parcial.saberpro.config;

import com.parcial.saberpro.entity.Rol;
import com.parcial.saberpro.entity.Usuario;
import com.parcial.saberpro.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@RequiredArgsConstructor
@Slf4j
public class DataInitializer implements CommandLineRunner {
    
    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;
    
    @Override
    @Transactional
    public void run(String... args) {
        try {
            log.info("===========================================");
            log.info("Iniciando configuración de base de datos...");
            log.info("===========================================");
            
            // Verificar conexión a la base de datos
            long totalUsuarios = usuarioRepository.count();
            log.info("Conexión exitosa a PostgreSQL. Usuarios existentes: {}", totalUsuarios);
            
            // Verificar si existen los usuarios correctos
            boolean adminExists = usuarioRepository.existsByUsername("admin");
            boolean estudianteExists = usuarioRepository.existsByUsername("estudiante");
            boolean docenteExists = usuarioRepository.existsByUsername("docente");
            
            if (adminExists && estudianteExists && docenteExists) {
                log.info("✓ Todos los usuarios requeridos ya existen");
                log.info("===========================================");
                return;
            }
            
            // Si hay usuarios pero no son los correctos, eliminarlos
            if (totalUsuarios > 0 && (!adminExists || !estudianteExists || !docenteExists)) {
                log.warn("Se encontraron usuarios incorrectos o incompletos");
                log.warn("Eliminando usuarios existentes para recrearlos...");
                usuarioRepository.deleteAll();
                log.info("✓ Usuarios antiguos eliminados");
            }
            
            // Crear usuario admin
            if (!usuarioRepository.existsByUsername("admin")) {
                try {
                    Usuario admin = new Usuario();
                    admin.setUsername("admin");
                    admin.setPassword(passwordEncoder.encode("admin123"));
                    admin.setNombreCompleto("Administrador del Sistema");
                    admin.setEmail("admin@uts.edu.co");
                    admin.setRol(Rol.ADMIN);
                    admin.setActivo(true);
                    
                    usuarioRepository.save(admin);
                    log.info("✓ Usuario ADMIN creado exitosamente");
                    log.info("  Username: admin");
                    log.info("  Password: admin123");
                } catch (Exception e) {
                    log.error("Error al crear usuario admin: {}", e.getMessage());
                    throw e;
                }
            }
            
            // Crear usuario estudiante
            if (!usuarioRepository.existsByUsername("estudiante")) {
                try {
                    Usuario estudiante = new Usuario();
                    estudiante.setUsername("estudiante");
                    estudiante.setPassword(passwordEncoder.encode("est123"));
                    estudiante.setNombreCompleto("Juan Pérez Estudiante");
                    estudiante.setEmail("estudiante@uts.edu.co");
                    estudiante.setRol(Rol.ESTUDIANTE);
                    estudiante.setActivo(true);
                    
                    usuarioRepository.save(estudiante);
                    log.info("✓ Usuario ESTUDIANTE creado exitosamente");
                    log.info("  Username: estudiante");
                    log.info("  Password: est123");
                } catch (Exception e) {
                    log.error("Error al crear usuario estudiante: {}", e.getMessage());
                    throw e;
                }
            }
            
            // Crear usuario docente
            if (!usuarioRepository.existsByUsername("docente")) {
                try {
                    Usuario docente = new Usuario();
                    docente.setUsername("docente");
                    docente.setPassword(passwordEncoder.encode("doc123"));
                    docente.setNombreCompleto("María García Docente");
                    docente.setEmail("docente@uts.edu.co");
                    docente.setRol(Rol.DOCENTE);
                    docente.setActivo(true);
                    
                    usuarioRepository.save(docente);
                    log.info("✓ Usuario DOCENTE creado exitosamente");
                    log.info("  Username: docente");
                    log.info("  Password: doc123");
                } catch (Exception e) {
                    log.error("Error al crear usuario docente: {}", e.getMessage());
                    throw e;
                }
            }
            
            // Mostrar todos los usuarios creados
            totalUsuarios = usuarioRepository.count();
            log.info("===========================================");
            log.info("✓✓✓ Base de datos inicializada correctamente ✓✓✓");
            log.info("Total de usuarios en la base de datos: {}", totalUsuarios);
            log.info("");
            log.info("CREDENCIALES DISPONIBLES:");
            log.info("  - admin / admin123");
            log.info("  - estudiante / est123");
            log.info("  - docente / doc123");
            log.info("===========================================");
            
        } catch (Exception e) {
            log.error("===========================================");
            log.error("❌ ERROR CRÍTICO al inicializar la base de datos");
            log.error("===========================================");
            log.error("Mensaje: {}", e.getMessage());
            log.error("Detalles:", e);
            log.error("===========================================");
            log.error("SOLUCIÓN SUGERIDA:");
            log.error("Conecta a PostgreSQL y ejecuta:");
            log.error("  DROP TABLE IF EXISTS usuarios CASCADE;");
            log.error("Luego reinicia la aplicación");
            log.error("===========================================");
        }
    }
}
