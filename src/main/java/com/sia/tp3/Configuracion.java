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
    private Long locus1;
    private Long locus2;
    private Long segmento;
    private Double probabilidadCruceUniforme;

    private String metodoCorte;
    private String metodoMutacion;
    private Double probabilidadDeMutacion;
    private Integer genAMutar;
    private String metodoSeleccion;
    private String metodoReemplazo;
    private Long cantidadDeReemplazo;
    private Long generaciones;

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
            this.locus1 = (Long) configuracion.get("locus1");
            this.locus2 = (Long) configuracion.get("locus2");
            this.segmento = (Long) configuracion.get("segmento");
            this.probabilidadCruceUniforme = (Double) configuracion.get("probabilidad_cruce_uniforme");

            this.metodoCorte = (String) configuracion.get("metodo_corte");

            this.metodoReemplazo = (String) configuracion.get("metodo_reemplazo");
            this.cantidadDeReemplazo = (Long) configuracion.get("cantidad_de_reemplazo");

            this.metodoMutacion = (String) configuracion.get("metodo_mutacion");
            this.probabilidadDeMutacion = (Double) configuracion.get("probabilidad_de_mutacion");

            String genAMutarString = String.valueOf(configuracion.get("gen_a_mutar"));
            if (genAMutarString == null) {
                throw new ConfiguracionIncorrectaExcepcion("Debe ingresar un gen a mutar valido [0, 5]");
            }
            this.genAMutar = Integer.parseInt(genAMutarString);

            this.metodoSeleccion = (String) configuracion.get("metodo_seleccion");

            this.generaciones = (Long) configuracion.get("generaciones");

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

    public String getMetodoReemplazo() {
        return metodoReemplazo;
    }

    public Long getGeneraciones() {
        return generaciones;
    }

    public Long getCantidadDeReemplazo() {
        return cantidadDeReemplazo;
    }

    public String getMetodoMutacion() {
        return metodoMutacion;
    }

    public Double getProbabilidadDeMutacion() {
        return probabilidadDeMutacion;
    }

    public Long getLocus1() {
        return locus1;
    }

    public Long getLocus2() {
        return locus2;
    }

    public Long getSegmento() {
        return segmento;
    }

    public String getMetodoSeleccion() {
        return metodoSeleccion;
    }

    public Integer getGenAMutar() {
        return genAMutar;
    }

    private class ConfiguracionIncorrectaExcepcion extends RuntimeException {
        public ConfiguracionIncorrectaExcepcion(String msg) {
            super(msg);
        }
    }

    private void validarConfig(){

        if (!metodoCruce.equals("un punto") && !metodoCruce.equals("dos puntos") && !metodoCruce.equals("uniforme"
        ) && !metodoCruce.equals("anular"))
            throw new ConfiguracionIncorrectaExcepcion("Debe ingresar un metodoCruce valido");

        if (locus1 < 0 || locus1 >= Genes.CANTIDAD_GENES)
            throw new ConfiguracionIncorrectaExcepcion("Debe ingresar un locus1 valido");

        if (locus2 < 0 || locus2 > locus1)
            throw new ConfiguracionIncorrectaExcepcion("Debe ingresar un locus2 valido");

        if(segmento < 1 || segmento > Genes.CANTIDAD_GENES/2)
            throw new ConfiguracionIncorrectaExcepcion("Debe ingresar un segmento valido");

        if (!metodoCorte.equals("maxima cantidad") && !metodoCorte.equals("estructura") && !metodoCorte.equals(
                "contenido") && !metodoCorte.equals("entorno a un optimo"))
            throw new ConfiguracionIncorrectaExcepcion("Debe ingresar un metodo de corte valido");

        if (!metodoReemplazo.equals("reemplazo 1") && !metodoReemplazo.equals("reemplazo 2") && !metodoReemplazo.equals(
                "reemplazo 3"))
            throw new ConfiguracionIncorrectaExcepcion("Debe ingresar un metodo de reemplazo valido");

        if (!metodoMutacion.equals("gen") && !metodoMutacion.equals("multigen"))
            throw new ConfiguracionIncorrectaExcepcion("Debe ingresar un metodo de mutacion valido");

        if (this.genAMutar < 0 || this.genAMutar >= Genes.CANTIDAD_GENES)
            throw new ConfiguracionIncorrectaExcepcion("Debe ingresar un gen a mutar valido [0, 5]");

        if (!metodoSeleccion.equals("elite") && !metodoSeleccion.equals("ruleta")
                && !metodoSeleccion.equals("universal") && !metodoSeleccion.equals("boltzmann")
                && !metodoSeleccion.equals("torneos") && !metodoSeleccion.equals("ranking"))
            throw new ConfiguracionIncorrectaExcepcion("Debe ingresar un metodo de seleccion valido");
    }
}

