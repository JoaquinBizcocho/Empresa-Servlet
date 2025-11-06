package com.empresa.command;

public class FactoryCommand {

    public static Command getCommand(String opcion) {
        if (opcion == null) {
            return null;
        }

        switch (opcion) {
            case "listar":
                return new ListarCommand();
            case "buscarPorDni":
                return new BuscarPorDniCommand();
            case "editarPorDni":
                return new EditarPorDniCommand();
            case "actualizarEmpleado":
                return new ActualizarEmpleadoCommand(); 
             default:
            	 return null;
        }
    }
}
