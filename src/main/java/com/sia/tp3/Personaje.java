package com.sia.tp3;import com.sia.tp3.items.*;import java.util.Random;public class Personaje {    private String personaje;    private Multiplicador multiplicador;    private double[][] genes;    private Double modificadorAtaque;    private Double modificadorDefensa;    private Double fuerza;    private Double agilidad;    private Double pericia;    private Double resistencia;    private Double vida;    private Double ataque;    private Double defensa;    private Double desempenio;    public Personaje(String personaje, Multiplicador multiplicador, Arma arma, Bota bota, Casco casco, Guante guante,                     Pechera pechera) {        this.personaje = personaje;        this.multiplicador = multiplicador;        genes = new double[Genes.CANTIDAD_GENES][Genes.CANTIDAD_CARACTERISTICAS];        genes[Genes.ARMA][Genes.FUERZA] = arma.getFuerza();        genes[Genes.ARMA][Genes.AGILIDAD] = arma.getAgilidad();        genes[Genes.ARMA][Genes.PERICIA] = arma.getPericia();        genes[Genes.ARMA][Genes.RESISTENCIA] = arma.getResistencia();        genes[Genes.ARMA][Genes.VIDA] = arma.getVida();        genes[Genes.BOTA][Genes.FUERZA] = bota.getFuerza();        genes[Genes.BOTA][Genes.AGILIDAD] = bota.getAgilidad();        genes[Genes.BOTA][Genes.PERICIA] = bota.getPericia();        genes[Genes.BOTA][Genes.RESISTENCIA] = bota.getResistencia();        genes[Genes.BOTA][Genes.VIDA] = bota.getVida();        genes[Genes.CASCO][Genes.FUERZA] = casco.getFuerza();        genes[Genes.CASCO][Genes.AGILIDAD] = casco.getAgilidad();        genes[Genes.CASCO][Genes.PERICIA] = casco.getPericia();        genes[Genes.CASCO][Genes.RESISTENCIA] = casco.getResistencia();        genes[Genes.CASCO][Genes.VIDA] = casco.getVida();        genes[Genes.GUANTE][Genes.FUERZA] = guante.getFuerza();        genes[Genes.GUANTE][Genes.AGILIDAD] = guante.getAgilidad();        genes[Genes.GUANTE][Genes.PERICIA] = guante.getPericia();        genes[Genes.GUANTE][Genes.RESISTENCIA] = guante.getResistencia();        genes[Genes.GUANTE][Genes.VIDA] = guante.getVida();        genes[Genes.PECHERA][Genes.FUERZA] = pechera.getFuerza();        genes[Genes.PECHERA][Genes.AGILIDAD] = pechera.getAgilidad();        genes[Genes.PECHERA][Genes.PERICIA] = pechera.getPericia();        genes[Genes.PECHERA][Genes.RESISTENCIA] = pechera.getResistencia();        genes[Genes.PECHERA][Genes.VIDA] = pechera.getVida();        Random r = new Random();        genes[Genes.ALTURA][0] = 1.3 + (2.0 - 1.3) * r.nextDouble();        this.modificadorAtaque =                0.5 - Math.pow(3 * genes[Genes.ALTURA][0] - 5, 4) + Math.pow(3 * genes[Genes.ALTURA][0] - 5, 2) + (genes[Genes.ALTURA][0] / 2);        this.modificadorDefensa =                2 + Math.pow(3 * genes[Genes.ALTURA][0] - 5, 4) - Math.pow(3 * genes[Genes.ALTURA][0] - 5, 2) - (genes[Genes.ALTURA][0] / 2);        this.desempenio = calcularDesempenio();    }    public Personaje(final String personaje, final Multiplicador multiplicador, final double[][] genes,                     final Double modificadorAtaque, final Double modificadorDefensa, final Double fuerza,                     final Double agilidad, final Double pericia, final Double resistencia, final Double vida,                     final Double ataque, final Double defensa, final Double desempenio) {        this.personaje = personaje;        this.multiplicador = multiplicador;        this.genes = genes;        this.modificadorAtaque = modificadorAtaque;        this.modificadorDefensa = modificadorDefensa;        this.fuerza = fuerza;        this.agilidad = agilidad;        this.pericia = pericia;        this.resistencia = resistencia;        this.vida = vida;        this.ataque = ataque;        this.defensa = defensa;        this.desempenio = desempenio;    }    public double[][] getGenes() {        return genes;    }    public Double calcularDesempenio() {        fuerza = calcularFuerza();        agilidad = calcularAgilidad();        pericia = calcularPericia();        resistencia = calcularResistencia();        vida = calcularVida();        ataque = (agilidad + pericia) * fuerza * modificadorAtaque;        defensa = (resistencia + pericia) * vida * modificadorDefensa;        switch (this.personaje) {            case "guerrero":                return 0.6 * ataque + 0.4 * defensa;            case "arquero":                return 0.9 * ataque + 0.1 * defensa;            case "defensor":                return 0.1 * ataque + 0.9 * defensa;            case "asesino":                return 0.7 * ataque + 0.3 * defensa;            default:                return (double) 0;        }    }    public Double getDesempenio() {        return desempenio;    }    public void setDesempenio(final Double desempenio) {        this.desempenio = desempenio;    }    public Double calcularFuerza() {        Double sumatoriaItemFuerza =                genes[Genes.ARMA][Genes.FUERZA] + genes[Genes.BOTA][Genes.FUERZA] + genes[Genes.CASCO][Genes.FUERZA] + genes[Genes.GUANTE][Genes.FUERZA] + genes[Genes.PECHERA][Genes.FUERZA];        sumatoriaItemFuerza = sumatoriaItemFuerza * this.multiplicador.getFuerza();        return 100 * Math.tanh(0.01 * sumatoriaItemFuerza);    }    public Double calcularAgilidad() {        Double sumatoriaItemAgilidad =                genes[Genes.ARMA][Genes.AGILIDAD] + genes[Genes.BOTA][Genes.AGILIDAD] + genes[Genes.CASCO][Genes.AGILIDAD] + genes[Genes.GUANTE][Genes.AGILIDAD] + genes[Genes.PECHERA][Genes.AGILIDAD];        sumatoriaItemAgilidad = sumatoriaItemAgilidad * this.multiplicador.getAgilidad();        return Math.tanh(0.01 * sumatoriaItemAgilidad);    }    public Double calcularPericia() {        Double sumatoriaItemPericia =                genes[Genes.ARMA][Genes.PERICIA] + genes[Genes.BOTA][Genes.PERICIA] + genes[Genes.CASCO][Genes.PERICIA] + genes[Genes.GUANTE][Genes.PERICIA] + genes[Genes.PECHERA][Genes.PERICIA];        sumatoriaItemPericia = sumatoriaItemPericia * this.multiplicador.getPericia();        return 0.6 * Math.tanh(0.01 * sumatoriaItemPericia);    }    public Double calcularResistencia() {        Double sumatoriaItemResistencia =                genes[Genes.ARMA][Genes.RESISTENCIA] + genes[Genes.BOTA][Genes.RESISTENCIA] + genes[Genes.CASCO][Genes.RESISTENCIA] + genes[Genes.GUANTE][Genes.RESISTENCIA] + genes[Genes.PECHERA][Genes.RESISTENCIA];        sumatoriaItemResistencia = sumatoriaItemResistencia * this.multiplicador.getResistencia();        return Math.tanh(0.01 * sumatoriaItemResistencia);    }    public Double calcularVida() {        Double sumatoriaItemVida =                genes[Genes.ARMA][Genes.VIDA] + genes[Genes.BOTA][Genes.VIDA] + genes[Genes.CASCO][Genes.VIDA] + genes[Genes.GUANTE][Genes.VIDA] + genes[Genes.PECHERA][Genes.VIDA];        sumatoriaItemVida = sumatoriaItemVida * this.multiplicador.getVida();        return 100 * Math.tanh(0.01 * sumatoriaItemVida);    }    public Double calcularAtaque() {        return (calcularAgilidad() + calcularPericia()) * calcularFuerza() * this.modificadorAtaque;    }    public Double calcularDefensa() {        return (calcularResistencia() + calcularPericia()) * calcularVida() * this.modificadorDefensa;    }    public void imprimirGenes() {        System.out.println("ARMA:");        imprimirCaracteristicas(genes[Genes.ARMA]);        System.out.println("BOTA:");        imprimirCaracteristicas(genes[Genes.BOTA]);        System.out.println("CASCO:");        imprimirCaracteristicas(genes[Genes.CASCO]);        System.out.println("GUANTE:");        imprimirCaracteristicas(genes[Genes.GUANTE]);        System.out.println("PECHERA:");        imprimirCaracteristicas(genes[Genes.PECHERA]);        System.out.println("ALTURA:");        System.out.println(genes[Genes.ALTURA][0] + "\n\n");    }    public Personaje copy() {        return new Personaje(personaje, multiplicador, genes, modificadorAtaque, modificadorDefensa, fuerza, agilidad                , pericia, resistencia, vida, ataque, defensa, desempenio);    }    private void imprimirCaracteristicas(final double[] gen) {        for (int i = 0; i < Genes.CANTIDAD_CARACTERISTICAS; i++) {            System.out.print(gen[i] + " ");        }        System.out.println();    }}