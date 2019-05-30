package com.sia.tp3;

import com.sia.tp3.Mutacion.InterfazMutacion;
import com.sia.tp3.cruce.InterfazCruce;
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
        ArrayList<Personaje> individuosParaReproduccion =
                reemplazo.seleccionarIndividuosParaReproduccion(poblacion.getPersonajes());

        ArrayList<Personaje> individuosRecombinados = recombinarIndividuos(individuosParaReproduccion);

        mutacion.hacer(individuosRecombinados);

        // seleccion.hacer(poblacion.getPersonajes(), individuosRecombinados);

        //TODO: ACA VENDRIA EVALUAR EL FITNESS DE LOS INDIVIDUOS RECOMBINADOS

        return reemplazo.hacer(poblacion.getPersonajes(), individuosRecombinados);
    }

    private ArrayList<Personaje> recombinarIndividuos(ArrayList<Personaje> personajes) {
        ArrayList<Personaje> nuevaGeneracion = new ArrayList<>();

        Random r = new Random();
        r.nextInt(personajes.size());

        int contadorNuevaGeneracion = 0;

        while (contadorNuevaGeneracion < personajes.size()) {
            nuevaGeneracion.add(personajes.get(r.nextInt(personajes.size())).copy());

            nuevaGeneracion.add(personajes.get(r.nextInt(personajes.size())).copy());

            cruce.hacer(nuevaGeneracion.get(contadorNuevaGeneracion), nuevaGeneracion.get(contadorNuevaGeneracion + 1));

            contadorNuevaGeneracion += 2;
        }

        if ((personajes.size() % 2) != 0) {
            nuevaGeneracion.remove(personajes.size());
        }

        return nuevaGeneracion;
    }

    public InterfazSeleccion getSeleccion() {
        return seleccion;
    }
}
