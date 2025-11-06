package com.empresa.Controller;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import java.util.List;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.empresa.empleados.Empleados;
import com.empresa.command.Command;
import com.empresa.command.FactoryCommand;
import com.empresa.empladoDAO.DAO; 

// Este servlet centraliza TODAS las peticiones de la aplicación
@WebServlet("/app")
public class FrontController extends HttpServlet {
	private static final long serialVersionUID = 1L;


	@Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // Se obtiene la acción desde el parámetro 'opcion'
        String opcion = request.getParameter("opcion");
        
       

        // Se crea una instancia del controlador correspondiente
        //EmpleadoController ec = new EmpleadoController();
        Command command = FactoryCommand.getCommand(opcion);

        if(command != null) {
        	command.ejecutar(request, response);
        } else {
        	RequestDispatcher rd = request.getRequestDispatcher("error.jsp");
            rd.forward(request, response);
        }
    }
        
        
        /*
        // Dependiendo de la opcion, se llama al método adecuado
        switch (opcion) {
            case "listar":
            	ec.listar(request, response);
                break;

            case "buscarPorDni":
                ec.buscarPorDni(request, response);
                break;

            case "editarPorDni":
                ec.editarPorDni(request, response);
                break;

            case "actualizarEmpleado":
                ec.actualizarEmpleado(request, response);
                break;

            default:
                // Si no se reconoce la opcion, se redirige a una página de error
                response.sendRedirect("views/error.jsp");
                break;
        }
    }*/

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Redirige las peticiones POST al método GET
        doGet(request, response);
    }
}
