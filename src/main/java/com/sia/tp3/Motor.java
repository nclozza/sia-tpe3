package com.sia.tp3;

import com.sia.tp3.cruce.InterfazCruce;
import com.sia.tp3.mutacion.InterfazMutacion;
import com.sia.tp3.reemplazo.InterfazReemplazo;
import com.sia.tp3.seleccion.InterfazSeleccion;

import java.util.ArrayList;
import java.util.Random;

public class Motor {

    private InterfazCruce cruce;
    private InterfazMutacion mutacion;
    private InterfazReemplazo reemplazo;
    private InterfazSeleccion seleccion;

    public Motor(final InterfazCruce cruce, final InterfazMutacion mutacion, final InterfazReemplazo reemplazo,
                 final InterfazSeleccion seleccion) {
        this.cruce = cruce;
        this.mutacion = mutacion;
        this.reemplazo = reemplazo;
        this.seleccion = seleccion;
    }

    public ArrayList<Personaje> correr(Poblacion poblacion) {
        ArrayList<Personaje> individuosParaReproduccion = seleccion.hacer(poblacion.getPersonajes(),
                reemplazo.getCantidad());

        ArrayList<Personaje> individuosRecombinados = recombinarIndividuos(individuosParaReproduccion);
        
        mutacion.hacer(individuosRecombinados);

        //TODO: ACA VENDRIA EVALUAR EL FITNESS DE LOS INDIVIDUOS RECOMBINADOS

        return reemplazo.hacer(poblacion.getPersonajes(), individuosRecombinados);
    }

    private ArrayList<Personaje> recombinarIndividuos(ArrayList<Personaje> individuosParaReproduccion) {
        ArrayList<Personaje> nuevaGeneracion = new ArrayList<>();

        Random r = new Random();

        boolean par = true;
        int size = individuosParaReproduccion.size();

        if ((individuosParaReproduccion.size() % 2) != 0) {
            par = false;
            size = individuosParaReproduccion.size() + 1;
        }

        int contadorNuevaGeneracion = 0;
        int individuo1;
        int individuo2;
        while (contadorNuevaGeneracion < size) {

            individuo1 = r.nextInt(individuosParaReproduccion.size());
            do {
                individuo2 = r.nextInt(individuosParaReproduccion.size());
            } while (individuo1 != individuo2);

            // Importante hacer la copia, para no pisar los padres originales
            nuevaGeneracion.add(individuosParaReproduccion.get(individuo1).copy());
            nuevaGeneracion.add(individuosParaReproduccion.get(individuo2).copy());

            cruce.hacer(nuevaGeneracion.get(contadorNuevaGeneracion),
                    nuevaGeneracion.get(contadorNuevaGeneracion + 1));

            contadorNuevaGeneracion += 2;
        }

        if (!par) {
            nuevaGeneracion.remove(size - 1);
        }

        return nuevaGeneracion;
    }
}
