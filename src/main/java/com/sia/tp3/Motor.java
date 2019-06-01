package com.sia.tp3;

import com.sia.tp3.reemplazo.InterfazReemplazo;

import java.util.ArrayList;

public class Motor {

    private InterfazReemplazo reemplazo1;
    private InterfazReemplazo reemplazo2;
    private double modificadorB;

    public Motor(final InterfazReemplazo reemplazo1, final InterfazReemplazo reemplazo2, final double modificadorB) {
        this.reemplazo1 = reemplazo1;
        this.reemplazo2 = reemplazo2;
        this.modificadorB = modificadorB;
    }

    public ArrayList<Personaje> correr(Poblacion poblacion) {

        ArrayList<Personaje> nuevaGeneracionReemplazo1 = reemplazo1.hacer(poblacion.getPersonajes());
        ArrayList<Personaje> nuevaGeneracionReemplazo2 = reemplazo2.hacer(poblacion.getPersonajes());

        int cantidadNuevaGeneracionReemplazo1 = new Double(poblacion.getPersonajes().size() * modificadorB).intValue();
        int cantidadNuevaGeneracionReemplazo2 = poblacion.getPersonajes().size() - cantidadNuevaGeneracionReemplazo1;

        ArrayList<Personaje> ret = new ArrayList<>();

        for (int i = 0; i < cantidadNuevaGeneracionReemplazo1; i++) {
            ret.add(nuevaGeneracionReemplazo1.get(i));
        }

        for (int i = 0; i < cantidadNuevaGeneracionReemplazo2; i++) {
            ret.add(nuevaGeneracionReemplazo2.get(i));
        }

        // SOLO PARA DEBUGGEAR
        if (ret.size() != poblacion.getPersonajes().size()) {
            throw new RuntimeException("1 - ERROR EN MOTOR");
        }

        return ret;
    }
}
