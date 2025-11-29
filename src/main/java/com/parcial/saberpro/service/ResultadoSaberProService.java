package com.parcial.saberpro.service;

import com.parcial.saberpro.entity.*;
import com.parcial.saberpro.repository.ResultadoSaberProRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.time.LocalDate;
import java.util.*;

@Service
@RequiredArgsConstructor
public class ResultadoSaberProService {
    
    private final ResultadoSaberProRepository resultadoRepository;
    private final EstudianteService estudianteService;
    
    @Transactional
    public ResultadoSaberPro registrarResultado(Long estudianteId, ResultadoSaberPro resultado) {
        Estudiante estudiante = estudianteService.obtenerPorId(estudianteId);
        
        // Validar puntaje según tipo de programa
        validarPuntaje(resultado.getPuntaje(), estudiante.getTipoPrograma());
        
        // Verificar si ya existe un resultado
        Optional<ResultadoSaberPro> existente = resultadoRepository.findByEstudianteId(estudianteId);
        if (existente.isPresent()) {
            throw new RuntimeException("Ya existe un resultado registrado para este estudiante");
        }
        
        resultado.setEstudiante(estudiante);
        resultado.setFechaPresentacion(resultado.getFechaPresentacion() != null ? 
            resultado.getFechaPresentacion() : LocalDate.now());
        
        return resultadoRepository.save(resultado);
    }
    
    @Transactional
    public ResultadoSaberPro actualizarResultado(Long resultadoId, ResultadoSaberPro resultadoActualizado) {
        ResultadoSaberPro resultado = obtenerPorId(resultadoId);
        
        validarPuntaje(resultadoActualizado.getPuntaje(), resultado.getEstudiante().getTipoPrograma());
        
        resultado.setPuntaje(resultadoActualizado.getPuntaje());
        resultado.setComunicacionEscrita(resultadoActualizado.getComunicacionEscrita());
        resultado.setComunicacionEscritaNivel(resultadoActualizado.getComunicacionEscritaNivel());
        resultado.setRazonamientoCuantitativo(resultadoActualizado.getRazonamientoCuantitativo());
        resultado.setRazonamientoCuantitativoNivel(resultadoActualizado.getRazonamientoCuantitativoNivel());
        resultado.setLecturaCritica(resultadoActualizado.getLecturaCritica());
        resultado.setLecturaCriticaNivel(resultadoActualizado.getLecturaCriticaNivel());
        resultado.setCompetenciasCiudadanas(resultadoActualizado.getCompetenciasCiudadanas());
        resultado.setCompetenciasCiudadanasNivel(resultadoActualizado.getCompetenciasCiudadanasNivel());
        resultado.setIngles(resultadoActualizado.getIngles());
        resultado.setInglesNivel(resultadoActualizado.getInglesNivel());
        resultado.setFormulacionProyectos(resultadoActualizado.getFormulacionProyectos());
        resultado.setFormulacionProyectosNivel(resultadoActualizado.getFormulacionProyectosNivel());
        resultado.setPensamientoCientifico(resultadoActualizado.getPensamientoCientifico());
        resultado.setPensamientoCientificoNivel(resultadoActualizado.getPensamientoCientificoNivel());
        resultado.setDisenoSoftware(resultadoActualizado.getDisenoSoftware());
        resultado.setDisenoSoftwareNivel(resultadoActualizado.getDisenoSoftwareNivel());
        resultado.setFechaPresentacion(resultadoActualizado.getFechaPresentacion());
        
        return resultadoRepository.save(resultado);
    }
    
    public ResultadoSaberPro obtenerPorId(Long id) {
        return resultadoRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Resultado no encontrado con ID: " + id));
    }
    
    public Optional<ResultadoSaberPro> obtenerPorEstudianteId(Long estudianteId) {
        return resultadoRepository.findByEstudianteId(estudianteId);
    }
    
    public List<ResultadoSaberPro> listarTodos() {
        return resultadoRepository.findAll();
    }
    
    public List<ResultadoSaberPro> listarConBeneficios() {
        return resultadoRepository.findEstudiantesConBeneficios();
    }
    
    public List<ResultadoSaberPro> listarNoAprobados() {
        return resultadoRepository.findEstudiantesNoAprobados();
    }
    
    public Map<String, Object> obtenerEstadisticas() {
        Map<String, Object> estadisticas = new HashMap<>();
        
        List<ResultadoSaberPro> todos = listarTodos();
        estadisticas.put("total", todos.size());
        
        Double promedio = resultadoRepository.calcularPromedioPuntajes();
        estadisticas.put("promedioGeneral", promedio != null ? Math.round(promedio * 100.0) / 100.0 : 0);
        
        estadisticas.put("noAprobados", resultadoRepository.contarNoAprobados());
        estadisticas.put("aprobados", resultadoRepository.contarPorNivelBeneficio(NivelBeneficio.APROBADO));
        estadisticas.put("buenos", resultadoRepository.contarPorNivelBeneficio(NivelBeneficio.BUENO));
        estadisticas.put("sobresalientes", resultadoRepository.contarPorNivelBeneficio(NivelBeneficio.SOBRESALIENTE));
        estadisticas.put("excelentes", resultadoRepository.contarPorNivelBeneficio(NivelBeneficio.EXCELENTE));
        
        // Calcular puntaje más alto y más bajo
        if (!todos.isEmpty()) {
            OptionalInt max = todos.stream().mapToInt(ResultadoSaberPro::getPuntaje).max();
            OptionalInt min = todos.stream().mapToInt(ResultadoSaberPro::getPuntaje).min();
            estadisticas.put("puntajeMaximo", max.orElse(0));
            estadisticas.put("puntajeMinimo", min.orElse(0));
        }
        
        return estadisticas;
    }
    
    private void validarPuntaje(Integer puntaje, TipoPrograma tipoPrograma) {
        if (puntaje == null || puntaje < 0) {
            throw new RuntimeException("El puntaje debe ser mayor o igual a 0");
        }
        
        int maxPuntaje = tipoPrograma.getPuntajeMaximo();
        if (puntaje > maxPuntaje) {
            throw new RuntimeException(String.format(
                "El puntaje no puede ser mayor a %d para programas %s", 
                maxPuntaje, tipoPrograma.getNombre()));
        }
    }
}
