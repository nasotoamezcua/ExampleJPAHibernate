<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
  <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
  <title>Lista de Usuarios: Spring MVC Lista de Usuarios</title>

</head>
<body>
<%--   <c:url value="/captcha/controller/example/validate" var="uriCaptcha"></c:url> --%>
   	<h1>Lista de Usuarios: <br /> Spring MVC Lista de Usuarios</h1>
    <fieldset>
      <legend>Lista de Usuarios</legend>
	     <table border="1">
			<tr>
   				<th colspan="5">Lista de Usuarios</th>
   			</tr>
	      <c:choose>
	      	<c:when test="${not empty usuarios}">
	      		<c:forEach items="${usuarios}" var="us" varStatus="u">
			      	<tr>
						<th>#</th>
						<th>id</th>
						<th>nombre</th>
						<th>password</th>	
						<th>UserName</th>
					</tr>
	      			<tr>
	      				<td>${u.count}</td>
	      				<td>${us.id}</td>
	      				<td>${us.nombre}</td>
	      				<td>${us.username}</td>
	      				<td>${us.password}</td>
	      			</tr>
	      			<tr>
	      				<th colspan="5">Direccion</th>
	      			</tr>
	      			<tr>
	      				<th colspan="3">Calle</th>
	      				<th colspan="2">CP</th>
	      			</tr>
	      			<c:choose>
	      				<c:when test="${not empty us.direccion}">
	      					<tr>
			      				<td colspan="3">${us.direccion.calle}</td>
			      				<td colspan="2">${us.direccion.codigoPostal}</td>
			      			</tr>
	      				</c:when>
	      				<c:otherwise>
	      					<tr>
								<td colspan="5">No existe Direccion</td>
				      		</tr>
	      				</c:otherwise>
	      			</c:choose>
	      				<tr>
		      				<th colspan="5">Permisos</th>
		      			</tr>
		      			<tr>
		      				<th colspan="3">Nombre</th>
		      				<th colspan="2">Estatus</th>
		      			</tr>
	      			<c:choose>
	      				<c:when test="${not empty us.permisos}">
	      					
			      			<c:forEach items="${us.permisos}" var="per" varStatus="p">
			      				<tr>
				      				<td colspan="3">${per.nombre}</td>
				      				<td colspan="2">${per.estatus}</td>
				      			</tr>
			      			</c:forEach>
	      				</c:when>
	      				<c:otherwise>
				      		<tr>
								<td colspan="5">No existen Permisos</td>
				      		</tr>
				      	</c:otherwise>
	      			</c:choose>
	      		</c:forEach>
	      	</c:when>
	      	<c:otherwise>
	      		<tr>
					<td colspan="5">No existen usuarios</td>
	      		</tr>
	      	</c:otherwise>
	      </c:choose>
	     </table>
    </fieldset>
</body>
</html>