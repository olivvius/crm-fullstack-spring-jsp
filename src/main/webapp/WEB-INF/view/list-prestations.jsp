<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri = "http://www.springframework.org/tags/form" prefix = "form"%>
<!DOCTYPE html>
<html xlmns:th="http://www.thymeleaf.org">
    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <title>Liste des prestations</title>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-gH2yIJqKdNHPEq0n4Mqa/HGKIhSkIHeL5AyhkYV8i59U5AR6csBvApHHNl/vI1Bx" crossorigin="anonymous">
    </head>
    <body>
        <%@ include file="navbar.jsp" %>

        <h1> Liste des prestations</h1>
        <div></div>
        <table class="table table-responsive-xl  text-center" >
            <thead class="thead-dark">
            <th >description</th>
            <th >durée</th>
            <th >prix</th>
            <th >prix total</th>
            <th >état</th>
            <th >client</th>
            <th ></th>
            <th ></th>
        </thead>

        <c:forEach var="p" items="${listPrestations}">
            <tbody >
            <td ><c:out value="${p.getDescription()}"/></td>
            <td ><c:out value="${p.getDuree()}"/></td>
            <td ><c:out value="${p.getPrix()}"/></td>
            <td ><c:out value="${p.getPrixtotal()}"/></td>
            <td ><c:out value="${p.getEtat()}"/></td>
            <td ><c:out value="${p.getClient().getNom()}"/></td>
            <td >
                <form:form action="/delete-prestation" method="post" modelAttribute="delete">
                    <form:hidden path="id" value="${p.getId()}" />
                    <form:button class="btn btn-danger">delete</form:button>
                </form:form>
            </td>
            <td >
                <form action="/modify-prestation" method="get">
                    <input type="hidden" name="id" value="${p.getId()}">
                    <input type="submit" value="modify" class="btn btn-warning" >
                </form> 
            </td>
        </tbody>
    </c:forEach>
</table>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0/dist/js/bootstrap.bundle.min.js" integrity="sha384-A3rJD856KowSb7dwlZdYEkO39Gagi7vIsF0jrRAoQmDKKtQBHUuLZ9AsSv4jD4Xa" crossorigin="anonymous"></script>
</body>
</html>
