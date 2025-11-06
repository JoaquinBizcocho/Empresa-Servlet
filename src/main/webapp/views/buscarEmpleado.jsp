<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Buscar empleado</title>
</head>
<body>
    <h1>Buscar empleado por DNI</h1>

    <form action="app" method="get">
        <input type="hidden" name="opcion" value="buscarPorDni">
        <label for="dni">DNI:</label>
        <input type="text" id="dni" name="dni" required>
        <input type="submit" value="Buscar">
    </form>

    <br/>

    
        <h2>Resultado de la b�squeda</h2>
        <table border="1">
            <tr>
                <td>Dni</td>
                <td>Sueldo Base</td>
            </tr>
            <tr>
                <td><c:out value="${empleado.dni}" /></td>
                <td><c:out value="${sueldoBase}" /></td>
            </tr>
        </table>
    

    <br/>
    <button type="button" onclick="window.location.href='index.jsp'">Volver</button>
</body>
</html>
