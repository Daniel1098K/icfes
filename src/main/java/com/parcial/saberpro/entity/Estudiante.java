package com.parcial.saberpro.entity;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;

@Entity
@Table(name = "estudiantes")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Estudiante {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(nullable = false, unique = true, length = 20)
    private String numeroDocumento;
    
    @Column(length = 100)
    private String primerNombre;
    
    @Column(length = 100)
    private String segundoNombre;
    
    @Column(nullable = false, length = 100)
    private String primerApellido;
    
    @Column(length = 100)
    private String segundoApellido;
    
    @Column(unique = true, length = 100)
    private String correoElectronico;
    
    @Column(length = 20)
    private String numeroTelefonico;
    
    @Column(length = 20)
    private String numeroRegistro;
    
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TipoPrograma tipoPrograma;
    
    @Column(length = 200)
    private String programa;
    
    @OneToOne(mappedBy = "estudiante", cascade = CascadeType.ALL, orphanRemoval = true)
    private ResultadoSaberPro resultado;
    
    @OneToOne
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;
    
    @Column(name = "fecha_registro")
    private LocalDate fechaRegistro;
    
    @PrePersist
    protected void onCreate() {
        fechaRegistro = LocalDate.now();
    }
    
    public String getNombreCompleto() {
        StringBuilder nombre = new StringBuilder();
        if (primerNombre != null) nombre.append(primerNombre).append(" ");
        if (segundoNombre != null) nombre.append(segundoNombre).append(" ");
        if (primerApellido != null) nombre.append(primerApellido).append(" ");
        if (segundoApellido != null) nombre.append(segundoApellido);
        return nombre.toString().trim();
    }
}
