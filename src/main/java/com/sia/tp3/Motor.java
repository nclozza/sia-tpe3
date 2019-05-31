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
    private InterfazReemplazo reemplazo1;
    private InterfazReemplazo reemplazo2;
    private InterfazSeleccion seleccion1;
    private InterfazSeleccion seleccion2;
    private int cantidadSeleccion1;
    private int cantidadSeleccion2;
    private int cantidadPoblacionAReemplazar1;
    private int cantidadPoblacionAReemplazar2;

    public Motor(final InterfazCruce cruce, final InterfazMutacion mutacion, final InterfazReemplazo reemplazo1,
                 final InterfazReemplazo reemplazo2, final InterfazSeleccion seleccion1,
                 final InterfazSeleccion seleccion2, final int cantidadSeleccion1, final int cantidadSeleccion2,
                 final int cantidadPoblacionAReemplazar1, final int cantidadPoblacionAReemplazar2) {
        this.cruce = cruce;
        this.mutacion = mutacion;
        this.reemplazo1 = reemplazo1;
        this.reemplazo2 = reemplazo2;
        this.seleccion1 = seleccion1;
        this.seleccion2 = seleccion2;
        this.cantidadSeleccion1 = cantidadSeleccion1;
        this.cantidadSeleccion2 = cantidadSeleccion2;
        this.cantidadPoblacionAReemplazar1 = cantidadPoblacionAReemplazar1;
        this.cantidadPoblacionAReemplazar2 = cantidadPoblacionAReemplazar2;
    }

    public ArrayList<Personaje> correr(Poblacion poblacion) {

        ArrayList<Personaje> individuosParaReproduccion = new ArrayList<>();
        ArrayList<Personaje> individuosSeleccionadosParaReproduccion1 = seleccion1.hacer(poblacion.getPersonajes(),
                reemplazo1.getCantidad());
        ArrayList<Personaje> individuosSeleccionadosParaReproduccion2 = seleccion1.hacer(poblacion.getPersonajes(),
                reemplazo2.getCantidad());

        individuosParaReproduccion.addAll(individuosSeleccionadosParaReproduccion1);
        individuosParaReproduccion.addAll(individuosSeleccionadosParaReproduccion2);

        ArrayList<Personaje> individuosRecombinados = recombinarIndividuos(individuosParaReproduccion);

        mutacion.hacer(individuosRecombinados);

        //TODO: ACA VENDRIA EVALUAR EL FITNESS DE LOS INDIVIDUOS RECOMBINADOS

        ArrayList<Personaje> ret = new ArrayList<>();
        ArrayList<Personaje> personajesReemplazo1 = reemplazo1.hacer(poblacion.getPersonajes(),
                individuosRecombinados);
        ArrayList<Personaje> personajesReemplazo2 = reemplazo2.hacer(poblacion.getPersonajes(),
                individuosRecombinados);

        for (int i = 0; i < cantidadPoblacionAReemplazar1; i++) {
            ret.add(personajesReemplazo1.get(i));
        }

        for (int i = 0; i < cantidadPoblacionAReemplazar2; i++) {
            ret.add(personajesReemplazo2.get(i));
        }

        return ret;
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
