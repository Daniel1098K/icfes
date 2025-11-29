package com.parcial.saberpro.repository;

import com.parcial.saberpro.entity.ResultadoSaberPro;
import com.parcial.saberpro.entity.NivelBeneficio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface ResultadoSaberProRepository extends JpaRepository<ResultadoSaberPro, Long> {
    Optional<ResultadoSaberPro> findByEstudianteId(Long estudianteId);
    
    List<ResultadoSaberPro> findByNivelBeneficio(NivelBeneficio nivelBeneficio);
    
    List<ResultadoSaberPro> findByPuntajeGreaterThanEqual(Integer puntaje);
    
    @Query("SELECT r FROM ResultadoSaberPro r WHERE r.puedeGraduar = false")
    List<ResultadoSaberPro> findEstudiantesNoAprobados();
    
    @Query("SELECT r FROM ResultadoSaberPro r WHERE r.nivelBeneficio IN " +
           "('BUENO', 'SOBRESALIENTE', 'EXCELENTE')")
    List<ResultadoSaberPro> findEstudiantesConBeneficios();
    
    @Query("SELECT AVG(r.puntaje) FROM ResultadoSaberPro r")
    Double calcularPromedioPuntajes();
    
    @Query("SELECT COUNT(r) FROM ResultadoSaberPro r WHERE r.puedeGraduar = false")
    Long contarNoAprobados();
    
    @Query("SELECT COUNT(r) FROM ResultadoSaberPro r WHERE r.nivelBeneficio = :nivel")
    Long contarPorNivelBeneficio(NivelBeneficio nivel);
}
