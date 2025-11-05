package com.empresa.Controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.empresa.empleados.Empleados;
import com.empresa.empleados.Nomina;
import com.empresa.NominaMetodo.NominaMetodos;
import com.empresa.empladoDAO.DAO;

/**
 * Servlet implementation class ProductoController
 */

/**
 * Servlet implementation class ProductoController
 * Este servlet controla todas las peticiones relacionadas con la gestión de empleados.
 * Se encarga de listar, buscar, editar y actualizar empleados.
 */

@WebServlet(description = "administra peticiones para la tabla empleados", urlPatterns = { "/empleados" })
public class EmpleadoController extends HttpServlet {
	

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	
	/**
	 * Constructor del servlet.
	 * Llama al constructor de la superclase HttpServlet.
	 */
	public EmpleadoController() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	
	/**
	 * Método que gestiona las peticiones HTTP GET.
	 * Se ejecuta cuando se llama al servlet con el método GET desde el navegador o un formulario.
	 */
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub

		/* Se obtiene el parámetro "opcion" que indica qué acción debe realizar el servlet.*/
		String opcion = request.getParameter("opcion");

		
		/* OPCIÓN LISTAR EMPLEADOS 
		* Si el parámetro "opcion" tiene el valor "listar", se obtiene la lista completa de empleados.
		* */
		 if (opcion.equals("listar")) {

			
			DAO empleadoDAO = new DAO();	// Se crea una instancia del DAO para acceder a la base de datos
			
			List<Empleados> lista = new ArrayList<>();	// Lista donde se guardarán los empleados obtenidos de la base de datos
			try {
				lista = empleadoDAO.obtenerEmpleados(); // Se llama al método que devuelve todos los empleados desde la base de datos
				
				// Se recorre la lista y se muestra por consola
				for (Empleados empleados : lista) {
					System.out.println(empleados);
				}

				request.setAttribute("lista", lista); 	// Se guarda la lista como atributo en el objeto request para enviarla al JSP
				
				// Se reenvía la petición a la página JSP que mostrará la lista de empleados
				RequestDispatcher requestDispatcher = request.getRequestDispatcher("/views/listar.jsp");
				requestDispatcher.forward(request, response);

			} catch (SQLException e) {
				// Si ocurre un error de SQL, se imprime el error en la consola
				e.printStackTrace();
			} 
			}
		 
			
			// OPCIÓN BUSCAR EMPLEADO POR DNI 

			else if ("buscarPorDni".equals(opcion)) {
			    String dni = request.getParameter("dni"); // Se obtiene el DNI ingresado por el usuario
			    DAO dao = new DAO();
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

		 	// OPCIÓN EDITAR EMPLEADO POR DNI
			else if ("editarPorDni".equals(opcion)) {
			    String dni = request.getParameter("dni"); // Se obtiene el DNI del empleado a editar
			    DAO dao = new DAO();
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
		// response.getWriter().append("Served at: ").append(request.getContextPath());
	

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	
	/**
	 * Método que gestiona las peticiones HTTP POST.
	 * Se ejecuta cuando se envía un formulario con método POST (por ejemplo, al actualizar un empleado).
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		// Se obtiene el parámetro "opcion" para saber qué acción realizar
		String opcion = request.getParameter("opcion");
		DAO empleadoDAO = new DAO(); // Se crea un objeto DAO para realizar operaciones con la base de datos
		 
		// OPCIÓN ACTUALIZAR EMPLEADO
		 if ("actualizarEmpleado".equals(opcion)) {
			    Empleados emp = new Empleados(); // Se crea un nuevo objeto Empleados para almacenar los datos del formulario
			    
			 // Se obtienen los datos enviados desde el formulario y se asignan al objeto empleado
			    emp.setDni(request.getParameter("dni"));
			    emp.setNombre(request.getParameter("nombre"));
			    emp.setSexo(request.getParameter("sexo").charAt(0));
			    emp.setCategoria(Integer.parseInt(request.getParameter("categoria")));
			    emp.setAnios(Integer.parseInt(request.getParameter("anios")));

			    DAO dao = new DAO(); // Se crea un objeto DAO para actualizar los datos en la base de datos
			    
			 // Se vuelve a enviar el objeto empleado y el mensaje al JSP de edición
			    request.setAttribute("empleado", emp);
			    request.getRequestDispatcher("/views/editarEmpelado.jsp").forward(request, response);
			}

		// doGet(request, response);
	}

}