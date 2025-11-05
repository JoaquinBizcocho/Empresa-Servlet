package com.empresa.empleados;

public class Nomina {

	private String dniN;
	private String nombreEmpleado;
	private Double sueldo;

	public Nomina(String dni, String nombreEmpleado) {
		this.dniN = dni;
		this.nombreEmpleado = nombreEmpleado;
	}
	
	

	public String getDni() {
		return dniN;
	}
	
	public String getNombreEmpleado() {
		return nombreEmpleado;
	}

	public Double getSueldo() {
		return sueldo;
	}

	@Override
	public String toString() {
		return "Producto [dni=" + dniN + ", Nombre Empleado: " + nombreEmpleado + ", sueldo: " + sueldo;
	}

}