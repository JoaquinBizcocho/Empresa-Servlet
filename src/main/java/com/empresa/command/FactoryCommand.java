package com.empresa.command;

public class FactoryCommand {

	//devuelve un objeto que implementa la interfaz command
    public static Command getCommand(String opcion) {
        if (opcion == null) {
            return null;
        }
        /*
        * Si la opcion que recibe por ejemplo es "listar" 
        * crea y devuelve un objeto de la clase ListarCommand
        */
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
