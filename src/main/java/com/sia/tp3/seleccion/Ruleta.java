package com.sia.tp3.seleccion;

import com.sia.tp3.Personaje;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.Random;

public class Ruleta implements InterfazSeleccion {

    private boolean usaBoltzmann;

    public Ruleta(boolean usaBoltzmann) {
        this.usaBoltzmann = usaBoltzmann;
    }

    @Override
    public ArrayList<Personaje> hacer(ArrayList<Personaje> personajes, int cantidad) {

        PriorityQueue<Double> numerosAleatorios = generarAleatorios(cantidad);

        if (personajes.get(0).getDesempenioRelativo() == 0.0) {
            calcularDesempenioRelativoYAcumulado(personajes, sumaDesempenio(personajes));
        }

        ArrayList<Personaje> ret = seleccionarPersonajes(personajes, numerosAleatorios);

        return  ret;
    }

    private PriorityQueue<Double> generarAleatorios(int cantidad){

        PriorityQueue<Double> numeroAleatorios= new PriorityQueue<>(Double::compare);
        Random r = new Random();
        Double aleatorio;

        while (cantidad != 0){

            do{
                aleatorio = (1.0) * r.nextDouble();

                if (!numeroAleatorios.contains(aleatorio)){
                    numeroAleatorios.add(aleatorio);
                    break;
                }
            } while (true);

            cantidad--;
        }

        return numeroAleatorios;
    }

    private Double sumaDesempenio(ArrayList<Personaje> personajes){

        Double suma = 0.0;

        for(Personaje p : personajes){
            suma += p.getDesempenio();
        }

        return suma;
    }

    private void calcularDesempenioRelativoYAcumulado(ArrayList<Personaje> personajes, Double sumaDesempenioTotal){

        Double acumulado = 0.0;
        for(Personaje p : personajes){
            p.setDesempenioRelativo(p.getDesempenio()/sumaDesempenioTotal);
            acumulado += p.getDesempenioRelativo();
            p.setDesempenioAcumulado(acumulado);
        }
    }

    private ArrayList<Personaje> seleccionarPersonajes(ArrayList<Personaje> personajes, PriorityQueue<Double> numerosAleatorios){

        ArrayList<Personaje> ret = new ArrayList<>();
        Double aleatorio;
        int i = 0;

        while(!numerosAleatorios.isEmpty()){

            aleatorio = numerosAleatorios.remove();

            while (i< personajes.size()){

                if (personajes.get(i).getDesempenioAcumulado() > aleatorio){

                    ret.add(personajes.get(i));
                    break;
                }
                else
                    i++;
            }
        }
        return ret;
    }
}
