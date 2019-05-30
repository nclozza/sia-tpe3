package com.sia.tp3.seleccion;

import com.sia.tp3.Personaje;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.PriorityQueue;

public class Elite implements InterfazSeleccion {

    @Override
    public ArrayList<Personaje> hacer(final ArrayList<Personaje> padres, ArrayList<Personaje> nuevaGeneracion) {
        ArrayList<Personaje> ret = new ArrayList<>();

        PriorityQueue<Personaje> priorityQueue =
                new PriorityQueue<>(Comparator.comparingDouble(Personaje::getDesempenio));

        priorityQueue.addAll(padres);
        priorityQueue.addAll(nuevaGeneracion);

        for (int i = 0; i < padres.size(); i++) {
            ret.add(priorityQueue.poll());
        }

        return ret;
    }
}
