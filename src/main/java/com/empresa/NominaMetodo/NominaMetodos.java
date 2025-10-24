package com.empresa.NominaMetodo;

import com.empresa.empleados.Empleados;

public class NominaMetodos{
    
    //tabla
    private static final int SUELDO_BASE[] = {50000, 70000, 90000, 110000, 130000, 150000, 170000, 190000, 210000, 230000};
    
    
    //metodo
    
    public int sueldo(Empleados emp){
        return SUELDO_BASE[emp.getCategoria() - 1] + emp.getAnios() * 5000; 
    }
    
    
    
    
    
    }