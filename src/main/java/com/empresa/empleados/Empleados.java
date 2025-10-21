package com.empresa.empleados;
 
import java.sql.Date;
 
public class Empleados {
 
 private String dni;
 private String nombre;
 private char sexo;
 private int categoria;
 private int anios;

 
 public Empleados(String dni, String nombre, char sexo, int categoria, int anios) {
  super();
  this.dni = dni;
  this.nombre = nombre;
  this.sexo = sexo;
  this.categoria = categoria;
  this.anios = anios;
 }
 
 public Empleados() {
  // TODO Auto-generated constructor stub
 }
 
 
 
 public String getDni() {
	return dni;
}

 public void setDni(String dni) {
	this.dni = dni;
 }

 public String getNombre() {
	return nombre;
 }

 public void setNombre(String nombre) {
	this.nombre = nombre;
 }

 public char getSexo() {
	return sexo;
 }

 public void setSexo(char sexo) {
	this.sexo = sexo;
 }

 public int getCategoria() {
	return categoria;
 }

 public void setCategoria(int categoria) {
	this.categoria = categoria;
 }

 public int getAnios() {
	return anios;
 }

 public void setAnios(int anios) {
	this.anios = anios;
 }

 @Override
 public String toString() {
  return "Producto [dni=" + dni + ", nombre=" + nombre + ", sexo=" + sexo + ", categoria=" + categoria
    + ", anios=" + anios + "]";
 }
 
}