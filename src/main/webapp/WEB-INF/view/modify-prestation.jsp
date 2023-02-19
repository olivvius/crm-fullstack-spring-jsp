<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri = "http://www.springframework.org/tags/form" prefix = "form"%>
<!DOCTYPE html>
<html xlmns:th="http://www.thymeleaf.org">
    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <title>Modifier une prestation</title>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-gH2yIJqKdNHPEq0n4Mqa/HGKIhSkIHeL5AyhkYV8i59U5AR6csBvApHHNl/vI1Bx" crossorigin="anonymous">
    </head>
    <body>
        <%@ include file="navbar.jsp" %>
        <br />
        <h1> Modifier une prestation</h1>
        <br />
        <form:form method = "POST" action = "/modify-prestation" modelAttribute="modify">


            <div class="conteiner h100">
                <div class="row h100 justify-content-center align-items-center">
                    <div class="col-10 col-md-8 col-lg-6">
                        <form:hidden path="id" />
                        <div class="form-group div-to-align">
                            <form:label path = "description">description</form:label>
                            <form:textarea class="form-control" path = "description" />
                            <form:errors path="description" />
                        </div>
                        <br>
                        <div class="form-group div-to-align">
                            <form:label path ="duree">durée</form:label>
                            <form:input class="form-control" type="number" path = "duree" />
                            <form:errors path="duree" />
                        </div>
                        <br>
                        <div class="form-group div-to-align">
                            <form:label path ="prix">prix journalier</form:label>
                            <form:input class="form-control" type="number" step="0.01" path = "prix" />
                            <form:errors path="prix" />
                        </div>
                        <br>
                        <div class="form-group div-to-align">
                            <td>Etat
                                <form:select class="form-control" path="etat">
                                    <form:option value="CANCELLED" label="CANCELLED" />
                                    <form:option value="CONFIRMED"  label="CONFIRMED" />
                                    <form:option value="PAID" label="PAID" />
                                    <form:option value="OPTION"  label="OPTION"/>
                                </form:select>
                            </td>
                        </div>
                        <br>
                        <div class="form-group div-to-align">
                            Client
                            <form:select path="client" class="form-control" >
                                <form:options itemValue="id" items="${listClients}" />
                            </form:select>
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
