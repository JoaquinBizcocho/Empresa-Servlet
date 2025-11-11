package com.empresa.Strategy;

import com.empresa.empleados.Empleados;

// Contexto que permite cambiar dinámicamente la estrategia
public class Calculadora {
    
    private CalculoSalarioStrategy estrategia;

    // Permite cambiar la estrategia en tiempo de ejecucion
    public void setEstrategia(CalculoSalarioStrategy estrategia) {
        this.estrategia = estrategia;
    }

    // Ejecuta el calculo usando la estrategia que hemos seleccionado
    public double ejecutarCalculo(Empleados emp) {
        if (estrategia == null) {
            throw new IllegalStateException("No se ha definido ninguna estrategia.");
        }
        return estrategia.calcularSalario(emp);
    }
}
