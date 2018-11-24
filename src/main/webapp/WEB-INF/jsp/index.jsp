<!DOCTYPE HTML>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<%@ page contentType="text/html; charset=UTF-8" %>

<html lang="en">
    <head>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta http-equiv="Pragma" content="no-cache">
        <meta http-equiv="Cache-Control" content="no-cache">
        <meta http-equiv="Expires" content="Sat, 01 Dec 2001, 00:00:00 GMT">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Student App</title>

        <link href="static/css/bootstrap.min.css" rel="stylesheet">
        <link href="static/css/style.css" rel="stylesheet">
        <link rel="stylesheet" href=static/css"/datepicker.css>
        <style>
            .jumbotron{
                background-color:#262626;
                color:white;
                padding-top: 5px;
                padding-bottom: 20px;
            }
        </style>
        <script>
            $(function () {
                $("#datepicker").datepicker();
            });
        </script>
    </head>
    <body>

        <div class="navbar navbar-inverse">
            <a href="/" class="navbar-brand">Students</a>
            <div class="navbar-collapse collapse">
                <ul class="nav navbar-nav">
                    <li><a href="add-student">Add student</a></li>
                </ul>
            </div>
        </div>

        <c:choose>
            <c:when test="${mode == 'MODE_HOME'}">
                <div class="container text-center" id="homeDiv">
                    <h3>ALL STUDENTS</h3>
                    <hr>
                    <div class="table-responsive">
                        <table class="table table-striped table-bordered text-left">
                            <thread>
                                <tr>
                                    <th>First name</th>
                                    <th>Last name</th>
                                    <th>Class</th>
                                    <th>Adress</th>
                                    <th>PESEL</th>
                                    <th>Date of Birth</th>
                                    <th>Dyslexia</th>
                                    <th>Grades</th>
                                    <th>Actions</th>
                                </tr>
                            </thread>
                            <tbody>
                                <c:forEach var="student" items="${student}">
                                    <tr>
                                        <td>${student.firstName}</td>
                                        <td>${student.lastName}</td>
                                        <td>${student.className}</td>
                                        <td>${student.streetAdress} ${student.houseNumber}, ${student.zipCode} ${student.city}</td>
                                        <td>${student.pesel}</td>
                                        <td>${student.dateOfBirth}</td>
                                        <td>${student.dyslexia}</td>
                                        <td><a href="add-grade?id=${student.id}"><span class="glyphicon glyphicon-plus"></span></a>
                                            <a href="show-grade?id=${student.id}"><span class="glyphicon glyphicon-search"></span></a></td>
                                        <td><a href="delete-student?id=${student.id}"><span class="glyphicon glyphicon-trash"></span></a></td>                                                                             
                                    </tr>
                                </c:forEach>
                            </tbody>
                        </table>
                    </div>
                </div>
            </c:when>
            <c:when test="${mode == 'ADD_STUDENT'}">
                <div class="container text-center">
                    <h3>ADD STUDENT</h3>
                    <hr>
                    <form:form class="form-horizontal" modelAttribute="student" method="POST" action="add-student">
                        <input type="hidden" name="id" value="${student.id}"/>

                        <spring:bind path="firstName">
                            <div class="form-group ${status.error ? 'has-error' : ''}">
                                <label class="control-label col-md-3">First name:</label>
                                <div class="col-md-7">
                                    <form:input path="firstName" class="form-control" type="text" name="firstName" id="firstName" value="${student.firstName}"/>
                                    <form:errors path="firstName"/>
                                </div>
                            </div>
                        </spring:bind>

                        <spring:bind path="lastName">
                            <div class="form-group ${status.error ? 'has-error' : ''}">
                                <label class="control-label col-md-3">Last name:</label>
                                <div class="col-md-7">
                                    <form:input path="lastName" type="text" class="form-control" name="lastName" id="lastName" value="${student.lastName}"/>
                                    <form:errors path="lastName"/>
                                </div>
                            </div>
                        </spring:bind>

                        <spring:bind path="className">
                            <div class="form-group ${status.error ? 'has-error' : ''}">
                                <label class="control-label col-md-3">Class:</label>
                                <div class="col-md-7">
                                    <form:input path="className" type="text" class="form-control" name="className" id="lastName" value="${student.className}"/>
                                    <form:errors path="className"/>
                                </div>
                            </div>
                        </spring:bind>

                        <spring:bind path="streetAdress">
                            <div class="form-group ${status.error ? 'has-error' : ''}">
                                <label class="control-label col-md-3">Street adress:</label>
                                <div class="col-md-7">
                                    <form:input path="streetAdress" type="text" class="form-control" name="streetAdress" id="streetAdress" value="${student.streetAdress}"/>
                                    <form:errors path="streetAdress"/>
                                </div>
                            </div> 
                        </spring:bind>

                        <spring:bind path="houseNumber">
                            <div class="form-group ${status.error ? 'has-error' : ''}">
                                <label class="control-label col-md-3">House number:</label>
                                <div class="col-md-7">
                                    <form:input path="houseNumber" type="text" class="form-control" name="houseNumber" id="houseNumber" value="${student.houseNumber}"/>
                                    <form:errors path="houseNumber"/>
                                </div>
                            </div>
                        </spring:bind>

                        <spring:bind path="city">
                            <div class="form-group ${status.error ? 'has-error' : ''}">
                                <label class="control-label col-md-3">City:</label>
                                <div class="col-md-7">
                                    <form:input path="city" type="text" class="form-control" name="city" id="city" value="${student.city}"/>
                                    <form:errors path="city"/>
                                </div>
                            </div>
                        </spring:bind>

                        <spring:bind path="zipCode">
                            <div class="form-group ${status.error ? 'has-error' : ''}">
                                <label class="control-label col-md-3">ZIP code:</label>
                                <div class="col-md-7">
                                    <form:input path="zipCode" type="text" class="form-control" name="zipCode" id="zipCode" value="${student.zipCode}"/>
                                    <form:errors path="zipCode"/>
                                </div>
                            </div>
                        </spring:bind>

                        <spring:bind path="pesel">
                            <div class="form-group ${status.error ? 'has-error' : ''}">
                                <label class="control-label col-md-3">PESEL:</label>
                                <div class="col-md-7">
                                    <form:input path="pesel"  type="text" class="form-control" name="pesel" id="pesel" value="${student.pesel}"/>
                                    <form:errors path="pesel"/>
                                </div>
                            </div>
                        </spring:bind>

                        <div class="form-group">
                            <label class="control-label col-md-3">Date of birth:</label>
                            <div class="col-md-7">
                                <input class="datepicker" type="text"  class="form-control" name="dateOfBirth" id="datepicker" value="${student.dateOfBirth}"/>
                            </div>
                        </div>                              
                        <div class="form-group">
                            <label class="control-label col-md-3">Dyslexia:</label>
                            <div class="col-md-7">
                                <input type="radio" class="col-sm-1" name="dyslexia" value="true"/>
                                <div class="col-sm-1">Yes</div>
                                <input type="radio" class="col-sm-1" name="dyslexia" value="false" checked/>
                                <div class="col-sm-1">No</div>
                            </div>
                        </div>
                        <div class="form-group">
                            <input type="submit" class="btn btn-primary" value="Save"/>
                        </div>
                    </form:form>
                </div>
            </c:when>
            <c:when test="${mode == 'ADD_GRADE'}">
                <div class="container text-center">
                    <h3>ADD GRADE</h3>
                    <h4><strong>STUDENT:</strong> ${student.firstName} ${student.lastName}</h4>
                    <hr>
                    <form:form lass="form-horizontal" modelAttribute="grade" method="POST" action="add-grade">
                        <input type="hidden" name="id" value="${grade.id}"/>
                        <input type="hidden" name="student_id" value="${student.id}"/>

                        <spring:bind path="subject">
                            <div class="form-group ${status.error ? 'has-error' : ''}">
                                <label class="control-label col-md-3">Subject:</label>
                                <div class="col-md-7">
                                    <form:input path="subject" type="text" class="form-control" name="subject" id="subject" value="${grade.subject}"/>
                                    <form:errors path="subject"/>
                                </div>
                            </div>
                        </spring:bind>

                        <spring:bind path="value">
                            <div class="form-group ${status.error ? 'has-error' : ''}">
                                <label class="control-label col-md-3">Value of grade:</label>
                                <div class="col-md-7">
                                    <form:input path="value" type="text" class="form-control" name="value" id="value" value="${grade.value}"/>
                                    <form:errors path="value"/>
                                </div>
                            </div>
                        </spring:bind>
                        <div class="form-group">
                            <input type="submit" class="btn btn-primary" value="Save"/>
                        </div>
                    </form:form>
                </div>
            </c:when>
            <c:when test="${mode == 'SHOW_GRADE'}">
                <div class="container text-center" id="homeDiv">
                    <h3>ALL STUDENTS</h3>
                    <h3>You show oceny od ${user.firstName} ${user.lastName} </h3>
                    <hr>
                    <div class="table-responsive">
                        <table class="table table-striped table-bordered text-left">
                            <thread>
                                <tr>
                                    <th>Subject</th>
                                    <th>Grade value</th>
                                    <th>Actions</th>
                                </tr>
                            </thread>
                            <tbody>
                                <c:forEach var="grade" items="${grade}">
                                    <tr>
                                        <td>${grade.subject}</td>
                                        <td>${grade.value}</td>
                                        <td><a href="delete-grade?id=${grade.id}"><span class="glyphicon glyphicon-trash"></span></a></td>  
                                    </tr>
                                </c:forEach>
                            </tbody>
                        </table>
                    </div>
                </div>
            </c:when>
        </c:choose>

        <div class="navbar-fixed-bottom">
            <div class="panel-footer">
                Copyright: Project FIS-SST 2018 (Kamil Zemczak).
            </div>
        </div>

        <script src="static/js/jquery-1.11.1.min.js"></script>
        <script src="static/js/bootstrap.min.js"></script>
        <script src="static/js/bootstrap-datepicker.js"></script>
        <script>$(function () {
                $('.datepicker').datepicker();
            });</script>
    </body>
</html>
