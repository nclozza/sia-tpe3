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

    private String cruce;
    private Double probabilidadCruceUniforme;

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

            this.cruce = (String) configuracion.get("cruce");

            if (!cruce.equals("un punto") && !personaje.equals("dos puntos") && !personaje.equals("uniforme") && !personaje.equals("anular"))
                throw new ConfiguracionIncorrectaExcepcion("Debe ingresar un cruce valido");

            this.probabilidadCruceUniforme = (Double) configuracion.get("probabilidad_cruce_uniforme");
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

    public String getCruce() {
        return cruce;
    }

    public void setCruce(final String cruce) {
        this.cruce = cruce;
    }

    public Double getProbabilidadCruceUniforme() {
        return probabilidadCruceUniforme;
    }

    public void setProbabilidadCruceUniforme(final Double probabilidadCruceUniforme) {
        this.probabilidadCruceUniforme = probabilidadCruceUniforme;
    }

    private class ConfiguracionIncorrectaExcepcion extends RuntimeException {
        public ConfiguracionIncorrectaExcepcion(String msg) {
            super(msg);
        }
    }
}

