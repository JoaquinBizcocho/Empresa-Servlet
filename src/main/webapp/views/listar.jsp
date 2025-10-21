<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
 pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Listar empleados</title>
</head>
<body>
 <h1>Listar empleados</h1>
 <table border="1">
  <tr>
   <td>Dni</td>
   <td>Nombre</td>
   <td>Sexo</td>
   <td>Categoria</td>
   <td>Anios</td>
   <td>Accion</td>
  </tr>
  <c:forEach var="empleados" items="${lista}">
  <tr>
    <td>
     <a href="empleados?opcion=editar&dni=<c:out value="${ empleados.dni}"></c:out>">
      <c:out value="${ empleados.dni}"></c:out>
     </a>
    </td>
    <td><c:out value="${ empleados.nombre}"></c:out></td>
    <td><c:out value="${ empleados.sexo}"></c:out></td>
    <td><c:out value="${ empleados.categoria}"></c:out></td>
    <td><c:out value="${ empleados.anios}"></c:out></td>
    <td>
     <a href="empleados?opcion=eliminar&dni=<c:out value="${ empleados.dni}"></c:out>">
      Eliminar 
     </a>
    </td>
  </tr>
  </c:forEach>
 </table>
</body>
</html>