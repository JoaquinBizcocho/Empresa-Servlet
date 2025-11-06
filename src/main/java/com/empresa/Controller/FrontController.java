package com.empresa.Controller;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import java.util.List;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.empresa.empleados.Empleados; 
import com.empresa.empladoDAO.DAO; 

// Este servlet centraliza TODAS las peticiones de la aplicación
@WebServlet("/app")
public class FrontController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // Se obtiene la acción desde el parámetro o 'opcion'
        String opcion = request.getParameter("opcion");
        
       

        // Se crea una instancia del controlador correspondiente
        EmpleadoController ec = new EmpleadoController();

        // Dependiendo de la acción, se llama al método adecuado
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
                // Si no se reconoce la acción, se redirige a una página de error
                response.sendRedirect("views/error.jsp");
                break;
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Redirige las peticiones POST al método GET
        doGet(request, response);
    }
}
