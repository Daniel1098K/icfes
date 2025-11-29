package com.parcial.saberpro.controller;

import com.parcial.saberpro.entity.*;
import com.parcial.saberpro.service.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/coordinacion")
@RequiredArgsConstructor
public class CoordinacionController {
    
    private final EstudianteService estudianteService;
    private final ResultadoSaberProService resultadoService;
    
    @GetMapping("/dashboard")
    public String dashboard(Model model) {
        Map<String, Object> estadisticas = resultadoService.obtenerEstadisticas();
        model.addAttribute("estadisticas", estadisticas);
        
        List<ResultadoSaberPro> recientes = resultadoService.listarTodos();
        if (recientes.size() > 10) {
            recientes = recientes.subList(0, 10);
        }
        model.addAttribute("resultadosRecientes", recientes);
        
        return "coordinacion/dashboard";
    }
    
    // ========== CRUD ESTUDIANTES ==========
    
    @GetMapping("/estudiantes")
    public String listarEstudiantes(@RequestParam(required = false) String buscar, Model model) {
        List<Estudiante> estudiantes;
        if (buscar != null && !buscar.trim().isEmpty()) {
            estudiantes = estudianteService.buscarEstudiantes(buscar);
        } else {
            estudiantes = estudianteService.listarTodos();
        }
        model.addAttribute("estudiantes", estudiantes);
        model.addAttribute("buscar", buscar);
        return "coordinacion/estudiantes/lista";
    }
    
    @GetMapping("/estudiantes/nuevo")
    public String nuevoEstudiante(Model model) {
        model.addAttribute("estudiante", new Estudiante());
        model.addAttribute("tiposPrograma", TipoPrograma.values());
        return "coordinacion/estudiantes/formulario";
    }
    
    @PostMapping("/estudiantes/guardar")
    public String guardarEstudiante(@ModelAttribute Estudiante estudiante, 
                                   RedirectAttributes redirectAttributes) {
        try {
            estudianteService.guardarEstudiante(estudiante);
            redirectAttributes.addFlashAttribute("mensaje", "Estudiante guardado exitosamente");
            redirectAttributes.addFlashAttribute("tipoMensaje", "success");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("mensaje", "Error: " + e.getMessage());
            redirectAttributes.addFlashAttribute("tipoMensaje", "danger");
        }
        return "redirect:/coordinacion/estudiantes";
    }
    
    @GetMapping("/estudiantes/editar/{id}")
    public String editarEstudiante(@PathVariable Long id, Model model) {
        try {
            Estudiante estudiante = estudianteService.obtenerPorId(id);
            model.addAttribute("estudiante", estudiante);
            model.addAttribute("tiposPrograma", TipoPrograma.values());
            return "coordinacion/estudiantes/formulario";
        } catch (Exception e) {
            return "redirect:/coordinacion/estudiantes";
        }
    }
    
    @PostMapping("/estudiantes/actualizar/{id}")
    public String actualizarEstudiante(@PathVariable Long id, 
                                      @ModelAttribute Estudiante estudiante,
                                      RedirectAttributes redirectAttributes) {
        try {
            estudianteService.actualizarEstudiante(id, estudiante);
            redirectAttributes.addFlashAttribute("mensaje", "Estudiante actualizado exitosamente");
            redirectAttributes.addFlashAttribute("tipoMensaje", "success");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("mensaje", "Error: " + e.getMessage());
            redirectAttributes.addFlashAttribute("tipoMensaje", "danger");
        }
        return "redirect:/coordinacion/estudiantes";
    }
    
    @GetMapping("/estudiantes/eliminar/{id}")
    public String eliminarEstudiante(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        try {
            estudianteService.eliminarEstudiante(id);
            redirectAttributes.addFlashAttribute("mensaje", "Estudiante eliminado exitosamente");
            redirectAttributes.addFlashAttribute("tipoMensaje", "success");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("mensaje", "Error: " + e.getMessage());
            redirectAttributes.addFlashAttribute("tipoMensaje", "danger");
        }
        return "redirect:/coordinacion/estudiantes";
    }
    
    @GetMapping("/estudiantes/detalle/{id}")
    public String detalleEstudiante(@PathVariable Long id, Model model) {
        try {
            Estudiante estudiante = estudianteService.obtenerPorId(id);
            model.addAttribute("estudiante", estudiante);
            
            resultadoService.obtenerPorEstudianteId(id)
                .ifPresent(resultado -> model.addAttribute("resultado", resultado));
            
            return "coordinacion/estudiantes/detalle";
        } catch (Exception e) {
            return "redirect:/coordinacion/estudiantes";
        }
    }
    
    // ========== GESTIÓN DE RESULTADOS ==========
    
    @GetMapping("/resultados")
    public String listarResultados(Model model) {
        List<ResultadoSaberPro> resultados = resultadoService.listarTodos();
        model.addAttribute("resultados", resultados);
        return "coordinacion/resultados/lista";
    }
    
    @GetMapping("/resultados/nuevo/{estudianteId}")
    public String nuevoResultado(@PathVariable Long estudianteId, Model model) {
        try {
            Estudiante estudiante = estudianteService.obtenerPorId(estudianteId);
            ResultadoSaberPro resultado = new ResultadoSaberPro();
            resultado.setEstudiante(estudiante);
            
            model.addAttribute("resultado", resultado);
            model.addAttribute("estudiante", estudiante);
            return "coordinacion/resultados/formulario";
        } catch (Exception e) {
            return "redirect:/coordinacion/estudiantes";
        }
    }
    
    @PostMapping("/resultados/guardar/{estudianteId}")
    public String guardarResultado(@PathVariable Long estudianteId,
                                  @ModelAttribute ResultadoSaberPro resultado,
                                  RedirectAttributes redirectAttributes) {
        try {
            resultadoService.registrarResultado(estudianteId, resultado);
            redirectAttributes.addFlashAttribute("mensaje", "Resultado registrado exitosamente");
            redirectAttributes.addFlashAttribute("tipoMensaje", "success");
            return "redirect:/coordinacion/estudiantes/detalle/" + estudianteId;
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("mensaje", "Error: " + e.getMessage());
            redirectAttributes.addFlashAttribute("tipoMensaje", "danger");
            return "redirect:/coordinacion/estudiantes";
        }
    }
    
    @GetMapping("/resultados/editar/{id}")
    public String editarResultado(@PathVariable Long id, Model model) {
        try {
            ResultadoSaberPro resultado = resultadoService.obtenerPorId(id);
            model.addAttribute("resultado", resultado);
            model.addAttribute("estudiante", resultado.getEstudiante());
            return "coordinacion/resultados/formulario";
        } catch (Exception e) {
            return "redirect:/coordinacion/resultados";
        }
    }
    
    @PostMapping("/resultados/actualizar/{id}")
    public String actualizarResultado(@PathVariable Long id,
                                     @ModelAttribute ResultadoSaberPro resultado,
                                     RedirectAttributes redirectAttributes) {
        try {
            ResultadoSaberPro actualizado = resultadoService.actualizarResultado(id, resultado);
            redirectAttributes.addFlashAttribute("mensaje", "Resultado actualizado exitosamente");
            redirectAttributes.addFlashAttribute("tipoMensaje", "success");
            return "redirect:/coordinacion/estudiantes/detalle/" + actualizado.getEstudiante().getId();
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("mensaje", "Error: " + e.getMessage());
            redirectAttributes.addFlashAttribute("tipoMensaje", "danger");
            return "redirect:/coordinacion/resultados";
        }
    }
    
    // ========== INFORMES ==========
    
    @GetMapping("/informes/general")
    public String informeGeneral(Model model) {
        List<ResultadoSaberPro> resultados = resultadoService.listarTodos();
        Map<String, Object> estadisticas = resultadoService.obtenerEstadisticas();
        
        model.addAttribute("resultados", resultados);
        model.addAttribute("estadisticas", estadisticas);
        return "coordinacion/informes/general";
    }
    
    @GetMapping("/informes/beneficios")
    public String informeBeneficios(Model model) {
        List<ResultadoSaberPro> conBeneficios = resultadoService.listarConBeneficios();
        model.addAttribute("resultados", conBeneficios);
        return "coordinacion/informes/beneficios";
    }
    
    @GetMapping("/informes/no-aprobados")
    public String informeNoAprobados(Model model) {
        List<ResultadoSaberPro> noAprobados = resultadoService.listarNoAprobados();
        model.addAttribute("resultados", noAprobados);
        return "coordinacion/informes/no-aprobados";
    }
}
