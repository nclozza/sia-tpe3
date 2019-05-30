package com.sia.tp3.reemplazo;

import com.sia.tp3.Personaje;

import java.util.ArrayList;
import java.util.Random;

public class Reemplazo2 implements InterfazReemplazo {

    private long k;

    public Reemplazo2(final long k) {
        this.k = k;
    }

    public long getK() {
        return k;
    }

    @Override
    public ArrayList<Personaje> hacer(final ArrayList<Personaje> padres, final ArrayList<Personaje> nuevaGeneracion) {
        // TODO: SOLO PARA DEBUG
        if (k != nuevaGeneracion.size()) {
            throw new RuntimeException("ERROR EN EL METODO DE REEMPLAZO 2");
        }

        ArrayList<Personaje> kPadres = new ArrayList<>();

        Random r = new Random();

        for (int i = 0; i < padres.size() - k; i++) {
            kPadres.add(padres.get(r.nextInt(padres.size())));
        }

        kPadres.addAll(nuevaGeneracion);

        return kPadres;
    }

    @Override
    public ArrayList<Personaje> seleccionarIndividuosParaReproduccion(final ArrayList<Personaje> personajes) {
        ArrayList<Personaje> ret = new ArrayList<>();
        Random r = new Random();

        for (int i = 0; i < personajes.size() - k; i++) {
            ret.add(personajes.get(r.nextInt(personajes.size())));
        }

        return ret;
    }
}
