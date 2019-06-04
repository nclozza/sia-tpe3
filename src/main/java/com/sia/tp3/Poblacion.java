package com.sia.tp3;

import com.sia.tp3.items.*;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class Poblacion {

    private String path;
    private ArrayList<Personaje> personajes;
    private Integer cantidadPersonajes;
    private int numeroDeGeneracion;
    private double mejorDesempenio;
    private double peorDesempenio;
    private double desempenioPromedio;
    private ArrayList<Double> mejoresDesempenios;
    private HashSet<String> hashSetPersonajes;

    public Poblacion(String personaje, Multiplicador multiplicador, final String path) {
        this.numeroDeGeneracion = 1;

        this.path = path;

        List<Arma> armas = leerArmas();
        List<Bota> botas = leerBotas();
        List<Casco> cascos = leerCascos();
        List<Guante> guantes = leerGuantes();
        List<Pechera> pecheras = leerPecheras();
        cantidadPersonajes = Math.min(Math.min(Math.min(Math.min(armas.size(), botas.size()), cascos.size()),
                guantes.size()), pecheras.size());
        personajes = new ArrayList<>();
        hashSetPersonajes = new HashSet<>();

        Iterator iteratorArmas = armas.iterator();
        Iterator iteratorBotas = botas.iterator();
        Iterator iteratorCascos = cascos.iterator();
        Iterator iteratorGuantes = guantes.iterator();
        Iterator iteratorPecheras = pecheras.iterator();

        Personaje aux;
        for (int i = 0; i < cantidadPersonajes; i++) {
            aux = new Personaje(personaje, multiplicador, (Arma) iteratorArmas.next(),
                    (Bota) iteratorBotas.next(), (Casco) iteratorCascos.next(), (Guante) iteratorGuantes.next(),
                    (Pechera) iteratorPecheras.next());

            personajes.add(aux);
            hashSetPersonajes.add(aux.toHash());
        }

        recalcularDesempenios();
        mejoresDesempenios = new ArrayList<>();
        mejoresDesempenios.add(mejorDesempenio);

        System.out.println("Se terminó de inicializar la población\n");
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
                armas.add(new Arma(Integer.parseInt(datos[0]), Double.parseDouble(datos[1]),
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
                botas.add(new Bota(Integer.parseInt(datos[0]), Double.parseDouble(datos[1]),
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
                cascos.add(new Casco(Integer.parseInt(datos[0]), Double.parseDouble(datos[1]),
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
                guantes.add(new Guante(Integer.parseInt(datos[0]), Double.parseDouble(datos[1]),
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
                pecheras.add(new Pechera(Integer.parseInt(datos[0]), Double.parseDouble(datos[1]),
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

    public void recalcularDesempenios() {
        double mejorDesempenio = 0;
        double peorDesempenio = personajes.get(0).getDesempenio();
        double sumaDesempenios = 0;
        for (Personaje personaje : personajes) {
            if (personaje.getDesempenio() > mejorDesempenio) {
                mejorDesempenio = personaje.getDesempenio();
            }

            if (personaje.getDesempenio() < peorDesempenio) {
                peorDesempenio = personaje.getDesempenio();
            }

            sumaDesempenios += personaje.getDesempenio();
        }

        this.mejorDesempenio = mejorDesempenio;
        this.peorDesempenio = peorDesempenio;
        this.desempenioPromedio = sumaDesempenios / personajes.size();
    }

    public ArrayList<Double> getMejoresDesempenios() {
        return mejoresDesempenios;
    }

    public void agregarMejorDesempenio() {
        recalcularDesempenios();
        mejoresDesempenios.add(mejorDesempenio);
    }

    public HashSet<String> getHashSetPersonajes() {
        return hashSetPersonajes;
    }

    public void aniadirTodosLosPersonajesAlHashSet() {
        hashSetPersonajes = new HashSet<>();
        for (Personaje personaje : personajes) {
            hashSetPersonajes.add(personaje.toHash());
        }
    }

    public double getPeorDesempenio() {
        return peorDesempenio;
    }

    public double getDesempenioPromedio() {
        return desempenioPromedio;
    }
}
