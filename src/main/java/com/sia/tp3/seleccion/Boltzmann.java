package com.sia.tp3.seleccion;

import com.sia.tp3.Personaje;
import com.sia.tp3.reemplazo.InterfazReemplazo;

import java.util.ArrayList;

public class Boltzmann {

    public ArrayList<Personaje> hacer(ArrayList<Personaje> personajes, int generacion) {

        Double temperatura = temperatura(Double.valueOf(generacion));
        Double promedio = calcularPromedio(personajes, temperatura);

        recalcularDesempenio(personajes, temperatura, promedio);

        return personajes;
    }

    private Double calcularPromedio(ArrayList<Personaje> personajes, Double temperatura){

        Double suma = 0.0;

        for(Personaje p : personajes){
            suma += Math.exp(p.getDesempenio()/temperatura);
        }

        return suma/personajes.size();
    }

    private Double temperatura (Double generacion){
        return 1/generacion;
    }

    private void recalcularDesempenio(ArrayList<Personaje> personajes, Double temperatura,Double promedio){

        Double func;
        for (Personaje p: personajes){

            func = Math.exp(p.getDesempenio()/temperatura) / promedio;
            p.setDesempenio(func);
        }
    }
}
