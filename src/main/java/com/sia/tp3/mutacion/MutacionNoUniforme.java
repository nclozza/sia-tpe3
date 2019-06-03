package com.sia.tp3.mutacion;

public class MutacionNoUniforme implements InterfazProbabilidad {

    @Override
    public double obtenerProbabilidad(final int numeroDeGeneracion) {
        //Esta funcion debe devolver un valor [0, 1]
        return 1.0 / numeroDeGeneracion;
    }
}
