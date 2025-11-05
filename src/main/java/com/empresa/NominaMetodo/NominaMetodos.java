package com.empresa.NominaMetodo;

import com.empresa.empleados.Empleados;


    
    //metodo
    
    public class NominaMetodos {
        public static double sueldo(Empleados emp) {
            double sueldoBase = 0;

            switch (emp.getCategoria()) {
                case 1: sueldoBase = 50000; break;
                case 2: sueldoBase = 70000; break;
                case 3: sueldoBase = 90000; break;
                case 4: sueldoBase = 110000; break;
                case 5: sueldoBase = 130000; break;
                case 6: sueldoBase = 150000; break;
                case 7: sueldoBase = 170000; break;
                case 8: sueldoBase = 190000; break;
                case 9: sueldoBase = 210000; break;
                case 10: sueldoBase = 230000; break;
                default: sueldoBase = 0; break;
            }

            // se suma algo por años trabajados
            sueldoBase += emp.getAnios() * 25;

            return sueldoBase;
        }
    }

    
    
  