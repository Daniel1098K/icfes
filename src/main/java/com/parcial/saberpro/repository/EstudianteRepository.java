package com.parcial.saberpro.repository;

import com.parcial.saberpro.entity.Estudiante;
import com.parcial.saberpro.entity.TipoPrograma;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface EstudianteRepository extends JpaRepository<Estudiante, Long> {
    Optional<Estudiante> findByNumeroDocumento(String numeroDocumento);
    Optional<Estudiante> findByNumeroRegistro(String numeroRegistro);
    Optional<Estudiante> findByCorreoElectronico(String correo);
    Optional<Estudiante> findByUsuarioId(Long usuarioId);
    
    List<Estudiante> findByTipoPrograma(TipoPrograma tipoPrograma);
    
    @Query("SELECT e FROM Estudiante e WHERE " +
           "LOWER(e.primerNombre) LIKE LOWER(CONCAT('%', :busqueda, '%')) OR " +
           "LOWER(e.primerApellido) LIKE LOWER(CONCAT('%', :busqueda, '%')) OR " +
           "LOWER(e.numeroDocumento) LIKE LOWER(CONCAT('%', :busqueda, '%')) OR " +
           "LOWER(e.numeroRegistro) LIKE LOWER(CONCAT('%', :busqueda, '%'))")
    List<Estudiante> buscarEstudiantes(String busqueda);
    
    boolean existsByNumeroDocumento(String numeroDocumento);
    boolean existsByCorreoElectronico(String correo);
}
