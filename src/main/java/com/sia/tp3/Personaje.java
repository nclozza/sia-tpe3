package com.sia.tp3;

import com.sia.tp3.items.*;

import java.util.Random;

public class Personaje {

    private String personaje;
    private Multiplicador multiplicador;
    private Arma arma;
    private Bota bota;
    private Casco casco;
    private Guante guante;
    private Pechera pechera;
    private Double altura;
    private Double modificadorAtaque;
    private Double modificadorDefensa;
    private Double fuerza;
    private Double agilidad;
    private Double pericia;
    private Double resistencia;
    private  Double vida;
    private Double ataque;
    private  Double defensa;
    private Double desempeño;

    public Personaje(String personaje, Multiplicador multiplicador,Arma arma, Bota bota, Casco casco, Guante guante, Pechera pechera) {
        this.personaje = personaje;
        this.multiplicador = multiplicador;
        this.arma = arma;
        this.bota = bota;
        this.casco = casco;
        this.guante = guante;
        this.pechera = pechera;
        Random r = new Random();
        this.altura = 1.3 + (2.0 - 1.3) * r.nextDouble();
        this.modificadorAtaque = 0.5 - Math.pow(3*this.altura - 5, 4) + Math.pow(3*this.altura-5, 2)  + (this.altura/2);
        this.modificadorDefensa = 2 + Math.pow(3*this.altura - 5, 4) - Math.pow(3*this.altura-5, 2)  - (this.altura/2);
        this.fuerza = calcularFuerza();
        this.agilidad = calcularAgilidad();
        this.pericia = calcularPericia();
        this.resistencia = calcularResistencia();
        this.vida = calcularVida();
        this.ataque = (this.agilidad + this.pericia) * this.fuerza * this.modificadorAtaque;
        this.defensa = (this.resistencia + this.pericia) * this.vida * this.modificadorDefensa;
        this. desempeño = calcularDesempeño(this.ataque, this.defensa);
    }


    public Double calcularDesempeño (Double ataque, Double defensa){

        switch (this.personaje){
            case "guerrero":
                return 0.6 * ataque + 0.4 * defensa;
            case "arquero":
                return 0.9 * ataque + 0.1 * defensa;
            case "defensor":
                return 0.1 * ataque + 0.9 * defensa;
            case "asesino":
                return 0.7 * ataque + 0.3 * defensa;
            default:
                return Double.valueOf(0);
        }
    }

    public Double calcularFuerza(){

        Double sumatoriaItemFuerza = this.arma.getFuerza() + this.bota.getFuerza() + this.casco.getFuerza() + this.guante.getFuerza() + this.pechera.getFuerza();
        sumatoriaItemFuerza = sumatoriaItemFuerza * this.multiplicador.getFuerza();

        return 100 * Math.tanh(0.01 * sumatoriaItemFuerza);
    }

    public Double calcularAgilidad(){

        Double sumatoriaItemAgilidad = this.arma.getAgilidad() + this.bota.getAgilidad() + this.casco.getAgilidad() + this.guante.getAgilidad() + this.pechera.getAgilidad();
        sumatoriaItemAgilidad = sumatoriaItemAgilidad * this.multiplicador.getAgilidad();

        return  Math.tanh(0.01 * sumatoriaItemAgilidad);
    }

    public Double calcularPericia(){

        Double sumatoriaItemPericia = this.arma.getPericia() + this.bota.getPericia() + this.casco.getPericia() + this.guante.getPericia() + this.pechera.getPericia();
        sumatoriaItemPericia = sumatoriaItemPericia * this.multiplicador.getPericia();

        return 0.6 * Math.tanh(0.01 * sumatoriaItemPericia);
    }

    public Double calcularResistencia(){

        Double sumatoriaItemResistencia = this.arma.getResistencia() + this.bota.getResistencia() + this.casco.getResistencia() + this.guante.getResistencia() + this.pechera.getResistencia();
        sumatoriaItemResistencia = sumatoriaItemResistencia * this.multiplicador.getResistencia();

        return  Math.tanh(0.01 * sumatoriaItemResistencia);
    }

    public Double calcularVida(){

        Double sumatoriaItemVida = this.arma.getVida() + this.bota.getVida() + this.casco.getVida() + this.guante.getVida() + this.pechera.getVida();
        sumatoriaItemVida = sumatoriaItemVida * this.multiplicador.getVida();

        return  100 * Math.tanh(0.01 * sumatoriaItemVida);
    }

    public Double calcularAtaque(){
        return (calcularAgilidad() + calcularPericia()) * calcularFuerza() * this.modificadorAtaque;
    }

    public Double calcularDefensa(){
        return (calcularResistencia() + calcularPericia()) * calcularVida() * this.modificadorDefensa;
    }
}
