package com.parcial.saberpro.controller;

import com.parcial.saberpro.entity.*;
import com.parcial.saberpro.service.*;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/estudiante")
@RequiredArgsConstructor
public class EstudianteController {
    
    private final EstudianteService estudianteService;
    private final ResultadoSaberProService resultadoService;
    private final UsuarioService usuarioService;
    
    @GetMapping("/dashboard")
    public String dashboard(Authentication authentication, Model model) {
        try {
            Usuario usuario = usuarioService.obtenerPorUsername(authentication.getName());
            Estudiante estudiante = estudianteService.buscarPorUsuarioId(usuario.getId())
                .orElse(null);
            
            model.addAttribute("estudiante", estudiante);
            
            if (estudiante != null) {
                resultadoService.obtenerPorEstudianteId(estudiante.getId())
                    .ifPresent(resultado -> model.addAttribute("resultado", resultado));
            }
            
            return "estudiante/dashboard";
        } catch (Exception e) {
            model.addAttribute("error", "Error al cargar la información");
            return "estudiante/dashboard";
        }
    }
    
    @GetMapping("/mi-resultado")
    public String miResultado(Authentication authentication, Model model) {
        try {
            Usuario usuario = usuarioService.obtenerPorUsername(authentication.getName());
            Estudiante estudiante = estudianteService.buscarPorUsuarioId(usuario.getId())
                .orElseThrow(() -> new RuntimeException("Estudiante no encontrado"));
            
            ResultadoSaberPro resultado = resultadoService.obtenerPorEstudianteId(estudiante.getId())
                .orElse(null);
            
            model.addAttribute("estudiante", estudiante);
            model.addAttribute("resultado", resultado);
            
            return "estudiante/mi-resultado";
        } catch (Exception e) {
            model.addAttribute("error", e.getMessage());
            return "estudiante/mi-resultado";
        }
    }
    
    @GetMapping("/mis-beneficios")
    public String misBeneficios(Authentication authentication, Model model) {
        try {
            Usuario usuario = usuarioService.obtenerPorUsername(authentication.getName());
            Estudiante estudiante = estudianteService.buscarPorUsuarioId(usuario.getId())
                .orElseThrow(() -> new RuntimeException("Estudiante no encontrado"));
            
            ResultadoSaberPro resultado = resultadoService.obtenerPorEstudianteId(estudiante.getId())
                .orElse(null);
            
            model.addAttribute("estudiante", estudiante);
            model.addAttribute("resultado", resultado);
            
            return "estudiante/mis-beneficios";
        } catch (Exception e) {
            model.addAttribute("error", e.getMessage());
            return "estudiante/mis-beneficios";
        }
    }
}
