<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Editar empleado</title>
</head>
<body>
    <h1>Editar empleado</h1>

    
    <form action="app" method="get">
        <input type="hidden" name="opcion" value="editarPorDni">
        <label for="dni">DNI:</label>
        <input type="text" id="dni" name="dni" required>
        <input type="submit" value="Buscar">
    </form>

    <br/>

    <c:if test="${not empty empleado}">
        <h2>Editar datos del empleado</h2>
        <form action="app" method="post">
            <input type="hidden" name="opcion" value="actualizarEmpleado">
            
            <table border="1">
                <tr>
                    <td>DNI</td>
                    <td><input type="text" name="dni" value="${empleado.dni}" readonly></td>
                </tr>
                <tr>
                    <td>Nombre</td>
                    <td><input type="text" name="nombre" value="${empleado.nombre}" required></td>
                </tr>
                <tr>
				<td>Sexo:</td>
				<td><select type="text" name="sexo">
						<option value="M">M</option>
						<option value="F">F</option>
				</select></td>
				</tr>
                <tr>
                    <td>Categoria</td>
                    <td><input type="number" name="categoria" value="${empleado.categoria}" required></td>
                </tr>
                <tr>
                    <td>Anios trabajados</td>
                    <td><input type="number" name="anios" value="${empleado.anios}" required></td>
                </tr>
            </table>
            <c:if test="${not empty mensaje}">
    			<p font-weight: bold;">${mensaje}</p>
			</c:if>
            <br>
            <input type="submit" value="Guardar cambios">
        </form>
    </c:if>

    <br/>
    <button type="button" onclick="window.location.href='index.jsp'">Volver</button>
</body>
</html>
