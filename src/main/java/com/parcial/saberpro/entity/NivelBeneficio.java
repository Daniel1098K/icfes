package com.parcial.saberpro.entity;

public enum NivelBeneficio {
    NO_APROBADO("No Aprobado", "danger"),
    APROBADO("Aprobado", "secondary"),
    BUENO("Bueno", "info"),
    SOBRESALIENTE("Sobresaliente", "warning"),
    EXCELENTE("Excelente", "success");
    
    private final String descripcion;
    private final String bootstrapClass;
    
    NivelBeneficio(String descripcion, String bootstrapClass) {
        this.descripcion = descripcion;
        this.bootstrapClass = bootstrapClass;
    }
    
    public String getDescripcion() { return descripcion; }
    public String getBootstrapClass() { return bootstrapClass; }
}
