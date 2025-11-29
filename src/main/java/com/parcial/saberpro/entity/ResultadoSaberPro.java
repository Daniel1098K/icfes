package com.parcial.saberpro.entity;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;

@Entity
@Table(name = "resultados_saber_pro")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ResultadoSaberPro {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @OneToOne
    @JoinColumn(name = "estudiante_id", nullable = false, unique = true)
    private Estudiante estudiante;
    
    @Column(nullable = false)
    private Integer puntaje;
    
    // Competencias específicas (según la imagen de Excel)
    private Integer comunicacionEscrita;
    private Integer comunicacionEscritaNivel;
    private Integer razonamientoCuantitativo;
    private Integer razonamientoCuantitativoNivel;
    private Integer lecturaCritica;
    private Integer lecturaCriticaNivel;
    private Integer competenciasCiudadanas;
    private Integer competenciasCiudadanasNivel;
    private Integer ingles;
    private Integer inglesNivel;
    
    // Competencias específicas del programa
    private Integer formulacionProyectos;
    private Integer formulacionProyectosNivel;
    private Integer pensamientoCientifico;
    private Integer pensamientoCientificoNivel;
    private Integer disenoSoftware;
    private Integer disenoSoftwareNivel;
    
    @Column(name = "fecha_presentacion")
    private LocalDate fechaPresentacion;
    
    @Column(name = "fecha_registro_resultado")
    private LocalDate fechaRegistroResultado;
    
    @Enumerated(EnumType.STRING)
    private NivelBeneficio nivelBeneficio;
    
    @Column(length = 500)
    private String descripcionBeneficio;
    
    @Column
    private Double notaExoneracion;
    
    @Column
    private Integer porcentajeBeca;
    
    @Column
    private Boolean puedeGraduar;
    
    @PrePersist
    protected void onCreate() {
        fechaRegistroResultado = LocalDate.now();
        calcularBeneficios();
    }
    
    @PreUpdate
    protected void onUpdate() {
        calcularBeneficios();
    }
    
    private void calcularBeneficios() {
        if (puntaje == null) return;
        
        // Verificar si puede graduarse
        puedeGraduar = puntaje >= 80;
        
        if (!puedeGraduar) {
            nivelBeneficio = NivelBeneficio.NO_APROBADO;
            descripcionBeneficio = "No cumple con el puntaje mínimo para graduarse (80 puntos)";
            notaExoneracion = null;
            porcentajeBeca = 0;
            return;
        }
        
        // Calcular beneficios según el tipo de programa
        TipoPrograma tipo = estudiante.getTipoPrograma();
        
        if (tipo == TipoPrograma.PROFESIONAL) {
            // Saber PRO (0-300 puntos)
            if (puntaje >= 241) {
                nivelBeneficio = NivelBeneficio.EXCELENTE;
                notaExoneracion = 5.0;
                porcentajeBeca = 100;
                descripcionBeneficio = "Exoneración de Seminario de grado IV con nota 5.0 + 100% beca derechos de grado";
            } else if (puntaje >= 211) {
                nivelBeneficio = NivelBeneficio.SOBRESALIENTE;
                notaExoneracion = 4.7;
                porcentajeBeca = 50;
                descripcionBeneficio = "Exoneración de Seminario de grado IV con nota 4.7 + 50% beca derechos de grado";
            } else if (puntaje >= 180) {
                nivelBeneficio = NivelBeneficio.BUENO;
                notaExoneracion = 4.5;
                porcentajeBeca = 0;
                descripcionBeneficio = "Exoneración de Seminario de grado IV con nota 4.5";
            } else {
                nivelBeneficio = NivelBeneficio.APROBADO;
                notaExoneracion = null;
                porcentajeBeca = 0;
                descripcionBeneficio = "Sin beneficios adicionales";
            }
        } else {
            // Saber T&T (0-200 puntos)
            if (puntaje >= 171) {
                nivelBeneficio = NivelBeneficio.EXCELENTE;
                notaExoneracion = 5.0;
                porcentajeBeca = 100;
                descripcionBeneficio = "Exoneración de Seminario de grado II con nota 5.0 + 100% beca derechos de grado";
            } else if (puntaje >= 151) {
                nivelBeneficio = NivelBeneficio.SOBRESALIENTE;
                notaExoneracion = 4.7;
                porcentajeBeca = 50;
                descripcionBeneficio = "Exoneración de Seminario de grado II con nota 4.7 + 50% beca derechos de grado";
            } else if (puntaje >= 120) {
                nivelBeneficio = NivelBeneficio.BUENO;
                notaExoneracion = 4.5;
                porcentajeBeca = 0;
                descripcionBeneficio = "Exoneración de Seminario de grado II con nota 4.5";
            } else {
                nivelBeneficio = NivelBeneficio.APROBADO;
                notaExoneracion = null;
                porcentajeBeca = 0;
                descripcionBeneficio = "Sin beneficios adicionales";
            }
        }
    }
}
