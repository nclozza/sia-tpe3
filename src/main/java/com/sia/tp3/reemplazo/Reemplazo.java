package com.sia.tp3.reemplazo;

import com.sia.tp3.Personaje;
import com.sia.tp3.cruce.InterfazCruce;
import com.sia.tp3.seleccion.InterfazSeleccion;

import java.util.ArrayList;
import java.util.Random;

public class Reemplazo {

    public InterfazSeleccion seleccion1;
    public InterfazSeleccion seleccion2;
    public InterfazCruce cruce;

    public Reemplazo(final InterfazSeleccion seleccion1, final InterfazSeleccion seleccion2,
                     final InterfazCruce cruce) {
        this.seleccion1 = seleccion1;
        this.seleccion2 = seleccion2;
        this.cruce = cruce;
    }

    public ArrayList<Personaje> seleccionar(final ArrayList<Personaje> personajes, final int cantidadSeleccion1,
                                            final int cantidadSeleccion2) {

        ArrayList<Personaje> individuosSeleccionados1 = seleccion1.hacer(personajes,
                cantidadSeleccion1);
        ArrayList<Personaje> individuosSeleccionados2 = seleccion2.hacer(personajes,
                cantidadSeleccion2);

        ArrayList<Personaje> ret = new ArrayList<>();
        ret.addAll(individuosSeleccionados1);
        ret.addAll(individuosSeleccionados2);

        return ret;
    }

    public ArrayList<Personaje> cruzarIndividuos(ArrayList<Personaje> individuosParaCruzar) {
        ArrayList<Personaje> nuevaGeneracion = new ArrayList<>();

        Random r = new Random();

        boolean par = true;
        int size = individuosParaCruzar.size();

        if ((individuosParaCruzar.size() % 2) != 0) {
            par = false;
            size = individuosParaCruzar.size() + 1;
        }

        int contadorNuevaGeneracion = 0;
        int individuo1;
        int individuo2;
        while (contadorNuevaGeneracion < size) {

            individuo1 = r.nextInt(individuosParaCruzar.size());
            do {
                individuo2 = r.nextInt(individuosParaCruzar.size());
            } while (individuo1 == individuo2);

            // Importante hacer la copia, para no pisar los padres originales
            nuevaGeneracion.add(individuosParaCruzar.get(individuo1).copy());
            nuevaGeneracion.add(individuosParaCruzar.get(individuo2).copy());

            cruce.hacer(nuevaGeneracion.get(contadorNuevaGeneracion),
                    nuevaGeneracion.get(contadorNuevaGeneracion + 1));

            contadorNuevaGeneracion += 2;
        }

        if (!par) {
            nuevaGeneracion.remove(size - 1);
        }

        return nuevaGeneracion;
    }

    public void recalcularTodosLosDesempenios(ArrayList<Personaje> personajes) {

        for (Personaje personaje : personajes) {
            personaje.recalcularDesempenio();
        }
    }
}
