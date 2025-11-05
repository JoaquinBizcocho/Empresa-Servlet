<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
 pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Menú de Opciones</title>
<style>

</style>
</head>
<body>
  <h1>Menu de Opciones Empleados</h1>
  <table border="1">
  <tr>
    <td><a href="empleados?opcion=listar"> Mostrar informacion de todos los Empleados</a></td>
  </tr>
  <tr>
    <td><a href="empleados?opcion=buscarPorDni"> Mostrar el salario existente de un empleado</a></td>
  </tr>
  <tr>
    <td><a href="empleados?opcion=editarPorDni"> Modificar los datos de un empleado</a></td>
  </tr>
</table>
</body>
</html>