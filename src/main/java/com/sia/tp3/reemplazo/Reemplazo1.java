package com.sia.tp3.reemplazo;

import com.sia.tp3.Personaje;
import com.sia.tp3.cruce.InterfazCruce;
import com.sia.tp3.mutacion.InterfazMutacion;
import com.sia.tp3.seleccion.InterfazSeleccion;

import java.util.ArrayList;

public class Reemplazo1 extends Reemplazo implements InterfazReemplazo {

    private InterfazMutacion mutacion;

    public Reemplazo1(final InterfazSeleccion seleccion1, final InterfazSeleccion seleccion2,
                      final InterfazCruce cruce, final InterfazMutacion mutacion) {
        super(seleccion1, seleccion2, cruce);
        this.mutacion = mutacion;
    }

    @Override
    public ArrayList<Personaje> hacer(final ArrayList<Personaje> personajes) {

        ArrayList<Personaje> ret = cruzarIndividuos(personajes);
        mutacion.hacer(ret);

        return ret;
    }
}
