package com.empresa.empladoDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.empresa.conexion.Conexion;
import com.empresa.empleados.Empleados;

public class DAO {
	private Connection connection;	// Conexión con la base de datos
	private PreparedStatement statement;	// Objeto para ejecutar sentencias SQL
	private boolean estadoOperacion;	// Variable para comprobar si la operación fue exitosa

	// Método para guardar un nuevo empleado en la base de datos
	public boolean guardar(Empleados empleado) throws SQLException {
		String sql = null;	
		estadoOperacion = false;
		connection = obtenerConexion();	// Se obtiene la conexión desde la clase Conexion

		try {
			connection.setAutoCommit(false);	// Se desactiva el autocommit para controlar transacciones
			sql = "INSERT INTO empleados (dni, nombre, sexo, categoria, anios) VALUES(?,?,?,?,?)";
			statement = connection.prepareStatement(sql);

			// Se asignan los valores del objeto empleado a los parámetros del SQL
			statement.setString(1, empleado.getDni());
			statement.setString(2, empleado.getNombre());
			statement.setString(3, String.valueOf(empleado.getSexo()));
			statement.setInt(4, empleado.getCategoria());
			statement.setInt(5, empleado.getAnios());

			// Se ejecuta la sentencia y se verifica si se insertó al menos una fila
			estadoOperacion = statement.executeUpdate() > 0;

			connection.commit();	// Se confirma la transacción
			statement.close();
			connection.close();
		} catch (SQLException e) {
			connection.rollback();	// En caso de error, se deshacen los cambios
			e.printStackTrace();
		}

		// Se devuelve si la operación fue exitosa o no
		return estadoOperacion;
	}

	// Método para editar los datos de un empleado existente
	public boolean editar(Empleados empleado) throws SQLException {
	    String sql = null;
	    estadoOperacion = false;
	    connection = obtenerConexion();

	    try {
	        connection.setAutoCommit(false);
	        sql = "UPDATE empleados SET nombre=?, sexo=?, categoria=?, anios=? WHERE dni=?";
	        statement = connection.prepareStatement(sql);

	     // Se establecen los valores actualizados del empleado
	        statement.setString(1, empleado.getNombre());
	        statement.setString(2, String.valueOf(empleado.getSexo()));
	        statement.setInt(3, empleado.getCategoria());
	        statement.setInt(4, empleado.getAnios());
	        statement.setString(5, empleado.getDni());

	     // Se ejecuta la actualización
	        estadoOperacion = statement.executeUpdate() > 0;
	        connection.commit();	// Se confirma la transacción
	        statement.close();
	        connection.close();
	    	
	      
	       } catch (SQLException e) {
	        connection.rollback();	// Si hay error, se revierte
	        e.printStackTrace();
	       }

	    return estadoOperacion;
	}
	
	// Método para buscar un empleado por su DNI
	public Empleados buscarPorDni(String dni) {
	    Empleados emp = null;
	    String sql = "SELECT * FROM empleados WHERE dni = ?";
	    try {
	        Connection con = Conexion.getConnection();
	        PreparedStatement ps = con.prepareStatement(sql);
	        ps.setString(1, dni);
	        ResultSet rs = ps.executeQuery();
	        // Si se encuentra un resultado, se crea el objeto empleado
	        if (rs.next()) {
	            emp = new Empleados();
	            emp.setDni(rs.getString("dni"));
	            emp.setNombre(rs.getString("nombre"));
	            emp.setSexo(rs.getString(3).charAt(0));
	            emp.setCategoria(rs.getInt("categoria"));
	            emp.setAnios(rs.getInt("anios"));
	        }
	     // Se cierran los recursos
	        rs.close();
	        ps.close();
	        con.close();
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	 // Retorna el empleado encontrado o null si no existe
	    return emp;
	}
	
	// Método para actualizar los datos de un empleado
	public boolean actualizarEmpleado(Empleados emp) {
	    boolean actualizado = false;
	    String sql = "UPDATE empleados SET nombre=?, sexo=?, categoria=?, anios=? WHERE dni=?";
	    try {
	        Connection con = Conexion.getConnection();
	        PreparedStatement ps = con.prepareStatement(sql);
	        // Se pasan los nuevos datos del empleado
	        ps.setString(1, emp.getNombre());
	        ps.setString(2, String.valueOf(emp.getSexo()));
	        ps.setInt(3, emp.getCategoria());
	        ps.setInt(4, emp.getAnios());
	        ps.setString(5, emp.getDni());
	        
	        // Se ejecuta la actualización y se verifica el resultado
	        int filas = ps.executeUpdate();
	        if (filas > 0) {
	            actualizado = true;
	        }

	        ps.close();
	        con.close();
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	    return actualizado;
	}




	// Método que devuelve una lista con todos los empleados
	public List<Empleados> obtenerEmpleados() throws SQLException {
		ResultSet resultSet = null;
		List<Empleados> listaEmpleados = new ArrayList<>();

		String sql = null;
		estadoOperacion = false;
		connection = obtenerConexion();

		try {
			sql = "SELECT * FROM empleados";
			statement = connection.prepareStatement(sql);
			resultSet = statement.executeQuery(sql);
			// Se recorre el resultado y se crean objetos Empleados
			while (resultSet.next()) {
				Empleados e = new Empleados();
				e.setDni(resultSet.getString(1));
				e.setNombre(resultSet.getString(2));
				e.setSexo(resultSet.getString(3).charAt(0));
				e.setCategoria(resultSet.getInt(4));
				e.setAnios(resultSet.getInt(5));
				listaEmpleados.add(e);	// Se añade cada empleado a la lista
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		// Retorna la lista completa de empleados
		return listaEmpleados;
	}
		
	// Método para obtener el sueldo de un empleado mediante su DNI
	public int obtenerSueldoPorDni(String dniEmpleado) throws SQLException {
	       int sueldo = -1;	// Valor por defecto si no se encuentra el empleado 
	       String sql = "SELECT sueldo FROM nominas WHERE dni=?";
	       connection = obtenerConexion();
	       try {
	           statement = connection.prepareStatement(sql);
	           statement.setString(1, dniEmpleado);
	           ResultSet resultSet = statement.executeQuery();
	           
	        // Si existe el registro, se obtiene el sueldo
	           if (resultSet.next()) {
	               sueldo = resultSet.getInt("sueldo");
	           }
	           statement.close();
	           connection.close();
	       } catch (SQLException e) {
	           e.printStackTrace();
	       }
	       return sueldo;	// Devuelve el sueldo o -1 si no se encontró
	   }

	
	// Método para obtener un empleado específico mediante su DNI
	public Empleados obtenerEmpleado(String Dni) throws SQLException {
		ResultSet resultSet = null;
		Empleados e = new Empleados();

		String sql = null;
		estadoOperacion = false;
		connection = obtenerConexion();

		try {
			sql = "SELECT * FROM empleados WHERE dni =?";
			statement = connection.prepareStatement(sql);
			statement.setString(1, Dni);

			resultSet = statement.executeQuery();

			// Si el resultado existe, se rellenan los datos del objeto empleado
			if (resultSet.next()) {
				e.setDni(resultSet.getString(1));
				e.setNombre(resultSet.getString(2));
				e.setSexo(resultSet.getString(3).charAt(0));
				e.setCategoria(resultSet.getInt(4));
				e.setAnios(resultSet.getInt(5));
			}

		} catch (SQLException x) {
			x.printStackTrace();
		}

		return e; // Devuelve el objeto empleado encontrado
	}

	// Método privado que obtiene una conexión del pool
	private Connection obtenerConexion() throws SQLException {
		return Conexion.getConnection();
	}
	
	/*
	 * Es un atajo interno que el DAO usa para no tener que escribir cada vez todo el codigo de conexion
	 * cada vez que una funcon como obtenerEmpleado por ejemplo nececita comunicarse con la base de datos
	 * llama a: connection = obtenerConexion(); y devuelve una conexion lista para usar
	 * 
	 * Un pool de conexiones es un conjunto de conexiones abiertas y reutilizables que tomcat mantiene activas
	 * en lugar de abrir y cerrar conexiones se reciclan
	 * */

}