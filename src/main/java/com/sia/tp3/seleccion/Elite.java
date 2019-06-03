package com.sia.tp3.seleccion;

import com.sia.tp3.Personaje;

import java.util.ArrayList;
import java.util.PriorityQueue;

public class Elite implements InterfazSeleccion {

    private boolean usaBoltzmann;

    public Elite(boolean usaBoltzmann) {
        this.usaBoltzmann = usaBoltzmann;
    }

    @Override
    public ArrayList<Personaje> hacer(final ArrayList<Personaje> personajes, final int cantidad) {
        ArrayList<Personaje> ret = new ArrayList<>();

        PriorityQueue<Personaje> priorityQueue =
                new PriorityQueue<>((personaje1, personaje2) -> Double.compare(personaje2.getDesempenio(),
                        personaje1.getDesempenio()));

        priorityQueue.addAll(personajes);

        for (int i = 0; i < cantidad; i++) {
            ret.add(priorityQueue.poll());
        }

        return ret;
    }
}
