package com.sia.tp3.seleccion;

import com.sia.tp3.Personaje;

import java.util.*;

public class Ranking implements InterfazSeleccion {

    @Override
    public ArrayList<Personaje> hacer(ArrayList<Personaje> personajes, int cantidad) {

        //TODO: Verificar si estos parametros van en el .config
        cantidad = 3;

        ordenarPersonajes(personajes);
        PriorityQueue<Double> numerosAleatorios = generarAleatorios(cantidad);

        calcularDesempenioAcumulado(personajes, Double.valueOf(personajes.size()*(personajes.size()+1))/2);

        ArrayList<Personaje> ret = seleccionarPersonajes(personajes, numerosAleatorios);

        return ret;
    }

    private void ordenarPersonajes(ArrayList<Personaje> personajes){

        personajes.sort(Comparator.comparingDouble(Personaje::getDesempenio));
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

    private void calcularDesempenioAcumulado(ArrayList<Personaje> personajes, Double sumaDesempenioTotal){

        Double acumulado = 0.0;
        int i = 1;
        for(Personaje p : personajes){
            acumulado += i / sumaDesempenioTotal;
            p.setDesempenioAcumulado(acumulado);
            i++;
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
