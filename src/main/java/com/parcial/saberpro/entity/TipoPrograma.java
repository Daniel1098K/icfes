package com.parcial.saberpro.entity;

public enum TipoPrograma {
    TECNOLOGICO("Tecnológico", "T&T", 200),
    PROFESIONAL("Profesional", "PRO", 300);
    
    private final String nombre;
    private final String sigla;
    private final int puntajeMaximo;
    
    TipoPrograma(String nombre, String sigla, int puntajeMaximo) {
        this.nombre = nombre;
        this.sigla = sigla;
        this.puntajeMaximo = puntajeMaximo;
    }
    
    public String getNombre() { return nombre; }
    public String getSigla() { return sigla; }
    public int getPuntajeMaximo() { return puntajeMaximo; }
}
