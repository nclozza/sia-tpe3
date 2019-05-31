package com.sia.tp3.reemplazo;

import com.sia.tp3.Personaje;

import java.util.ArrayList;
import java.util.Random;

public class Reemplazo2 implements InterfazReemplazo {

    private int cantidad;

    public Reemplazo2(final int cantidad) {
        this.cantidad = cantidad;
    }

    @Override
    public ArrayList<Personaje> hacer(final ArrayList<Personaje> padres, final ArrayList<Personaje> nuevaGeneracion) {
        // TODO: SOLO PARA DEBUG
        if (cantidad != nuevaGeneracion.size()) {
            throw new RuntimeException("ERROR EN EL METODO DE REEMPLAZO 2");
        }

        ArrayList<Personaje> ret = new ArrayList<>();

        Random r = new Random();

        for (int i = 0; i < padres.size() - cantidad; i++) {
            ret.add(padres.get(r.nextInt(padres.size())));
        }

        ret.addAll(nuevaGeneracion);

        return ret;
    }

    @Override
    public ArrayList<Personaje> seleccionarIndividuosParaReproduccion(final ArrayList<Personaje> personajes) {
        ArrayList<Personaje> ret = new ArrayList<>();
        Random r = new Random();

        for (int i = 0; i < personajes.size() - cantidad; i++) {
            ret.add(personajes.get(r.nextInt(personajes.size())));
        }

        return ret;
    }

    @Override
    public int getCantidad() {
        return cantidad;
    }
}
