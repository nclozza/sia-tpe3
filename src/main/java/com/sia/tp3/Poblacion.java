package com.sia.tp3;

import com.sia.tp3.items.*;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class Poblacion {

    private ArrayList<Personaje> personajes;
    private Integer cantidadPersonajes;
    private int numeroDeGeneracion;
    private double mejorDesempenio;
    private ArrayList<Double> mejoresDesempenios;

    private static final String path = "src/main/java/com/sia/tp3/testdata/";

    public Poblacion(String personaje, Multiplicador multiplicador) {
        this.numeroDeGeneracion = 1;

        List<Arma> armas = leerArmas();
        List<Bota> botas = leerBotas();
        List<Casco> cascos = leerCascos();
        List<Guante> guantes = leerGuantes();
        List<Pechera> pecheras = leerPecheras();
        cantidadPersonajes = Math.min(Math.min(Math.min(Math.min(armas.size(), botas.size()), cascos.size()),
                guantes.size()), pecheras.size());
        personajes = new ArrayList<>();

        Iterator iteratorArmas = armas.iterator();
        Iterator iteratorBotas = botas.iterator();
        Iterator iteratorCascos = cascos.iterator();
        Iterator iteratorGuantes = guantes.iterator();
        Iterator iteratorPecheras = pecheras.iterator();

        for (int i = 0; i < cantidadPersonajes; i++) {
            personajes.add(new Personaje(personaje, multiplicador, (Arma) iteratorArmas.next(),
                    (Bota) iteratorBotas.next(), (Casco) iteratorCascos.next(), (Guante) iteratorGuantes.next(),
                    (Pechera) iteratorPecheras.next()));
        }

        recalcularMejorDesempenio();
        mejoresDesempenios = new ArrayList<>();
        mejoresDesempenios.add(mejorDesempenio);
    }

    public ArrayList<Personaje> getPersonajes() {
        return personajes;
    }

    public void setPersonajes(ArrayList<Personaje> personajes) {
        this.personajes = personajes;
    }

    public List<Arma> leerArmas() {
        List<Arma> armas = new LinkedList<>();
        this.cantidadPersonajes = 0;
        try {
            BufferedReader buffer = new BufferedReader(new FileReader(path + "armas.tsv"));
            String line;
            String[] datos;
            buffer.readLine();

            while ((line = buffer.readLine()) != null) {

                datos = line.split("\t");
                armas.add(Integer.parseInt(datos[0]), new Arma(Double.parseDouble(datos[1]),
                        Double.parseDouble(datos[2]), Double.parseDouble(datos[3]), Double.parseDouble(datos[4]),
                        Double.parseDouble(datos[5])));
                this.cantidadPersonajes++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return armas;
    }

    public List<Bota> leerBotas() {
        List<Bota> botas = new LinkedList<>();
        try {
            BufferedReader buffer = new BufferedReader(new FileReader(path + "botas.tsv"));
            String line;
            String[] datos;
            buffer.readLine();

            while ((line = buffer.readLine()) != null) {

                datos = line.split("\t");
                botas.add(Integer.parseInt(datos[0]), new Bota(Double.parseDouble(datos[1]),
                        Double.parseDouble(datos[2]), Double.parseDouble(datos[3]), Double.parseDouble(datos[4]),
                        Double.parseDouble(datos[5])));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return botas;
    }

    public List<Casco> leerCascos() {
        List<Casco> cascos = new LinkedList<>();
        try {
            BufferedReader buffer = new BufferedReader(new FileReader(path + "cascos.tsv"));
            String line;
            String[] datos;
            buffer.readLine();

            while ((line = buffer.readLine()) != null) {

                datos = line.split("\t");
                cascos.add(Integer.parseInt(datos[0]), new Casco(Double.parseDouble(datos[1]),
                        Double.parseDouble(datos[2]), Double.parseDouble(datos[3]), Double.parseDouble(datos[4]),
                        Double.parseDouble(datos[5])));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return cascos;
    }

    public List<Guante> leerGuantes() {
        List<Guante> guantes = new LinkedList<>();
        try {
            BufferedReader buffer = new BufferedReader(new FileReader(path + "guantes.tsv"));
            String line;
            String[] datos;
            buffer.readLine();

            while ((line = buffer.readLine()) != null) {

                datos = line.split("\t");
                guantes.add(Integer.parseInt(datos[0]), new Guante(Double.parseDouble(datos[1]),
                        Double.parseDouble(datos[2]), Double.parseDouble(datos[3]), Double.parseDouble(datos[4]),
                        Double.parseDouble(datos[5])));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return guantes;
    }

    public List<Pechera> leerPecheras() {
        List<Pechera> pecheras = new LinkedList<>();
        try {
            BufferedReader buffer = new BufferedReader(new FileReader(path + "pecheras.tsv"));
            String line;
            String[] datos;
            buffer.readLine();

            while ((line = buffer.readLine()) != null) {

                datos = line.split("\t");
                pecheras.add(Integer.parseInt(datos[0]), new Pechera(Double.parseDouble(datos[1]),
                        Double.parseDouble(datos[2]), Double.parseDouble(datos[3]), Double.parseDouble(datos[4]),
                        Double.parseDouble(datos[5])));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return pecheras;
    }

    public int getNumeroDeGeneracion() {
        return numeroDeGeneracion;
    }

    public void resetNumeroDeGeneracion() {
        numeroDeGeneracion = 0;
    }

    public void aumentarGeneracion() {
        numeroDeGeneracion++;
    }

    public double getMejorDesempenio() {
        return mejorDesempenio;
    }

    public void recalcularMejorDesempenio() {
        double mejorDesempenio = 0;
        for (Personaje personaje : personajes) {
            if (personaje.getDesempenio() > mejorDesempenio) {
                mejorDesempenio = personaje.getDesempenio();
            }
        }

        this.mejorDesempenio = mejorDesempenio;
    }

    public ArrayList<Double> getMejoresDesempenios() {
        return mejoresDesempenios;
    }

    public void agregarMejorDesempenio() {
        recalcularMejorDesempenio();
        mejoresDesempenios.add(mejorDesempenio);
    }
}
