<!DOCTYPE HTML>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<%@ page contentType="text/html; charset=UTF-8" %>

<html lang="en">
    <head>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta http-equiv="Pragma" content="no-cache">
        <meta http-equiv="Cache-Control" content="no-cache">
        <meta http-equiv="Expires" content="Sat, 01 Dec 2001, 00:00:00 GMT">

        <title>Student App</title>

        <link href="static/css/bootstrap.min.css" rel="stylesheet">
        <link href="static/css/style.css" rel="stylesheet">
        <style>
            .jumbotron{
                background-color:#262626;
                color:white;
                padding-top: 5px;
                padding-bottom: 20px;
            }
        </style>
    </head>
    <body>

        <div class="navbar navbar-inverse">
            <a href="/" class="navbar-brand">Students</a>
            <div class="navbar-collapse collapse">
                <ul class="nav navbar-nav">
                    <li><a href="add-student">Add student</a></li>
                </ul>

                <ul class="nav navbar-nav navbar-right">
                    <li><a href="studenmts"><span class="glyphicon glyphicon-plus" style="font-size:20px;"></span></a></li>
                </ul>
            </div>
        </div>

        <c:choose>
            <c:when test="${mode == 'MODE_HOME'}">
                <div class="container" id="homeDiv">
                    <div class="row container-fluid">
                        <div class="col-md-6" id="studentsDiv">
                            <h3>Students</h3>
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
                                            <th>Actions</th>
                                        </tr>
                                    </thread>
                                    <tbody>
                                        <c:forEach var="student" items="${students}">
                                            <tr>
                                                <td>${student.firstName}</td>
                                                <td>${student.lastName}</td>
                                                <td>${student.className}</td>
                                                <td>${student.streetAdress} ${student.houseNumber}, ${student.zipCode} ${student.city}</td>
                                                <td>${student.pesel}</td>
                                                <td>${student.dateOfBirth}</td>
                                                <td>${student.dyslexia}</td>
                                                <td><a href="buy-stock?id=${stock.id}"><button type="button" class="btn btn-primary center-block">SHOW GRADES</button></a></td>
                                            </tr>
                                        </c:forEach>
                                    </tbody>
                                </table>
                            </div>
                        </div>
                    </div>

                    <br>
                    <br>
                </c:when>
                <c:when test="${mode == 'ADD_STUDENT'}">
                    <div class="container text-center">
                        <h3>Add student</h3>
                        <hr>
                        <form class="form-horizontal" method="POST" action="add-student">

                            <input type="hidden" name="id" value="${student.id}"/>
                            <div class="form-group">
                                <label class="control-label col-md-3">First name:</label>
                                <div class="col-md-7">
                                    <input type="text" class="form-control" name="firstName" value="${student.firstName}"/>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="control-label col-md-3">Last name:</label>
                                <div class="col-md-7">
                                    <input type="text" class="form-control" name="lastName" value="${student.lastName}"/>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="control-label col-md-3">Class:</label>
                                <div class="col-md-7">
                                    <input type="text" class="form-control" name="studentClass" value="${student.studentClass}"/>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="control-label col-md-3">Street adress:</label>
                                <div class="col-md-7">
                                    <input type="text" class="form-control" name="stretAdress" value="${student.streetAdress}"/>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="control-label col-md-3">House number:</label>
                                <div class="col-md-7">
                                    <input type="number" class="form-control" name="houseNumber" max="25" min="0"  value="${student.houseNumber}"/>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="control-label col-md-3">City</label>
                                <div class="col-md-7">
                                    <input type="text" class="form-control" name="city" value="${student.city}"/>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="control-label col-md-3">Zip code:</label>
                                <div class="col-md-7">
                                    <input type="text" class="form-control" name="zipCode" value="${student.zipCode}"/>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="control-label col-md-3">PESEL:</label>
                                <div class="col-md-7">
                                    <input type="text" class="form-control" name="pesel" value="${student.pesel}"/>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="control-label col-md-3">Date of birth:</label>
                                <div class="col-md-7">
                                    <input type="text" class="form-control" name="dateOfBirth" value="${student.dateofBrith}"/>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="control-label col-md-3">Dyslexia:</label>
                                <div class="col-md-7">
                                    <input type="radio" class="col-sm-1" name="finished" value="tak"/>
                                    <div class="col-sm-1">Tak</div>
                                    <input type="radio" class="col-sm-1" name="finished" value="nie" checked/>
                                    <div class="col-sm-1">Nie</div>
                                </div>
                            </div>
                            <div class="form-group">
                                <input type="submit" class="btn btn-primary" value="Save"/>
                            </div>
                        </form>
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
