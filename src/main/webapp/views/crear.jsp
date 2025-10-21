<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
 pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Crear empleado</title>
</head>
<body>
 <h1>Crear empleado</h1>
 <form action="empleados" method="post">
    <input type="hidden" name="opcion" value="guardar">

    <label>DNI:</label>
    <input type="text" name="dni"><br>

    <label>Nombre:</label>
    <input type="text" name="nombre"><br>

    <label>Sexo:</label>
    <input type="text" name="sexo" maxlength="1"><br> <!-- ⚠️ ESTE CAMPO ES OBLIGATORIO -->

    <label>Categoria:</label>
    <input type="number" name="categoria"><br>

    <label>Anios:</label>
    <input type="number" name="anios"><br>

    <input type="submit" value="Guardar">
</form>
</body>
</html>