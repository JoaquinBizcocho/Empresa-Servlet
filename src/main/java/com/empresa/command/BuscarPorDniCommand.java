package com.empresa.command;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.empresa.empleados.Empleados;
import com.empresa.empladoDAO.DAO;
import com.empresa.empladoDAO.DAOFactory;
import com.empresa.Strategy.*;

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
	        
	     // Creamos una instancia de calculadora que usaremos para ejecutar nuestros calculos
            Calculadora calculadora = new Calculadora();

            // Ponemos la estrategia que queramos usar
            // Aqui puedes cambiar la estrategia por cualquier otra
            calculadora.setEstrategia(new CalculoSalarioCompleto());

            // Calculamos el salario usando la estrategia elegida
            double sueldoCalculado = calculadora.ejecutarCalculo(empleado);
	        
	        
	     // Se guardan los datos del empleado y su sueldo calculado con el metodo strategy en el request
	        request.setAttribute("empleado", empleado);
	        request.setAttribute("sueldoBase", sueldoCalculado);
	    }
		// Se reenvía la información al JSP que muestra los datos del empleado buscado
	    request.getRequestDispatcher("/views/buscarEmpleado.jsp").forward(request, response);
	}
}
