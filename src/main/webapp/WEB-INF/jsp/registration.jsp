<!DOCTYPE html>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html; charset=UTF-8" %>

<c:set var="contextPath" value="${pageContext.request.contextPath}"/>

<html lang="en">
    <head>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1">

        <meta name="description" content="">
        <meta name="author" content="">

        <title>Registration</title>

        <link href="${contextPath}/static/css/bootstrap.min.css" rel="stylesheet">
        <link href="${contextPath}/static/css/common.css" rel="stylesheet">

        <style>
            .jumbotron{
                background-color:#9dd29c;
                color:white;
                padding: 5px;
            }
        </style>
    </head>
    <body>
        <div class="container mainPanel">
            <center> <img src="static/images/stockExchangeLogo.png" width="200" height="200"> </center>
            <br>
            <div class="row">
                <div class="col-md-4">
                </div>
                <div class="col-md-4">
                    <div class="jumbotron text-center">

                        <h2><strong>REGISTRATION:</strong></h2>
                        <form:form method="POST" modelAttribute="userForm" class="form-signin">

                            <spring:bind path="username">
                                <div class="form-group ${status.error ? 'has-error' : ''}">
                                    <form:input type="text" path="username" class="form-control" placeholder="Username"
                                                autofocus="true"></form:input>
                                    <form:errors path="username"></form:errors>
                                        <small id="usernameHelpInline" class="text-muted">
                                            From 6 to 32 characters.
                                        </small>
                                    </div>
                            </spring:bind>

                            <spring:bind path="name">
                                <div class="form-group ${status.error ? 'has-error' : ''}">
                                    <form:input type="text" path="name" class="form-control" placeholder="Name"
                                                autofocus="true"></form:input>
                                    <form:errors path="name"></form:errors>
                                    </div>
                            </spring:bind>

                            <spring:bind path="surname">
                                <div class="form-group ${status.error ? 'has-error' : ''}">
                                    <form:input type="text" path="surname" class="form-control" placeholder="Surname"
                                                autofocus="true"></form:input>
                                    <form:errors path="surname"></form:errors>
                                    </div>
                            </spring:bind>

                            <spring:bind path="money">
                                <div class="form-group ${status.error ? 'has-error' : ''}">
                                    <form:input type="text" path="money" class="form-control" placeholder="Budget (PLN)"
                                                autofocus="true"></form:input>
                                    <form:errors path="money"></form:errors>
                                        <small id="moneyHelpInline" class="text-muted">
                                            Field not required
                                        </small>
                                    </div>
                            </spring:bind>

                            <spring:bind path="password">
                                <div class="form-group ${status.error ? 'has-error' : ''}">
                                    <form:input type="password" path="password" class="form-control" placeholder="Password"></form:input>
                                        <small id="passwordHelpInline" class="text-muted">
                                            A minimum of 8 characters
                                        </small>
                                    <form:errors path="password"></form:errors>
                                    </div>
                            </spring:bind>

                            <spring:bind path="passwordConfirm">
                                <div class="form-group ${status.error ? 'has-error' : ''}">
                                    <form:input type="password" path="passwordConfirm" class="form-control"
                                                placeholder="Repeat password"></form:input>
                                    <form:errors path="passwordConfirm"></form:errors>
                                    </div>
                            </spring:bind>

                            <button class="btn btn-lg btn-primary btn-block" type="submit">Register</button>
                        </form:form>
                    </div>
                </div>
            </div>
        </div>

        <div class="navbar-fixed-bottom">
            <div class="panel-footer">
                Copyright: Project 2018 (Kamil Zemczak).
            </div>
        </div>

        <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
        <script src="${contextPath}/static/js/bootstrap.min.js"></script>
    </body>
</html>
