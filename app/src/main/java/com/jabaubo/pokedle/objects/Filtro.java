package com.jabaubo.pokedle.objects;

public class Filtro {
    private Integer tipo1;
    private Integer tipo2;
    private Double altura;
    private Double peso;
    private Integer etapa;
    private Integer region;

    public Filtro() {
    }

    public Integer getTipo1() {
        return tipo1;
    }

    public void setTipo1(Integer tipo1) {
        this.tipo1 = tipo1;
    }

    public Integer getTipo2() {
        return tipo2;
    }

    public void setTipo2(Integer tipo2) {
        this.tipo2 = tipo2;
    }

    public Double getAltura() {
        return altura;
    }

    public void setAltura(Double altura) {
        this.altura = altura;
    }

    public Double getPeso() {
        return peso;
    }

    public void setPeso(Double peso) {
        this.peso = peso;
    }

    public Integer getEtapa() {
        return etapa;
    }

    public void setEtapa(Integer etapa) {
        this.etapa = etapa;
    }

    public Integer getRegion() {
        return region;
    }

    public void setRegion(Integer region) {
        this.region = region;
    }
}
