package com.sia.tp3.seleccion;

import com.sia.tp3.Genes;
import com.sia.tp3.Personaje;

import java.util.ArrayList;
import java.util.Random;

public class Ruleta implements InterfazSeleccion {


    @Override
    public ArrayList<Personaje> hacer(ArrayList<Personaje> personajes, int cantidad) {

        ArrayList<Double> numeroAleatorios = generarAleatorios(cantidad);
        ArrayList<Personaje> ret = new ArrayList<>();

        //TODO: No implementado

        return  null;
    }

    public ArrayList<Double> generarAleatorios(int cantidad){

        ArrayList<Double> numeroAleatorios = new ArrayList<>();
        Random r = new Random();

        while (cantidad != 0){
            numeroAleatorios.add((1.0) * r.nextDouble());
            cantidad--;
        }
        return numeroAleatorios;
    }
}
