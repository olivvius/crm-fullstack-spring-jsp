<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri = "http://www.springframework.org/tags/form" prefix = "form"%>
<!DOCTYPE html>
<html xlmns:th="http://www.thymeleaf.org">
    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <title>Ajouter un user</title>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-gH2yIJqKdNHPEq0n4Mqa/HGKIhSkIHeL5AyhkYV8i59U5AR6csBvApHHNl/vI1Bx" crossorigin="anonymous">
    </head>

    <body>
        <%@ include file="navbar.jsp" %>
        <br />
        <h1> Ajouter un user</h1>

        <form:form method = "POST" action = "/add-user" modelAttribute="add-user">
            <div class="conteiner h200">
                <div class="row h200 justify-content-center align-items-center">
                    <div class="col-6 col-md-8 col-lg-6">
                        <div class="form-group div-to-align">
                            <td><form:label path = "nom">nom</form:label></td>
                            <td><form:input class="form-control" path = "nom" /></td>
                            <td><form:errors path="nom" /></td>
                        </div>
                        <br>
                        <div class="form-group div-to-align">
                            <td><form:label path = "prenom">prenom</form:label></td>
                            <td><form:input class="form-control" path = "prenom" /></td>
                            <td><form:errors path="prenom" /></td>
                        </div>
                        <br>
                        <div class="form-group div-to-align">
                            <td><form:label path = "email">email</form:label></td>
                            <td><form:input class="form-control" path = "email" /></td>
                            <td><form:errors path="email" /></td>
                        </div>
                        <br>
                        <div class="form-group div-to-align">
                            <td><form:label path = "password">password</form:label></td>
                            <td><form:password class="form-control" path = "password" /></td>
                            <td><form:errors path="password" /></td>
                        </div>
                        <br>
                        <div class="form-group div-to-align">
                            <td colspan = "2">
                                <input type = "submit" value = "Submit" class="btn btn-primary mb-2"/>
                            </td>
                        </div>
                    </div>
                </div>
            </div>
        </form:form>
        <br />
    </body>
</html>
