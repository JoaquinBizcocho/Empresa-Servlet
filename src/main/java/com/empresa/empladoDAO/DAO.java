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
			sql = "UPDATE empleados SET nombre=?, cantidad=?, precio=?, fecha_actualizar=? WHERE id=?";
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

	// eliminar producto
	public boolean eliminar(int idEmpleado) throws SQLException {
		String sql = null;
		estadoOperacion = false;
		connection = obtenerConexion();
		try {
			connection.setAutoCommit(false);
			sql = "DELETE FROM empleados WHERE id=?";
			statement = connection.prepareStatement(sql);
			statement.setInt(1, idEmpleado);

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

	// obtener empleado
	public Empleados obtenerProducto(int Dni) throws SQLException {
		ResultSet resultSet = null;
		Empleados p = new Empleados();

		String sql = null;
		estadoOperacion = false;
		connection = obtenerConexion();

		try {
			sql = "SELECT * FROM productos WHERE id =?";
			statement = connection.prepareStatement(sql);
			statement.setInt(1, Dni);

			resultSet = statement.executeQuery();

			if (resultSet.next()) {
				p.setDni(resultSet.getString(1));
				p.setNombre(resultSet.getString(2));
				p.setSexo(resultSet.getString(3).charAt(0));
				p.setCategoria(resultSet.getInt(4));
				p.setAnios(resultSet.getInt(5));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return p;
	}

	// obtener conexion pool
	private Connection obtenerConexion() throws SQLException {
		return Conexion.getConnection();
	}

}