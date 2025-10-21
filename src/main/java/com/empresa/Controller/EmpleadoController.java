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

		if (opcion.equals("crear")) {
			System.out.println("Usted a presionado la opcion crear");
			RequestDispatcher requestDispatcher = request.getRequestDispatcher("/views/crear.jsp");
			requestDispatcher.forward(request, response);
		} else if (opcion.equals("listar")) {

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
			int dni = Integer.parseInt(request.getParameter("dni"));
			System.out.println("Editar dni: " + dni);
			DAO productoDAO = new DAO();
			Empleados p = new Empleados();
			try {
				p = productoDAO.obtenerProducto(dni);
				System.out.println(p);
				request.setAttribute("producto", p);
				RequestDispatcher requestDispatcher = request.getRequestDispatcher("/views/editar.jsp");
				requestDispatcher.forward(request, response);

			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		} else if (opcion.equals("eliminar")) {
			DAO empleadoDAO = new DAO();
			int id = Integer.parseInt(request.getParameter("id"));
			try {
				empleadoDAO.eliminar(id);
				System.out.println("Registro eliminado satisfactoriamente...");
				RequestDispatcher requestDispatcher = request.getRequestDispatcher("/index.jsp");
				requestDispatcher.forward(request, response);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
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

		if (opcion.equals("guardar")) {
			DAO empleadoDAO = new DAO();
			Empleados empleado = new Empleados();
			empleado.setDni(request.getParameter("dni"));
			empleado.setNombre(request.getParameter("nombre"));
			empleado.setSexo(request.getParameter("sexo").charAt(0));
			empleado.setCategoria(Integer.parseInt(request.getParameter("categoria")));
			empleado.setAnios(Integer.parseInt(request.getParameter("anios")));
			try {
				empleadoDAO.guardar(empleado);
				System.out.println("Registro guardado satisfactoriamente...");
				RequestDispatcher requestDispatcher = request.getRequestDispatcher("/index.jsp");
				requestDispatcher.forward(request, response);

			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else if (opcion.equals("editar2")) {
			Empleados empleado = new Empleados();
			DAO empleadoDAO = new DAO();

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