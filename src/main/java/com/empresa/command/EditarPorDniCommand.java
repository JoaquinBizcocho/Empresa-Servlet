package com.empresa.command;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.empresa.empleados.Empleados;
import com.empresa.empladoDAO.DAO;
import com.empresa.empladoDAO.DAOFactory;

//Editamos un empleado por DNI

public class EditarPorDniCommand implements Command {

    @Override
    public void ejecutar(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    	 String dni = request.getParameter("dni"); // Se obtiene el DNI del empleado a editar
		    DAO dao = DAOFactory.crearEmpleadoDAO();
		    Empleados empleado = dao.buscarPorDni(dni); // Se busca el empleado en la base de datos

		 // Si el empleado existe, se envía su información al JSP de edicion
		    if (empleado != null) {
		        request.setAttribute("empleado", empleado);
		        request.getRequestDispatcher("/views/editarEmpelado.jsp").forward(request, response);
		    } else {
		    	// Si no existe, se muestra un mensaje de error en el JSP
		        request.setAttribute("mensaje", "No se encontró ningún empleado con ese DNI.");
		        request.getRequestDispatcher("/views/editarEmpelado.jsp").forward(request, response);
		    }
		}
}
