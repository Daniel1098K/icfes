package com.parcial.saberpro.service;

import com.parcial.saberpro.entity.Estudiante;
import com.parcial.saberpro.entity.TipoPrograma;
import com.parcial.saberpro.repository.EstudianteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class EstudianteService {
    
    private final EstudianteRepository estudianteRepository;
    
    @Transactional
    public Estudiante guardarEstudiante(Estudiante estudiante) {
        validarEstudiante(estudiante);
        return estudianteRepository.save(estudiante);
    }
    
    @Transactional
    public Estudiante actualizarEstudiante(Long id, Estudiante estudianteActualizado) {
        Estudiante estudiante = obtenerPorId(id);
        
        estudiante.setPrimerNombre(estudianteActualizado.getPrimerNombre());
        estudiante.setSegundoNombre(estudianteActualizado.getSegundoNombre());
        estudiante.setPrimerApellido(estudianteActualizado.getPrimerApellido());
        estudiante.setSegundoApellido(estudianteActualizado.getSegundoApellido());
        estudiante.setCorreoElectronico(estudianteActualizado.getCorreoElectronico());
        estudiante.setNumeroTelefonico(estudianteActualizado.getNumeroTelefonico());
        estudiante.setNumeroRegistro(estudianteActualizado.getNumeroRegistro());
        estudiante.setTipoPrograma(estudianteActualizado.getTipoPrograma());
        estudiante.setPrograma(estudianteActualizado.getPrograma());
        
        return estudianteRepository.save(estudiante);
    }
    
    @Transactional
    public void eliminarEstudiante(Long id) {
        if (!estudianteRepository.existsById(id)) {
            throw new RuntimeException("Estudiante no encontrado con ID: " + id);
        }
        estudianteRepository.deleteById(id);
    }
    
    public Estudiante obtenerPorId(Long id) {
        return estudianteRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Estudiante no encontrado con ID: " + id));
    }
    
    public Optional<Estudiante> buscarPorDocumento(String numeroDocumento) {
        return estudianteRepository.findByNumeroDocumento(numeroDocumento);
    }
    
    public Optional<Estudiante> buscarPorCorreo(String correo) {
        return estudianteRepository.findByCorreoElectronico(correo);
    }
    
    public Optional<Estudiante> buscarPorUsuarioId(Long usuarioId) {
        return estudianteRepository.findByUsuarioId(usuarioId);
    }
    
    public List<Estudiante> listarTodos() {
        return estudianteRepository.findAll();
    }
    
    public List<Estudiante> listarPorTipoPrograma(TipoPrograma tipoPrograma) {
        return estudianteRepository.findByTipoPrograma(tipoPrograma);
    }
    
    public List<Estudiante> buscarEstudiantes(String termino) {
        if (termino == null || termino.trim().isEmpty()) {
            return listarTodos();
        }
        return estudianteRepository.buscarEstudiantes(termino.trim());
    }
    
    private void validarEstudiante(Estudiante estudiante) {
        if (estudiante.getNumeroDocumento() == null || estudiante.getNumeroDocumento().trim().isEmpty()) {
            throw new RuntimeException("El número de documento es obligatorio");
        }
        
        if (estudiante.getPrimerApellido() == null || estudiante.getPrimerApellido().trim().isEmpty()) {
            throw new RuntimeException("El primer apellido es obligatorio");
        }
        
        if (estudiante.getTipoPrograma() == null) {
            throw new RuntimeException("El tipo de programa es obligatorio");
        }
        
        // Validar documentos únicos
        if (estudiante.getId() == null) {
            if (estudianteRepository.existsByNumeroDocumento(estudiante.getNumeroDocumento())) {
                throw new RuntimeException("Ya existe un estudiante con ese número de documento");
            }
            if (estudiante.getCorreoElectronico() != null && 
                estudianteRepository.existsByCorreoElectronico(estudiante.getCorreoElectronico())) {
                throw new RuntimeException("Ya existe un estudiante con ese correo electrónico");
            }
        }
    }
}
