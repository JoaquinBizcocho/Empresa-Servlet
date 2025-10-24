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
import com.empresa.empladoDAO.DAO;

/**
 * Servlet implementation class ProductoController
 */
@WebServlet(description = "administra peticiones para la tabla empleados", urlPatterns = { "/empleados" })
public class EmpleadoController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public EmpleadoController() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub

		String opcion = request.getParameter("opcion");

		
		
		 if (opcion.equals("listar")) {

			DAO empleadoDAO = new DAO();
			List<Empleados> lista = new ArrayList<>();
			try {
				lista = empleadoDAO.obtenerEmpleados();
				for (Empleados empleados : lista) {
					System.out.println(empleados);
				}

				request.setAttribute("lista", lista);
				RequestDispatcher requestDispatcher = request.getRequestDispatcher("/views/listar.jsp");
				requestDispatcher.forward(request, response);

			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			System.out.println("Usted a presionado la opcion listar");
		} else if (opcion.equals("editar")) {
			String dni = (request.getParameter("dni"));
			System.out.println("Editar dni: " + dni);
			DAO empleadosDAO = new DAO();
			Empleados e = new Empleados();
			try {
				e = empleadosDAO.obtenerEmpleado(dni);
				System.out.println(e);
				request.setAttribute("empleados", e);
				RequestDispatcher requestDispatcher = request.getRequestDispatcher("/views/editar.jsp");
				requestDispatcher.forward(request, response);

			} catch (SQLException x) {
				// TODO Auto-generated catch block
				x.printStackTrace();
			}

		}
		// response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		String opcion = request.getParameter("opcion");
		DAO empleadoDAO = new DAO();
		
		 if (opcion.equals("editar")) {
			Empleados empleado = new Empleados();

			empleado.setDni(request.getParameter("dni"));
			empleado.setNombre(request.getParameter("nombre"));
			empleado.setSexo(request.getParameter("sexo").charAt(0));
			empleado.setCategoria(Integer.parseInt(request.getParameter("categoria")));
			empleado.setAnios(Integer.parseInt(request.getParameter("anios")));
			
			try {
				empleadoDAO.editar(empleado);
				System.out.println("Registro editado satisfactoriamente...");
				RequestDispatcher requestDispatcher = request.getRequestDispatcher("/index.jsp");
				requestDispatcher.forward(request, response);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		// doGet(request, response);
	}

}