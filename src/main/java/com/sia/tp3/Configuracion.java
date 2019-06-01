package com.sia.tp3;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class Configuracion {

    private String personaje;
    private Double fuerza;
    private Double agilidad;
    private Double pericia;
    private Double resistencia;
    private Double vida;

    private String metodoCruce;
    private Integer locus1;
    private Integer locus2;
    private Integer segmento;
    private Double probabilidadCruceUniforme;

    private String metodoCorte;
    private String metodoMutacion;
    private Double probabilidadDeMutacion;
    private Integer genAMutar;
    private String metodoSeleccion1;
    private String metodoSeleccion2;
    private String metodoReemplazo1;
    private String metodoReemplazo2;
    private Integer cantidadDeReemplazo1;
    private Integer cantidadDeReemplazo2;
    private Double A;
    private Double B;

    private Integer generaciones;


    public Configuracion() {

        JSONParser parser = new JSONParser();
        JSONArray jsonArray = null;

        try {
            jsonArray = (JSONArray) parser.parse(new FileReader("src/main/java/com/sia/tp3/config.json"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }

        for (Object o : jsonArray) {
            JSONObject configuracion = (JSONObject) o;

            this.personaje = (String) configuracion.get("personaje");

            if (!personaje.equals("guerrero") && !personaje.equals("arquero") && !personaje.equals("defensor") && !personaje.equals("asesino"))
                throw new ConfiguracionIncorrectaExcepcion("Debe ingresar un personaje valido");

            this.fuerza = (Double) configuracion.get("fuerza");
            this.agilidad = (Double) configuracion.get("agilidad");
            this.pericia = (Double) configuracion.get("pericia");
            this.resistencia = (Double) configuracion.get("resistencia");
            this.vida = (Double) configuracion.get("vida");

            this.metodoCruce = (String) configuracion.get("metodo_cruce");
            this.locus1 = ((Long) configuracion.get("locus1")).intValue();
            this.locus2 = ((Long) configuracion.get("locus2")).intValue();
            this.segmento = ((Long) configuracion.get("segmento")).intValue();
            this.probabilidadCruceUniforme = (Double) configuracion.get("probabilidad_cruce_uniforme");

            this.metodoCorte = (String) configuracion.get("metodo_corte");

            this.metodoReemplazo1 = (String) configuracion.get("metodo_reemplazo_1");
            this.metodoReemplazo2 = (String) configuracion.get("metodo_reemplazo_2");

            this.cantidadDeReemplazo1 = ((Long) configuracion.get("cantidad_de_reemplazo_1")).intValue();
            this.cantidadDeReemplazo2 = ((Long) configuracion.get("cantidad_de_reemplazo_2")).intValue();

            this.metodoMutacion = (String) configuracion.get("metodo_mutacion");
            this.genAMutar = ((Long) configuracion.get("gen_a_mutar")).intValue();
            this.probabilidadDeMutacion = (Double) configuracion.get("probabilidad_de_mutacion");

            this.metodoSeleccion1 = (String) configuracion.get("metodo_seleccion_1");
            this.metodoSeleccion2 = (String) configuracion.get("metodo_seleccion_2");

            this.generaciones = ((Long) configuracion.get("generaciones")).intValue();

            this.A = (Double) configuracion.get("A");
            this.B = (Double) configuracion.get("B");

            validarConfig();
        }
    }

    public String getPersonaje() {
        return personaje;
    }

    public void setPersonaje(String personaje) {
        this.personaje = personaje;
    }

    public Double getFuerza() {
        return fuerza;
    }

    public void setFuerza(Double fuerza) {
        this.fuerza = fuerza;
    }

    public Double getAgilidad() {
        return agilidad;
    }

    public void setAgilidad(Double agilidad) {
        this.agilidad = agilidad;
    }

    public Double getPericia() {
        return pericia;
    }

    public void setPericia(Double pericia) {
        this.pericia = pericia;
    }

    public Double getResistencia() {
        return resistencia;
    }

    public void setResistencia(Double resistencia) {
        this.resistencia = resistencia;
    }

    public Double getVida() {
        return vida;
    }

    public void setVida(Double vida) {
        this.vida = vida;
    }

    public String getMetodoCruce() {
        return metodoCruce;
    }

    public void setMetodoCruce(final String metodoCruce) {
        this.metodoCruce = metodoCruce;
    }

    public Double getProbabilidadCruceUniforme() {
        return probabilidadCruceUniforme;
    }

    public void setProbabilidadCruceUniforme(final Double probabilidadCruceUniforme) {
        this.probabilidadCruceUniforme = probabilidadCruceUniforme;
    }

    public String getMetodoCorte() {
        return metodoCorte;
    }

    public String getMetodoReemplazo1() {
        return metodoReemplazo1;
    }

    public Integer getGeneraciones() {
        return generaciones;
    }

    public Integer getCantidadDeReemplazo1() {
        return cantidadDeReemplazo1;
    }

    public String getMetodoMutacion() {
        return metodoMutacion;
    }

    public Double getProbabilidadDeMutacion() {
        return probabilidadDeMutacion;
    }

    public Integer getLocus1() {
        return locus1;
    }

    public Integer getLocus2() {
        return locus2;
    }

    public Integer getSegmento() {
        return segmento;
    }

    public String getMetodoSeleccion1() {
        return metodoSeleccion1;
    }

    public Integer getGenAMutar() {
        return genAMutar;
    }

    public Double getA() {
        return A;
    }

    public Double getB() {
        return B;
    }

    public String getMetodoSeleccion2() {
        return metodoSeleccion2;
    }

    public String getMetodoReemplazo2() {
        return metodoReemplazo2;
    }

    public Integer getCantidadDeReemplazo2() {
        return cantidadDeReemplazo2;
    }

    private class ConfiguracionIncorrectaExcepcion extends RuntimeException {
        public ConfiguracionIncorrectaExcepcion(String msg) {
            super(msg);
        }
    }

    private void validarConfig() {

        if (!metodoCruce.equals("un punto") && !metodoCruce.equals("dos puntos") && !metodoCruce.equals("uniforme"
        ) && !metodoCruce.equals("anular"))
            throw new ConfiguracionIncorrectaExcepcion("Debe ingresar un metodoCruce valido");

        if (locus1 < 0 || locus1 >= Genes.CANTIDAD_GENES)
            throw new ConfiguracionIncorrectaExcepcion("Debe ingresar un locus1 valido");

        if (locus2 < 0 || locus2 > locus1)
            throw new ConfiguracionIncorrectaExcepcion("Debe ingresar un locus2 valido");

        if (segmento < 1 || segmento > Genes.CANTIDAD_GENES / 2)
            throw new ConfiguracionIncorrectaExcepcion("Debe ingresar un segmento valido");

        if (!metodoCorte.equals("maxima cantidad") && !metodoCorte.equals("estructura") && !metodoCorte.equals(
                "contenido") && !metodoCorte.equals("entorno a un optimo"))
            throw new ConfiguracionIncorrectaExcepcion("Debe ingresar un metodo de corte valido");

        if (!metodoReemplazo1.equals("reemplazo 1") && !metodoReemplazo1.equals("reemplazo 2") && !metodoReemplazo1.equals(
                "reemplazo 3"))
            throw new ConfiguracionIncorrectaExcepcion("Debe ingresar un metodo de reemplazo valido");

        if (!metodoMutacion.equals("gen") && !metodoMutacion.equals("multigen"))
            throw new ConfiguracionIncorrectaExcepcion("Debe ingresar un metodo de mutacion valido");

        if (this.genAMutar < 0 || this.genAMutar >= Genes.CANTIDAD_GENES)
            throw new ConfiguracionIncorrectaExcepcion("Debe ingresar un gen a mutar valido [0, 5]");

        if (!metodoSeleccion1.equals("elite") && !metodoSeleccion1.equals("ruleta")
                && !metodoSeleccion1.equals("universal") && !metodoSeleccion1.equals("boltzmann")
                && !metodoSeleccion1.equals("torneos") && !metodoSeleccion1.equals("ranking"))
            throw new ConfiguracionIncorrectaExcepcion("Debe ingresar un metodo de seleccion valido");

        if (metodoReemplazo1.equals("reemplazo 2") || metodoReemplazo1.equals("reemplazo 3")) {
            if (cantidadDeReemplazo1 <= 1)
                throw new ConfiguracionIncorrectaExcepcion("La cantidad de reemplazo1 no puede ser menor o igual a 1 " +
                        "si se utilizan los métodos de reemplazo2 o reemplazo3");
        }
        if (metodoReemplazo2.equals("reemplazo 2") || metodoReemplazo2.equals("reemplazo 3")) {
            if (cantidadDeReemplazo2 <= 1)
                throw new ConfiguracionIncorrectaExcepcion("La cantidad de reemplazo2 no puede ser menor o igual a 1 " +
                        "si se utilizan los métodos de reemplazo2 o reemplazo3");
        }
    }
}

