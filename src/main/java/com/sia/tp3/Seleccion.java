package com.sia.tp3;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

public class Seleccion {

    public static Personaje[] seleccionar(Personaje[] personajes, Personaje[] nuevosPersonajes) {
        return elite(personajes, nuevosPersonajes);
    }

    private static Personaje[] elite(Personaje[] personajes, Personaje[] nuevosPersonajes) {
        Personaje[] retPersonajes = new Personaje[personajes.length];

        PriorityQueue<Personaje> priorityQueue = new PriorityQueue<>(Comparator.comparingDouble(Personaje::getDesempenio));

        priorityQueue.addAll(Arrays.asList(personajes));
        priorityQueue.addAll(Arrays.asList(nuevosPersonajes));

        for (int i = 0; i < retPersonajes.length; i++) {
            retPersonajes[i] = priorityQueue.poll();
        }

        return retPersonajes;
    }
}
