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
                                        <td class="text-center"><a href="add-grade?id=${student.id}"><span class="glyphicon glyphicon-plus"></span></a>
                                            <a href="show-grade?id=${student.id}"><span class="glyphicon glyphicon-search"></span></a></td>
                                        <td class="text-center"><a href="delete-student?id=${student.id}"><span class="glyphicon glyphicon-trash"></span></a></td>                                                                             
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
                            <div class="form-group">
                                <label class="col-md-3 control-label">First name:</label>
                                <div class="col-md-7 ${status.error ? 'has-error' : ''}">
                                    <form:input path="firstName" class="form-control" type="text" name="firstName" id="firstName" value="${student.firstName}"/>
                                    <form:errors class="text-danger" path="firstName"/>
                                </div>
                            </div>
                        </spring:bind>

                        <spring:bind path="lastName">
                            <div class="form-group">
                                <label class="control-label col-md-3">Last name:</label>
                                <div class="col-md-7 ${status.error ? 'has-error' : ''}">
                                    <form:input path="lastName" type="text" class="form-control" name="lastName" id="lastName" value="${student.lastName}"/>
                                    <form:errors class="text-danger" path="lastName"/>
                                </div>
                            </div>
                        </spring:bind>

                        <spring:bind path="className">
                            <div class="form-group">
                                <label class="control-label col-md-3">Class:</label>
                                <div class="col-md-7 ${status.error ? 'has-error' : ''}">
                                    <form:input path="className" type="text" class="form-control" name="className" id="lastName" value="${student.className}"/>
                                    <form:errors class="text-danger" path="className"/>
                                </div>
                            </div>
                        </spring:bind>

                        <spring:bind path="streetAdress">
                            <div class="form-group">
                                <label class="control-label col-md-3">Street adress:</label>
                                <div class="col-md-7 ${status.error ? 'has-error' : ''}">
                                    <form:input path="streetAdress" type="text" class="form-control" name="streetAdress" id="streetAdress" value="${student.streetAdress}"/>
                                    <form:errors class="text-danger" path="streetAdress"/>
                                </div>
                            </div> 
                        </spring:bind>

                        <spring:bind path="houseNumber">
                            <div class="form-group">
                                <label class="control-label col-md-3">House number:</label>
                                <div class="col-md-7 ${status.error ? 'has-error' : ''}">
                                    <form:input path="houseNumber" type="text" class="form-control" name="houseNumber" id="houseNumber" value="${student.houseNumber}"/>
                                    <form:errors class="text-danger" path="houseNumber"/>
                                </div>
                            </div>
                        </spring:bind>

                        <spring:bind path="city">
                            <div class="form-group ">
                                <label class="control-label col-md-3">City:</label>
                                <div class="col-md-7 ${status.error ? 'has-error' : ''}">
                                    <form:input path="city" type="text" class="form-control" name="city" id="city" value="${student.city}"/>
                                    <form:errors class="text-danger" path="city"/>
                                </div>
                            </div>
                        </spring:bind>

                        <spring:bind path="zipCode">
                            <div class="form-group">
                                <label class="control-label col-md-3">ZIP code:</label>
                                <div class="col-md-7 ${status.error ? 'has-error' : ''}">
                                    <form:input path="zipCode" type="text" class="form-control" name="zipCode" id="zipCode" value="${student.zipCode}"/>
                                    <form:errors class="text-danger" path="zipCode"/>
                                </div>
                            </div>
                        </spring:bind>

                        <spring:bind path="pesel">
                            <div class="form-group">
                                <label class="control-label col-md-3">PESEL:</label>
                                <div class="col-md-7 ${status.error ? 'has-error' : ''}">
                                    <form:input path="pesel" type="text" class="form-control" name="pesel" id="pesel" value="${student.pesel}"/>
                                    <form:errors class="text-danger" path="pesel"/>
                                </div>
                            </div>
                        </spring:bind>

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
                            <input id="save" type="submit" class="btn btn-primary" value="Save"/>
                        </div>
                    </form:form>
                </div>
            </c:when>
            <c:when test="${mode == 'ADD_GRADE'}">
                <div class="container text-center">
                    <h3>ADD GRADE</h3>
                    <h4><strong>STUDENT:</strong> ${student.firstName} ${student.lastName}</h4>
                    <hr>
                    <div class="panel panel-default">
                        <div class="panel-body">  <h4>Grade must be value from 1 to 6. Only full and halves are allowed.</h4></div>
                    </div>
                    <form:form class="form-inline" modelAttribute="grade" method="POST" action="add-grade">
                        <input type="hidden" name="id" value="${grade.id}"/>
                        <input type="hidden" name="student_id" value="${student.id}"/>
                        <div class="row">
                            <spring:bind path="subject">
                                <div class="form-group col-md-4 col-md-offset-2">
                                    <label class="control-label col-md-3 center-form">Subject:</label>
                                    <div class="col-md-7 ${status.error ? 'has-error' : ''}">
                                        <form:input path="subject" type="text" class="form-control" name="subject" id="subject" value="${grade.subject}"/>
                                        <form:errors class="text-danger" path="subject"/>
                                    </div>
                                </div>
                            </spring:bind>
                            <spring:bind path="value">
                                <div class="form-group col-md-4">
                                    <label class="col-md-3 center-form" for="sell">Value:</label>
                                    <div class="col-md-7 ${status.error ? 'has-error' : ''}">
                                        <form:errors class="text-danger" path="value"/>
                                        <select class="form-control grade-form" id="sel1" name="value" id="value" value="${grade.value}">
                                            <option>1</option>
                                            <option>2</option>
                                            <option>2.5</option>
                                            <option>3.0</option>
                                            <option>3.5</option>
                                            <option>4.0</option>
                                            <option>4.5</option>
                                            <option>5.0</option>
                                            <option>5.5</option>
                                            <option>6.0</option>
                                        </select>
                                    </div>
                                </div>
                            </spring:bind>
                        </div><br>                            
                        <div class="form-group">
                            <input type="submit" class="btn btn-primary" value="Save"/>
                        </div>
                    </form:form>
                </div>
            </c:when>
            <c:when test="${mode == 'SHOW_GRADE'}">
                <div class="container text-center" id="homeDiv">
                    <h3>You are viewing grades from: <strong>${student.firstName} ${student.lastName}</strong></h3>
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
    </body>
</html>
