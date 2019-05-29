package com.sia.tp3;

import com.sia.tp3.items.*;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

public class Poblacion {

    private List<Personaje> personajes;
    private Integer cantidadPersonajes;

    public Poblacion(String personaje, Multiplicador multiplicador) {

      List<Arma> armas = leerArmas();
      List<Bota> botas = leerBotas();
      List<Casco> cascos = leerCascos();
      List<Guante> guantes = leerGuantes();
      List<Pechera> pecheras = leerPecheras();
      this.personajes = new LinkedList<>();

      for (int i=0; i < this.cantidadPersonajes; i++){
        this.personajes.add(new Personaje(personaje, multiplicador, armas.get(i), botas.get(i), cascos.get(i), guantes.get(i), pecheras.get(i)));
      }
    }

    public List<Personaje> getPersonajes() {
        return personajes;
    }

    public void setPersonajes(List<Personaje> personajes) {
        this.personajes = personajes;
    }

    public List<Arma> leerArmas (){
        List<Arma> armas = new LinkedList<>();
        this.cantidadPersonajes = 0;
        try {
            BufferedReader buffer = new BufferedReader(new FileReader("src/main/java/com/sia/tp3/testdata/armas.tsv"));
            String line;
            String[] datos;
            buffer.readLine();

            while ((line = buffer.readLine()) != null){

                datos = line.split("\t");
                armas.add(Integer.parseInt(datos[0]),  new Arma(Double.parseDouble(datos[1]),Double.parseDouble(datos[2]),Double.parseDouble(datos[3]),Double.parseDouble(datos[4]),Double.parseDouble(datos[5])));
                this.cantidadPersonajes++;
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return armas;
    }

    public List<Bota> leerBotas (){
        List<Bota> botas = new LinkedList<>();
        try {
            BufferedReader buffer = new BufferedReader(new FileReader("src/main/java/com/sia/tp3/testdata/botas.tsv"));
            String line;
            String[] datos;
            buffer.readLine();

            while ((line = buffer.readLine()) != null){

                datos = line.split("\t");
                botas.add(Integer.parseInt(datos[0]),  new Bota(Double.parseDouble(datos[1]),Double.parseDouble(datos[2]),Double.parseDouble(datos[3]),Double.parseDouble(datos[4]),Double.parseDouble(datos[5])));
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return botas;
    }

    public List<Casco> leerCascos (){
        List<Casco> cascos = new LinkedList<>();
        try {
            BufferedReader buffer = new BufferedReader(new FileReader("src/main/java/com/sia/tp3/testdata/cascos.tsv"));
            String line;
            String[] datos;
            buffer.readLine();

            while ((line = buffer.readLine()) != null){

                datos = line.split("\t");
                cascos.add(Integer.parseInt(datos[0]),  new Casco(Double.parseDouble(datos[1]),Double.parseDouble(datos[2]),Double.parseDouble(datos[3]),Double.parseDouble(datos[4]),Double.parseDouble(datos[5])));
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return cascos;
    }

    public List<Guante> leerGuantes (){
        List<Guante> guantes = new LinkedList<>();
        try {
            BufferedReader buffer = new BufferedReader(new FileReader("src/main/java/com/sia/tp3/testdata/guantes.tsv"));
            String line;
            String[] datos;
            buffer.readLine();

            while ((line = buffer.readLine()) != null){

                datos = line.split("\t");
                guantes.add(Integer.parseInt(datos[0]),  new Guante(Double.parseDouble(datos[1]),Double.parseDouble(datos[2]),Double.parseDouble(datos[3]),Double.parseDouble(datos[4]),Double.parseDouble(datos[5])));
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return guantes;
    }

    public List<Pechera> leerPecheras (){
        List<Pechera> pecheras = new LinkedList<>();
        try {
            BufferedReader buffer = new BufferedReader(new FileReader("src/main/java/com/sia/tp3/testdata/pecheras.tsv"));
            String line;
            String[] datos;
            buffer.readLine();

            while ((line = buffer.readLine()) != null){

                datos = line.split("\t");
                pecheras.add(Integer.parseInt(datos[0]),  new Pechera(Double.parseDouble(datos[1]),Double.parseDouble(datos[2]),Double.parseDouble(datos[3]),Double.parseDouble(datos[4]),Double.parseDouble(datos[5])));
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return pecheras;
    }
}
