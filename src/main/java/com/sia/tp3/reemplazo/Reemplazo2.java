package com.sia.tp3.reemplazo;

import com.sia.tp3.Personaje;
import com.sia.tp3.cruce.InterfazCruce;
import com.sia.tp3.mutacion.InterfazMutacion;
import com.sia.tp3.seleccion.InterfazSeleccion;

import java.util.ArrayList;

public class Reemplazo2 extends Reemplazo implements InterfazReemplazo {

    private double modificadorA;
    private int k;
    private InterfazMutacion mutacion;

    public Reemplazo2(final InterfazSeleccion seleccion1, final InterfazSeleccion seleccion2,
                      final InterfazCruce cruce, final double modificadorA, final int k,
                      final InterfazMutacion mutacion) {
        super(seleccion1, seleccion2, cruce);
        this.modificadorA = modificadorA;
        this.k = k;
        this.mutacion = mutacion;
    }

    @Override
    public ArrayList<Personaje> hacer(final ArrayList<Personaje> personajes) {

        int cantidadSeleccion1 = new Double(k * modificadorA).intValue();
        int cantidadSeleccion2 = k - cantidadSeleccion1;

        ArrayList<Personaje> individuosParaCruzar = seleccionar(personajes, cantidadSeleccion1, cantidadSeleccion2);

        // SOLO PARA DEBUGGEAR
        if (individuosParaCruzar.size() != k) {
            throw new RuntimeException("1 - ERROR EN REEMPLAZO 2");
        }

        ArrayList<Personaje> individuosCruzados = cruzarIndividuos(individuosParaCruzar);
        mutacion.hacer(individuosCruzados);

        // SOLO PARA DEBUGGEAR
        if (individuosCruzados.size() != k) {
            throw new RuntimeException("2 - ERROR EN REEMPLAZO 2");
        }

        cantidadSeleccion1 = new Double((personajes.size() - k) * modificadorA).intValue();
        cantidadSeleccion2 = (personajes.size() - k) - cantidadSeleccion1;

        ArrayList<Personaje> padresSeleccionados = seleccionar(personajes, cantidadSeleccion1, cantidadSeleccion2);
        ArrayList<Personaje> ret = new ArrayList<>(padresSeleccionados);

        // SOLO PARA DEBUGGEAR
        if (ret.size() != (personajes.size() - k)) {
            throw new RuntimeException("3 - ERROR EN REEMPLAZO 2");
        }

        cantidadSeleccion1 = new Double(k * modificadorA).intValue();
        cantidadSeleccion2 = k - cantidadSeleccion1;
        ret.addAll(seleccionar(individuosCruzados, cantidadSeleccion1, cantidadSeleccion2));

        // SOLO PARA DEBUGGEAR
        if (ret.size() != personajes.size()) {
            throw new RuntimeException("4 - ERROR EN REEMPLAZO 2");
        }

        return ret;
    }
}
