package com.sia.tp3;

import java.util.ArrayList;
import java.util.Random;

public class Reemplazo {

    private int metodo;
    private int k;

    public Reemplazo(final int metodo, final int k) {
        this.metodo = metodo;
        this.k = k;
    }

    public ArrayList<Personaje> obtenerNuevaGeneracion(ArrayList<Personaje> personajes) {

        ArrayList<Personaje> nuevaGeneracion;
        Random r = new Random();

        switch (metodo) {
            case 1:
                nuevaGeneracion = Cruce.nuevaGeneracion(personajes);
                Mutacion.mutarGeneracion(nuevaGeneracion);
                return nuevaGeneracion;

            case 2:
                ArrayList<Personaje> kPadres = new ArrayList<>();
                for (int i = 0; i < k; i++) {
                    kPadres.add(personajes.get(r.nextInt(personajes.size())));
                }

                nuevaGeneracion = Cruce.nuevaGeneracion(kPadres);
                Mutacion.mutarGeneracion(nuevaGeneracion);

                for (int i = 0; i < personajes.size() - k; i++) {
                    nuevaGeneracion.add(personajes.get(r.nextInt(personajes.size())));
                }

                return nuevaGeneracion;

            default:
                return personajes;
        }
    }

    public int getMetodo() {
        return metodo;
    }

    public int getK() {
        return k;
    }
}
