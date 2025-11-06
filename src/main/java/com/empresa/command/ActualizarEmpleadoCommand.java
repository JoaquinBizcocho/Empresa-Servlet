package com.empresa.command;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.empresa.empleados.Empleados;
import com.empresa.empladoDAO.DAO;
import com.empresa.empladoDAO.DAOFactory;

//Actualizamos empleado
public class ActualizarEmpleadoCommand implements Command {

    @Override
    public void ejecutar(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    	 Empleados emp = new Empleados(); // Se crea un nuevo objeto Empleados para almacenar los datos del formulario
		    
		 // Se obtienen los datos enviados desde el formulario y se asignan al objeto empleado
		    emp.setDni(request.getParameter("dni"));
		    emp.setNombre(request.getParameter("nombre"));
		    emp.setSexo(request.getParameter("sexo").charAt(0));
		    emp.setCategoria(Integer.parseInt(request.getParameter("categoria")));
		    emp.setAnios(Integer.parseInt(request.getParameter("anios")));

		    DAO dao = DAOFactory.crearEmpleadoDAO(); // Se crea un objeto DAO para actualizar los datos en la base de datos
	        boolean actualizado = dao.actualizarEmpleado(emp);

		    if (actualizado) {
	            request.setAttribute("mensaje", "Los cambios se han guardado correctamente.");
	        } else {
	            request.setAttribute("mensaje", "No se pudieron guardar los cambios.");
	        }
		    
		 // Se vuelve a enviar el objeto empleado y el mensaje al JSP de edición
		    request.setAttribute("empleado", emp);
		    request.getRequestDispatcher("/views/editarEmpelado.jsp").forward(request, response);
		}
    }

