package com.empresa.command;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.empresa.empleados.Empleados;
import com.empresa.empladoDAO.DAO;
import com.empresa.empladoDAO.DAOFactory;


 //lista los empleados.

public class ListarCommand implements Command {

    @Override
    public void ejecutar(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
    DAO empleadoDAO = DAOFactory.crearEmpleadoDAO();	// Se crea una instancia del DAO para acceder a la base de datos
	
	List<Empleados> lista = new ArrayList<>();	// Lista donde se guardaran los empleados obtenidos de la base de datos
	try {
		lista = empleadoDAO.obtenerEmpleados(); // Se llama al metodo que devuelve todos los empleados desde la base de datos
		
		// Se recorre la lista y se muestra por consola
		for (Empleados empleados : lista) {
			System.out.println(empleados);
		}

		request.setAttribute("lista", lista); 	// Se guarda la lista como atributo en el objeto request para enviarla al JSP
		
		// Se reenvia la petición a la pagina JSP que mostrara la lista de empleados
		RequestDispatcher requestDispatcher = request.getRequestDispatcher("/views/listar.jsp");
		requestDispatcher.forward(request, response);

	} catch (SQLException e) {
		// Si ocurre un error de SQL, se imprime el error en la consola
		e.printStackTrace();
	} 
	}
}
