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
	private Connection connection;
	private PreparedStatement statement;
	private boolean estadoOperacion;

	// guardar empleado
	public boolean guardar(Empleados empleado) throws SQLException {
		String sql = null;
		estadoOperacion = false;
		connection = obtenerConexion();

		try {
			connection.setAutoCommit(false);
			sql = "INSERT INTO empleados (dni, nombre, sexo, categoria, anios) VALUES(?,?,?,?,?)";
			statement = connection.prepareStatement(sql);

			statement.setString(1, empleado.getDni());
			statement.setString(2, empleado.getNombre());
			statement.setString(3, String.valueOf(empleado.getSexo()));
			statement.setInt(4, empleado.getCategoria());
			statement.setInt(5, empleado.getAnios());

			estadoOperacion = statement.executeUpdate() > 0;

			connection.commit();
			statement.close();
			connection.close();
		} catch (SQLException e) {
			connection.rollback();
			e.printStackTrace();
		}

		return estadoOperacion;
	}

	// editar empleado
	public boolean editar(Empleados empleado) throws SQLException {
	    String sql = null;
	    estadoOperacion = false;
	    connection = obtenerConexion();

	    try {
	        connection.setAutoCommit(false);
	        sql = "UPDATE empleados SET nombre=?, sexo=?, categoria=?, anios=? WHERE dni=?";
	        statement = connection.prepareStatement(sql);

	        statement.setString(1, empleado.getNombre());
	        statement.setString(2, String.valueOf(empleado.getSexo()));
	        statement.setInt(3, empleado.getCategoria());
	        statement.setInt(4, empleado.getAnios());
	        statement.setString(5, empleado.getDni());

	        estadoOperacion = statement.executeUpdate() > 0;
	        connection.commit();
	        statement.close();
	        connection.close();
	    	
	      
	       } catch (SQLException e) {
	        connection.rollback();
	        e.printStackTrace();
	       }

	    return estadoOperacion;
	}
	
	
	public Empleados buscarPorDni(String dni) {
	    Empleados emp = null;
	    String sql = "SELECT * FROM empleados WHERE dni = ?";
	    try {
	        Connection con = Conexion.getConnection();
	        PreparedStatement ps = con.prepareStatement(sql);
	        ps.setString(1, dni);
	        ResultSet rs = ps.executeQuery();
	        if (rs.next()) {
	            emp = new Empleados();
	            emp.setDni(rs.getString("dni"));
	            emp.setNombre(rs.getString("nombre"));
	            emp.setSexo(rs.getString(3).charAt(0));
	            emp.setCategoria(rs.getInt("categoria"));
	            emp.setAnios(rs.getInt("anios"));
	        }
	        rs.close();
	        ps.close();
	        con.close();
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	    return emp;
	}
	
	public boolean actualizarEmpleado(Empleados emp) {
	    boolean actualizado = false;
	    String sql = "UPDATE empleados SET nombre=?, sexo=?, categoria=?, anios=? WHERE dni=?";
	    try {
	        Connection con = Conexion.getConnection();
	        PreparedStatement ps = con.prepareStatement(sql);
	        ps.setString(1, emp.getNombre());
	        ps.setString(2, String.valueOf(emp.getSexo()));
	        ps.setInt(3, emp.getCategoria());
	        ps.setInt(4, emp.getAnios());
	        ps.setString(5, emp.getDni());
	        
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




	// obtener lista de Empleados
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
			while (resultSet.next()) {
				Empleados e = new Empleados();
				e.setDni(resultSet.getString(1));
				e.setNombre(resultSet.getString(2));
				e.setSexo(resultSet.getString(3).charAt(0));
				e.setCategoria(resultSet.getInt(4));
				e.setAnios(resultSet.getInt(5));
				listaEmpleados.add(e);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return listaEmpleados;
	}
		
	
	public int obtenerSueldoPorDni(String dniEmpleado) throws SQLException {
	       int sueldo = -1;
	       String sql = "SELECT sueldo FROM nominas WHERE dni=?";
	       connection = obtenerConexion();
	       try {
	           statement = connection.prepareStatement(sql);
	           statement.setString(1, dniEmpleado);
	           ResultSet resultSet = statement.executeQuery();
	           if (resultSet.next()) {
	               sueldo = resultSet.getInt("sueldo");
	           }
	           statement.close();
	           connection.close();
	       } catch (SQLException e) {
	           e.printStackTrace();
	       }
	       return sueldo;
	   }

	
	// obtener empleado
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

		return e;
	}

	// obtener conexion pool
	private Connection obtenerConexion() throws SQLException {
		return Conexion.getConnection();
	}

}