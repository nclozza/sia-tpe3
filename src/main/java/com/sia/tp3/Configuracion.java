package com.sia.tp3;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class Configuracion {

    private String path;
    private Boolean generarGraficos;
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
    private Double optimo;
    private Integer generacionesAVerificar;
    private Double porcentaje;
    private String metodoMutacion;
    private Double probabilidadDeMutacion;
    private Integer genAMutar;
    private Boolean mutacionUniforme;
    private Double baseExponencialMutacionNoUniforme;
    private String metodoSeleccion1;
    private Boolean seleccion1UsaBoltzmann;
    private String metodoSeleccion2;
    private Boolean seleccion2UsaBoltzmann;
    private String metodoSeleccion3;
    private Boolean seleccion3UsaBoltzmann;
    private String metodoSeleccion4;
    private Boolean seleccion4UsaBoltzmann;
    private String metodoReemplazo;
    private Integer cantidadDeReemplazo;
    private Integer cantidadDePersonajesTorneos;
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

            this.generarGraficos = (Boolean) configuracion.get("generar_graficos");
            this.path = (String) configuracion.get("datos");
            this.personaje = (String) configuracion.get("personaje");

            if (!personaje.equals("guerrero") && !personaje.equals("arquero") && !personaje.equals("defensor") && !personaje.equals("asesino")) {
                throw new ConfiguracionIncorrectaExcepcion("Debe ingresar un personaje valido");
            }


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
            this.optimo = (Double) configuracion.get("optimo");
            this.porcentaje = (Double) configuracion.get("porcentaje");
            this.generacionesAVerificar = ((Long) configuracion.get("generaciones_a_verificar")).intValue();

            this.metodoSeleccion1 = (String) configuracion.get("metodo_seleccion_1");
            this.seleccion1UsaBoltzmann = (Boolean) configuracion.get("seleccion1_usa_boltzman");
            this.metodoSeleccion2 = (String) configuracion.get("metodo_seleccion_2");
            this.seleccion2UsaBoltzmann = (Boolean) configuracion.get("seleccion2_usa_boltzman");
            this.metodoSeleccion3 = (String) configuracion.get("metodo_seleccion_3");
            this.seleccion3UsaBoltzmann = (Boolean) configuracion.get("seleccion3_usa_boltzman");
            this.metodoSeleccion4 = (String) configuracion.get("metodo_seleccion_4");
            this.seleccion4UsaBoltzmann = (Boolean) configuracion.get("seleccion4_usa_boltzman");

            this.metodoReemplazo = (String) configuracion.get("metodo_reemplazo");
            this.cantidadDeReemplazo = ((Long) configuracion.get("cantidad_de_reemplazo")).intValue();
            this.cantidadDePersonajesTorneos = ((Long) configuracion.get("cantidad_de_personajes_torneos")).intValue();

            this.metodoMutacion = (String) configuracion.get("metodo_mutacion");
            this.genAMutar = ((Long) configuracion.get("gen_a_mutar")).intValue();
            this.probabilidadDeMutacion = (Double) configuracion.get("probabilidad_de_mutacion");
            this.mutacionUniforme = (Boolean) configuracion.get("mutacion_uniforme");
            this.baseExponencialMutacionNoUniforme = (Double) configuracion.get(
                    "base_exponencial_mutacion_no_uniforme");

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

    public String getMetodoReemplazo() {
        return metodoReemplazo;
    }

    public Integer getGeneraciones() {
        return generaciones;
    }

    public Integer getCantidadDeReemplazo() {
        return cantidadDeReemplazo;
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

    public String getMetodoSeleccion2() {
        return metodoSeleccion2;
    }

    public String getMetodoSeleccion3() {
        return metodoSeleccion3;
    }

    public String getMetodoSeleccion4() {
        return metodoSeleccion4;
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

    public Integer getCantidadDePersonajesTorneos() {
        return cantidadDePersonajesTorneos;
    }

    public Boolean getSeleccion1UsaBoltzmann() {
        return seleccion1UsaBoltzmann;
    }

    public Boolean getSeleccion2UsaBoltzmann() {
        return seleccion2UsaBoltzmann;
    }

    public Boolean getSeleccion3UsaBoltzmann() {
        return seleccion3UsaBoltzmann;
    }

    public Boolean getSeleccion4UsaBoltzmann() {
        return seleccion4UsaBoltzmann;
    }

    public Double getOptimo() {
        return optimo;
    }

    public Integer getGeneracionesAVerificar() {
        return generacionesAVerificar;
    }

    public Double getPorcentaje() {
        return porcentaje;
    }

    public Boolean getMutacionUniforme() {
        return mutacionUniforme;
    }

    public Double getBaseExponencialMutacionNoUniforme() {
        return baseExponencialMutacionNoUniforme;
    }

    public String getPath() {
        return path;
    }

    public Boolean getGenerarGraficos() {
        return generarGraficos;
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

        if (locus2 < 0 || locus2 < locus1)
            throw new ConfiguracionIncorrectaExcepcion("Debe ingresar un locus2 valido");

        if (segmento < 1 || segmento > Genes.CANTIDAD_GENES / 2)
            throw new ConfiguracionIncorrectaExcepcion("Debe ingresar un segmento valido");

        if (!metodoCorte.equals("maxima cantidad") && !metodoCorte.equals("estructura") && !metodoCorte.equals(
                "contenido") && !metodoCorte.equals("optimo"))
            throw new ConfiguracionIncorrectaExcepcion("Debe ingresar un metodo de corte valido");

        if (!metodoReemplazo.equals("reemplazo 1") && !metodoReemplazo.equals("reemplazo 2") && !metodoReemplazo.equals(
                "reemplazo 3"))
            throw new ConfiguracionIncorrectaExcepcion("Debe ingresar un metodo de reemplazo valido");

        if (!metodoMutacion.equals("gen") && !metodoMutacion.equals("multigen"))
            throw new ConfiguracionIncorrectaExcepcion("Debe ingresar un metodo de mutacion valido");

        if (this.genAMutar < 0 || this.genAMutar >= Genes.CANTIDAD_GENES)
            throw new ConfiguracionIncorrectaExcepcion("Debe ingresar un gen a mutar valido [0, 5]");

        if (!metodoSeleccion1.equals("elite") && !metodoSeleccion1.equals("ruleta")
                && !metodoSeleccion1.equals("universal")
                && !metodoSeleccion1.equals("torneos deterministica") && !metodoSeleccion1.equals("torneos " +
                "probabilistica")
                && !metodoSeleccion1.equals("ranking"))
            throw new ConfiguracionIncorrectaExcepcion("Debe ingresar un metodo de seleccion 1 valido");

        if (!metodoSeleccion2.equals("elite") && !metodoSeleccion2.equals("ruleta")
                && !metodoSeleccion2.equals("universal")
                && !metodoSeleccion2.equals("torneos deterministica") && !metodoSeleccion2.equals("torneos " +
                "probabilistica")
                && !metodoSeleccion2.equals("ranking"))
            throw new ConfiguracionIncorrectaExcepcion("Debe ingresar un metodo de seleccion 2 valido");

        if (!metodoSeleccion3.equals("elite") && !metodoSeleccion3.equals("ruleta")
                && !metodoSeleccion3.equals("universal")
                && !metodoSeleccion3.equals("torneos deterministica") && !metodoSeleccion3.equals("torneos " +
                "probabilistica")
                && !metodoSeleccion3.equals("ranking"))
            throw new ConfiguracionIncorrectaExcepcion("Debe ingresar un metodo de seleccion 3 valido");

        if (!metodoSeleccion4.equals("elite") && !metodoSeleccion4.equals("ruleta")
                && !metodoSeleccion4.equals("universal")
                && !metodoSeleccion4.equals("torneos deterministica") && !metodoSeleccion4.equals("torneos " +
                "probabilistica")
                && !metodoSeleccion4.equals("ranking"))
            throw new ConfiguracionIncorrectaExcepcion("Debe ingresar un metodo de seleccion 4 valido");

        if (metodoReemplazo.equals("reemplazo 2") || metodoReemplazo.equals("reemplazo 3")) {
            if (cantidadDeReemplazo <= 1)
                throw new ConfiguracionIncorrectaExcepcion("La cantidad de reemplazo1 no puede ser menor o igual a 1 " +
                        "si se utilizan los métodos de reemplazo2 o reemplazo3");
        }

        if (!getMutacionUniforme()) {
            if (baseExponencialMutacionNoUniforme < 0 || baseExponencialMutacionNoUniforme > 1)
                throw new ConfiguracionIncorrectaExcepcion("Debe ingresar una base válida, comprendida entre [0, 1]");
        }
    }
}

