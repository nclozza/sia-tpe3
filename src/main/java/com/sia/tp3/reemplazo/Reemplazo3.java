package com.sia.tp3.reemplazo;

import com.sia.tp3.Personaje;

import java.util.ArrayList;
import java.util.Random;

public class Reemplazo3 implements InterfazReemplazo {

    private int cantidad;

    public Reemplazo3(final int cantidad) {
        this.cantidad = cantidad;
    }

    @Override
    public ArrayList<Personaje> hacer(final ArrayList<Personaje> padres, final ArrayList<Personaje> nuevaGeneracion) {
        ArrayList<Personaje> ret = new ArrayList<>();

        Random r = new Random();

        for (int i = 0; i < padres.size() - cantidad; i++) {
            ret.add(padres.get(r.nextInt(padres.size())));
        }

        for (int i = 0; i < cantidad; i++) {
            if (r.nextDouble() < 0.5) {
                ret.add(padres.get(r.nextInt(padres.size())));
            } else {
                ret.add(nuevaGeneracion.get(r.nextInt(nuevaGeneracion.size())));
            }
        }

        return ret;
    }

    @Override
    public int getCantidad() {
        return cantidad;
    }
}
