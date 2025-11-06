package com.empresa.command;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.empresa.empleados.Empleados;
import com.empresa.empladoDAO.DAO;
import com.empresa.empladoDAO.DAOFactory;
import com.empresa.NominaMetodo.NominaMetodos;

//Buscar sueldo base de empleado por DNI
public class BuscarPorDniCommand implements Command {

    @Override
    public void ejecutar(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
	    String dni = request.getParameter("dni"); // Se obtiene el DNI ingresado por el usuario
	    DAO dao = DAOFactory.crearEmpleadoDAO();
	    Empleados empleado = dao.buscarPorDni(dni);  // Se busca al empleado mediante su DNI

	    // Si el empleado existe
	    if (empleado != null) {
	        double sueldoBase = NominaMetodos.sueldo(empleado); // Se calcula su sueldo base usando la clase NominaMetodos
	        
	     // Se guardan los datos del empleado y su sueldo en el request
	        request.setAttribute("empleado", empleado);
	        request.setAttribute("sueldoBase", sueldoBase);
	    }
		// Se reenvía la información al JSP que muestra los datos del empleado buscado
	    request.getRequestDispatcher("/views/buscarEmpleado.jsp").forward(request, response);
	}
}
