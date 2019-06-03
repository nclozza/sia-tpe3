package com.sia.tp3.seleccion;

import com.sia.tp3.Personaje;

import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.Random;

public class Ruleta implements InterfazSeleccion {

    private boolean usaBoltzmann;

    public Ruleta(final boolean usaBoltzmann) {
        this.usaBoltzmann = usaBoltzmann;
    }

    @Override
    public ArrayList<Personaje> hacer(final ArrayList<Personaje> personajes, final int cantidad,
                                      final int numeroDeGeneracion) {

        ArrayList<Personaje> aux = personajes;

        if (usaBoltzmann) {
            aux = new ArrayList<>();
            for (Personaje personaje : personajes) {
                aux.add(personaje.copy());
            }

            Boltzmann boltzmann = new Boltzmann();
            boltzmann.hacer(aux, numeroDeGeneracion);
        }

        PriorityQueue<Double> numerosAleatorios = generarAleatorios(cantidad);

        if (aux.get(0).getDesempenioRelativo() == 0.0) {
            calcularDesempenioRelativoYAcumulado(aux, sumaDesempenio(aux));
        }

        return seleccionarPersonajes(aux, numerosAleatorios);
    }

    private PriorityQueue<Double> generarAleatorios(final int cantidad) {

        PriorityQueue<Double> numeroAleatorios = new PriorityQueue<>(Double::compare);
        Random r = new Random();
        double aleatorio;
        int contador = cantidad;

        while (contador != 0) {

            do {
                aleatorio = (1.0) * r.nextDouble();

                if (!numeroAleatorios.contains(aleatorio)) {
                    numeroAleatorios.add(aleatorio);
                    break;
                }
            } while (true);

            contador--;
        }

        return numeroAleatorios;
    }

    private double sumaDesempenio(final ArrayList<Personaje> personajes) {

        double suma = 0.0;

        for (Personaje p : personajes) {
            suma += p.getDesempenio();
        }

        return suma;
    }

    private void calcularDesempenioRelativoYAcumulado(final ArrayList<Personaje> personajes,
                                                      final double sumaDesempenioTotal) {

        double acumulado = 0.0;
        for (Personaje p : personajes) {
            p.setDesempenioRelativo(p.getDesempenio() / sumaDesempenioTotal);
            acumulado += p.getDesempenioRelativo();
            p.setDesempenioAcumulado(acumulado);
        }
    }

    private ArrayList<Personaje> seleccionarPersonajes(final ArrayList<Personaje> personajes,
                                                       final PriorityQueue<Double> numerosAleatorios) {

        ArrayList<Personaje> ret = new ArrayList<>();
        double aleatorio;
        int i = 0;

        while (!numerosAleatorios.isEmpty()) {

            aleatorio = numerosAleatorios.remove();

            while (i < personajes.size()) {

                if (personajes.get(i).getDesempenioAcumulado() > aleatorio) {

                    ret.add(personajes.get(i));
                    break;
                } else
                    i++;
            }
        }
        return ret;
    }
}
