<!DOCTYPE html>
<%@ page contentType="text/html; charset=UTF-8" %>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width = device-width, initial-scale = 1">

        <title>Login</title>
        <link href="${contextPath}/static/css/bootstrap.css" rel="stylesheet">
        <link href="${contextPath}/static/css/bootstrap.min.css" rel="stylesheet">
        <link href="${contextPath}/static/css/common.css" rel="stylesheet">

        <style>
            .jumbotron{
                background-color:#9dd29c;
                color:white;
                padding: 5px;
            }
            /* Adds borders for tabs */
            .tab-content {
                border-left: 1px solid #ddd;
                border-right: 1px solid #ddd;
                border-bottom: 1px solid #ddd;
                padding: 10px;
            }
            .nav-tabs {
                margin-bottom: 0;
            }
        </style>

    </head>
    <body>
        <div class="container mainPanel">
            <center> <img src="static/images/stockExchangeLogo.png" width="200" height="200"> </center>
            <center><h4>Welcome in Stock Exchange.</h4></center>
            <center><h4>Access to the site only after logging in.</h4></center>

            <div class="row">
                <div class="col-md-4"> </div>
                <div class="col-md-4">
                    <div class="jumbotron text-center">
                        <form method="POST" action="${contextPath}/login" class="form-signin">
                            <h2><strong>LOGIN</strong></h2>
                            <div class="form-group ${error != null ? 'has-error' : ''}">
                                <span>${message}</span>
                                <input name="username" type="text" class="form-control" placeholder="Username" autofocus="true"/>
                                <input name="password" type="password" class="form-control" placeholder="Password"/>
                                <span><strong>${error}</strong></span>
                                <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                                <button class="btn btn-lg btn-primary btn-block" type="submit">Sign in</button>
                            </div>
                    </div>
                </div>
            </div>
        </div>

    <center><p><h4>If you don't have an account: <a href="${contextPath}/registration">REGISTER HERE.</a></p></h4></center>

    <div class="navbar-fixed-bottom">
        <div class="panel-footer">
            Copyright: Project 2018 (Kamil Zemczak).
        </div>
    </div>

    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js" integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa" crossorigin="anonymous"></script>
</body>
</html>
