package com.empresa.Strategy;

import com.empresa.empleados.Empleados;

// Interfaz que define la estrategia de cálculo del salario
public interface CalculoSalarioStrategy {
    double calcularSalario(Empleados empleado);
}