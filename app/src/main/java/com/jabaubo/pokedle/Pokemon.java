/*
* Autor : Javier Bautista Bonilla
*
* Clase : Pokemon
*
* Funcionalidad : Modelo del objeto Pokemon */

package com.jabaubo.pokedle;

import java.util.Arrays;

public class Pokemon {
    private int numero;
    private String nombre;
    private int tipo1;
    private int tipo2;
    private double altura;
    private double peso;
    private int etapaEvolutiva;
    private int region;
    private int[] comparacion;

    //Constantes de tipos
    public static final int NINGUNO=0;
    public static final int BICHO = 1;
    public static final int SINIESTRO = 2;
    public static final int DRAGON = 3;
    public static final int ELECTRICO = 4;
    public static final int HADA = 5;
    public static final int LUCHA = 6;
    public static final int FUEGO = 7;
    public static final int VOLADOR = 8;
    public static final int FANTASMA = 9;
    public static final int PLANTA = 10;
    public static final int TIERRA = 11;
    public static final int HIELO = 12;
    public static final int NORMAL = 13;
    public static final int VENENO = 14;
    public static final int PSIQUICO = 15;
    public static final int ROCA = 16;
    public static final int ACERO = 17;
    public static final int AGUA = 18;

    //Constantes de regiones
    public static final int KANTO = 1;
    public static final int JOHTO = 2;
    public static final int HOENN = 3;
    public static final int SINNOH = 4;
    public static final int TESELIA = 5;
    public static final int KALOS = 6;
    public static final int ALOLA = 7;
    public static final int GALAR = 8;
    public static final int PALDEA = 9;

    public Pokemon(int numero, String nombre, int tipo1, int tipo2, double altura, double peso, int etapaEvolutiva, int region) {
        this.numero = numero;
        this.nombre = nombre;
        this.tipo1 = tipo1;
        this.tipo2 = tipo2;
        this.altura = altura;
        this.peso = peso;
        this.etapaEvolutiva = etapaEvolutiva;
        this.region = region;
        this.comparacion = new int[5];

    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getTipo1() {
        return tipo1;
    }

    public void setTipo1(int tipo1) {
        this.tipo1 = tipo1;
    }

    public int getTipo2() {
        return tipo2;
    }

    public void setTipo2(int tipo2) {
        this.tipo2 = tipo2;
    }

    public double getAltura() {
        return altura;
    }

    public void setAltura(double altura) {
        this.altura = altura;
    }

    public double getPeso() {
        return peso;
    }

    public void setPeso(double peso) {
        this.peso = peso;
    }

    public int getEtapaEvolutiva() {
        return etapaEvolutiva;
    }

    public void setEtapaEvolutiva(int etapaEvolutiva) {
        this.etapaEvolutiva = etapaEvolutiva;
    }

    public int getRegion() {
        return region;
    }

    public void setRegion(int region) {
        this.region = region;
    }

    @Override
    public String toString() {
        return "Pokemon{" +
                "numero=" + numero +
                ", nombre='" + nombre + '\'' +
                ", tipo1=" + tipo1 +
                ", tipo2=" + tipo2 +
                ", altura=" + altura +
                ", peso=" + peso +
                ", etapaEvolutiva=" + etapaEvolutiva +
                ", region=" + region +
                ", comparacion=" + Arrays.toString(comparacion) +
                '}';
    }

    public String toStringComparacion() {
        return "Pokemon{" +
                "comparacion=" + Arrays.toString(comparacion) +
                ", nombre='" + nombre + '\'' +
                '}';
    }

    public int[] getComparacion() {
        return comparacion;
    }

    public void setComparacion(int[] comparacion) {
        this.comparacion = comparacion;
    }

    public static String getNombreTipo(int tipo) {
        switch (tipo) {
            case NINGUNO: return "Ninguno";
            case BICHO: return "Bicho";
            case SINIESTRO: return "Siniestro";
            case DRAGON: return "Dragón";
            case ELECTRICO: return "Eléctrico";
            case HADA: return "Hada";
            case LUCHA: return "Lucha";
            case FUEGO: return "Fuego";
            case VOLADOR: return "Volador";
            case FANTASMA: return "Fantasma";
            case PLANTA: return "Planta";
            case TIERRA: return "Tierra";
            case HIELO: return "Hielo";
            case NORMAL: return "Normal";
            case VENENO: return "Veneno";
            case PSIQUICO: return "Psíquico";
            case ROCA: return "Roca";
            case ACERO: return "Acero";
            case AGUA: return "Agua";
            default: return "Desconocido";
        }
    }

    public static String getNombreRegion (int region) {
        switch (region) {
            case KANTO: return "Kanto";
            case JOHTO: return "Johto";
            case HOENN: return "Hoenn";
            case SINNOH: return "Sinnoh";
            case TESELIA: return "Teselia";
            case KALOS: return "Kalos";
            case ALOLA: return "Alola";
            case GALAR: return "Galar";
            case PALDEA: return "Paldea";
            default: return "Desconocida";
        }
    }


}
