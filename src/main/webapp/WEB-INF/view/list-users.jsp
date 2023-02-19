<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri = "http://www.springframework.org/tags/form" prefix = "form"%>
<!DOCTYPE html>
<html xlmns:th="http://www.thymeleaf.org">
    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <title>Liste des users</title>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-gH2yIJqKdNHPEq0n4Mqa/HGKIhSkIHeL5AyhkYV8i59U5AR6csBvApHHNl/vI1Bx" crossorigin="anonymous">
    </head>
    <body>
        <%@ include file="navbar.jsp" %>
        <h1> Liste des users</h1>

        <a href="http://localhost:8081/add-user" class="link" >Ajouter un user</a>

        <table class="table table-responsive-xl  text-center" >
            <thead class="thead-dark">
            <th >nom</th>
            <th >prenom</th>
            <th >mail</th>
            <th >Roles</th>
            <th ></th>
        </thead>
        <c:forEach var="p" items="${listUsers}">
            <tbody >
            <td ><c:out value="${p.getNom()}"/></td>
            <td ><c:out value="${p.getPrenom()}"/></td>
            <td ><c:out value="${p.getEmail()}"/></td>
            <td ><c:out value="${p.getRoles()}"/></td>
            <td >
                <form:form action="/delete-user" method="post" modelAttribute="delete-user">
                    <form:hidden path="id" value="${p.getId()}" />
                    <form:button class="btn btn-danger">delete</form:button >
                </form:form>
            </td>
        </tbody>
    </c:forEach>
</table>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0/dist/js/bootstrap.bundle.min.js" integrity="sha384-A3rJD856KowSb7dwlZdYEkO39Gagi7vIsF0jrRAoQmDKKtQBHUuLZ9AsSv4jD4Xa" crossorigin="anonymous"></script>
</body>
</html>
