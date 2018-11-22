<!DOCTYPE HTML>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<%@ page contentType="text/html; charset=UTF-8" %>

<html lang="en">
    <head>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta http-equiv="Pragma" content="no-cache">
        <meta http-equiv="Cache-Control" content="no-cache">
        <meta http-equiv="Expires" content="Sat, 01 Dec 2001, 00:00:00 GMT">

        <title>Stock Exchange</title>

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
        <script type="text/javascript">
            function stopRKey(evt) {
                var evt = (evt) ? evt : ((event) ? event : null);
                var node = (evt.target) ? evt.target : ((evt.srcElement) ? evt.srcElement : null);
                if ((evt.keyCode == 13) && (node.type == "text")) {
                    return false;
                }
            }
            document.onkeypress = stopRKey;
        </script>
    </head>
    <body>

        <div class="navbar navbar-inverse">
            <a href="/" class="navbar-brand">Stocks</a>
            <div class="navbar-collapse collapse">
                <ul class="nav navbar-nav">

                    <li class="dropdown">
                        <a class="dropdown-toggle" data-toggle="dropdown" href="#">User panel <span class="caret"></span></a>
                        <ul class="dropdown-menu">
                            <li><a href="user-money">Edit wallet</a></li>
                            <li><a href="user-account">Edit account</a></li>
                        </ul></li>
                </ul>

                <ul class="nav navbar-nav navbar-right">
                    <c:if test="${pageContext.request.userPrincipal.name != null}">
                        <form id="logoutForm" method="POST" action="${contextPath}/logout">
                            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                        </form>
                    </c:if>

                    <li class="navbar-text">Logged in as ${user.name} ${user.surname}</li>
                    <li><a href="user-account"><span class="glyphicon glyphicon-cog" style="font-size:20px;"></span></a></li>
                    <li><a onclick="document.forms['logoutForm'].submit()" href="#"><span class="glyphicon glyphicon-off" style="font-size:20px;"></span></a></li>
                </ul>
            </div>
        </div>

        <c:choose>
            <c:when test="${mode == 'MODE_HOME'}">
                <div class="container" id="homeDiv">
                    <div class="row container-fluid">
                        <div class="col-md-6" id="stocksDiv">
                            <h3>Stock prices</h3>
                            <hr>
                            <div class="table-responsive">
                                <table class="table table-striped table-bordered text-left">
                                    <thread>
                                        <tr>
                                            <th>Company</th>
                                            <th>Value</th>
                                            <th>Actions</th>
                                        </tr>
                                    </thread>
                                    <tbody>
                                        <c:forEach var="stock" items="${stocks}">
                                            <tr>
                                                <td>${stock.code}</td>
                                                <td>${stock.price}</td>
                                                <td><a href="buy-stock?id=${stock.id}"><button type="button" class="btn btn-primary center-block">BUY</button></a></td>
                                            </tr>
                                        </c:forEach>
                                    </tbody>
                                </table>
                                <hr>
                                Last data update: <strong>${date}, ${time} (summer-time)</strong>      
                            </div>
                        </div>
                        <div class="col-md-6">
                            <h3>My wallet</h3>
                            <hr>
                            <div class="table-responsive">
                                <table class="table table-striped table-bordered text-left">
                                    <thread>
                                        <tr>
                                            <th>Company</th>
                                            <th>Unit price</th>
                                            <th>Amount</th>
                                            <th>Value</th>
                                            <th>Actions</th>
                                        </tr>
                                    </thread>
                                    <tbody>
                                        <c:forEach var="wallet" items="${wallet}">
                                            <tr>
                                                <td>${wallet.stock.code}</td>
                                                <td>${wallet.stock.price}</td>
                                                <td>${wallet.amount}</td>
                                                <td>${wallet.value}</td>
                                                <td><a href="sell-stock?id=${wallet.stock.id}"><button type="button" class="btn btn-primary center-block">SELL</button></a></td>
                                            </tr>
                                        </c:forEach>
                                    </tbody>
                                </table>
                                <hr>
                                Avaliable money: <strong>${user.money}</strong> PLN                                             
                            </div>
                        </div>
                    </div>

                    <br>
                    <br>
                </c:when>
                <c:when test="${mode == 'MODE_BUY'}">
                    <div class="container text-center">
                        <h3>${stock.name} (<strong>${stock.code}</strong>)</h3>
                        <h4>Current price: <strong>${stock.price} PLN</strong></h4>
                        <h4>Possible amount to buy: <strong>${stock.amount}</strong></h4>
                        <hr>
                        <form class="form-horizontal" method="POST" action="buy-stock">
                            <input type="hidden" name="id" value="${stock.id}"/>
                            <div class="form-group">
                                <label class="control-label col-md-3 col-md-offset-2">Amount:</label>
                                <div class="col-md-3 ${error != null ? 'has-error' : ''}">
                                    <input type="text" class="form-control" name="amount" value="${amount}"/>
                                    <span style="color: red"><strong>${error}</strong></span>
                                </div>
                            </div>
                            <button type="button" class="btn btn-primary" data-toggle="modal" data-target="#buyModalCenter" data-whatever="@amount">BUY</button>
                            <div class="modal fade" id="buyModalCenter" tabindex="-1" role="dialog" aria-labelledby="buyModalCenterTitle" aria-hidden="true">
                                <div class="modal-dialog modal-dialog-centered" role="document">
                                    <div class="modal-content">
                                        <div class="modal-header">
                                            <h5 class="modal-title" id="exampleModalLongTitle"><strong>CONFIRM YOUR DECISION</strong></h5>
                                            <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                                <span aria-hidden="true">&times;</span>
                                            </button>
                                        </div>
                                        <div class="modal-body">
                                            You are buying ${wallet.amount} actions of ${stock.name} (<strong>${stock.code}</strong>)
                                            <br>
                                            Current price: <strong>${stock.price} PLN</strong>
                                        </div>
                                        <div class="modal-footer">
                                            <button type="button" class="btn btn-secondary" data-dismiss="modal">CLOSE</button>
                                            <input type="submit" class="btn btn-primary" value="CONFIRM AND BUY"/>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </form>
                    </div>
                </c:when>
                <c:when test="${mode == 'MODE_SELL'}">
                    <div class="container text-center">
                        <h3>${stock.name} (<strong>${stock.code}</strong>)</h3>
                        <h4>Current price: <strong>${stock.price} PLN</strong></h4>
                        <h4>Possible amount to sell: <strong>${wallet.amount}</strong></h4>
                        <hr>
                        <form class="form-horizontal" method="POST" action="sell-stock">
                            <input type="hidden" name="id" value="${stock.id}"/>
                            <div class="form-group">
                                <label class="control-label col-md-3 col-md-offset-2">Amount:</label>
                                <div class="col-md-3 ${error != null ? 'has-error' : ''}">
                                    <input type="text" class="form-control" name="amount" value="${amount}"/>
                                    <span style="color: red"><strong>${error}</strong></span>
                                </div>
                            </div>
                            <button type="button" class="btn btn-primary" data-toggle="modal" data-target="#sellModalCenter">SELL</button>
                            <div class="modal fade" id="sellModalCenter" tabindex="-1" role="dialog" aria-labelledby="sellModalCenterTitle" aria-hidden="true">
                                <div class="modal-dialog modal-dialog-centered" role="document">
                                    <div class="modal-content">
                                        <div class="modal-header">
                                            <h5 class="modal-title" id="exampleModalLongTitle"><strong>CONFIRM YOUR DECISION</strong></h5>
                                            <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                                <span aria-hidden="true">&times;</span>
                                            </button>
                                        </div>
                                        <div class="modal-body">
                                            You are selling ${wallet.amount} actions of ${stock.name} (<strong>${stock.code}</strong>)
                                            <br>
                                            Current price: <strong>${stock.price} PLN</strong>
                                        </div>
                                        <div class="modal-footer">
                                            <button type="button" class="btn btn-secondary" data-dismiss="modal">CLOSE</button>
                                            <input type="submit" class="btn btn-primary" value="CONFIRM AND SELL"/>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </form>
                    </div>
                </c:when>
                <c:when test="${mode == 'MODE_USER_MONEY'}">
                    <div class="container text-center" id="walletDiv">
                        <h3>Your wallet: <strong>${user.money} PLN</strong></h3>
                        <hr>
                        <form class="form-horizontal" method="POST" action="user-money">
                            <div class="form-group">
                                <label class="control-label col-md-3 col-md-offset-3">Change value of your wallet (PLN):</label>
                                <div class="col-md-3 ${error != null ? 'has-error' : ''}">
                                    <input type="text" class="form-control" name="money" value="${money}" />
                                    <span style="color: red"><strong>${error}</strong></span>
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
                    Copyright: Project 2018 (Kamil Zemczak).
                </div>
            </div>

            <script src="static/js/jquery-1.11.1.min.js"></script>
            <script src="static/js/bootstrap.min.js"></script>

    </body>
</html>
