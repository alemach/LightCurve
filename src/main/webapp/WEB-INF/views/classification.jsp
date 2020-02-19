<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: alemach
  Date: 06.02.2020
  Time: 12:49
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html lang="en">
<head>
    <meta http-equiv="X-UA-Compatible" content="IE=edge"/>
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no"/>
    <meta name="description" content="Time series analysis with FFNN"/>
    <meta name="Aleksander Machaj" content="Time series analysis with FFNN"/>
    <title>Neural network training</title>
    <link href="/resources/css/styles.css" rel="stylesheet"/>
    <link href="https://cdn.datatables.net/1.10.20/css/dataTables.bootstrap4.min.css" rel="stylesheet" crossorigin="anonymous"/>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.11.2/js/all.min.js" crossorigin="anonymous"></script>
</head>
<body class="sb-nav-fixed" style="background: url('/resources/assets/img/stars-1257860.jpg'); background-size: cover ">
<nav class="sb-topnav navbar navbar-expand navbar-dark bg-dark">
    <a class="navbar-brand" href="index.html">Light Curve</a>
    <button class="btn btn-link btn-sm order-1 order-lg-0" id="sidebarToggle" href="#"><i class="fas fa-bars"></i></button>
</nav>
<div id="layoutSidenav">
    <div id="layoutSidenav_nav">
        <nav class="sb-sidenav accordion sb-sidenav-dark" id="sidenavAccordion">
            <div class="sb-sidenav-menu">
                <div class="nav">
                    <div class="sb-sidenav-menu-heading"></div>
                    <a class="nav-link" href="<c:url value="/"/>">
                        <div class="sb-nav-link-icon"></div>
                        Home</a>
                    <a class="nav-link" href="<c:url value="/training"/>">
                        <div class="sb-nav-link-icon"></div>
                        Training</a>
                    <a class="nav-link" href="<c:url value="/classification"/>">
                        <div class="sb-nav-link-icon"></div>
                        Classify</a>

                </div>
            </div>
        </nav>
    </div>
    <div id="layoutSidenav_content">
        <main>
            <div class="container-fluid">
                <h1 class="mt-4" style="font-size: 0/*; padding: 30px*/">Dashboard</h1>
                <c:if test="${evaluation != null}">
                    <ol class="breadcrumb mb-4">

                        <li class="breadcrumb-item active">

                            Test object <c:out value="${testCurveDTO.keplerId}"/> classified
                            <c:if test="${evaluation[0] < 0.5}">NOT</c:if>
                            to be a transit with confidence level
                            <c:if test="${evaluation[0] < 0.5}"><c:out value="${(1-evaluation[0])*100}"/>%</c:if>
                            <c:if test="${evaluation[0] >= 0.5}"><c:out value="${evaluation[0]*100}"/>%</c:if>
                            <img style="max-width:100%" src="/resources/LightCurveChart.jpeg">
                        </li>
                    </ol>

                </c:if>
                <div id="layoutAuthentication">
                    <div id="layoutAuthentication_content">
                        <main>
                            <div class="container">
                                <div class="row justify-content-center">
                                    <div class="col-lg-5">
                                        <div class="card shadow-lg border-0 rounded-lg mt-5">
                                            <div class="card-header"><h3 class="text-center font-weight-light my-4">Choose data for classification</h3></div>
                                            <div class="card-body">
                                                <form:form modelAttribute="testCurveDTO" method="post">
                                                    <div class="form-group">
                                                        <label class="small mb-1" for="inputEmailAddress">Pick data to evaluate (Number: 1 - <c:out value="${idRange}"/>):</label>
                                                        <form:input path="id" class="form-control py-4" id="inputEmailAddress" aria-describedby="emailHelp" placeholder="Number: 1 - ${idRange}"/><br>
                                                        <form:errors path="id"/><br>
                                                    </div>
                                                    <div class="form-row">
                                                        <div class="col-md-6">
                                                            <div class="form-group">
                                                                <label class="small mb-1" for="inputPassword">Positive data</label>
                                                                    <%--                                                                <input class="form-control py-4"  type="password" placeholder="Enter password"/>--%>
                                                                <form:radiobutton id="inputPassword" path="keplerId" value="1"/>
                                                            </div>
                                                        </div>
                                                        <div class="col-md-6">
                                                            <div class="form-group">
                                                                <label class="small mb-1" for="inputConfirmPassword">Negative data</label>
                                                                <form:radiobutton id="inputConfirmPassword" path="keplerId" value="2"/><br>
                                                                    <%--                                                                <input class="form-control py-4"  type="password" placeholder="Confirm password"/>--%>
                                                            </div>
                                                        </div>
                                                    </div>
                                                    <div class="form-row">
                                                        <form:errors path="keplerId"/>
                                                    </div>
                                                    <div class="form-group mt-4 mb-0"><input class="btn btn-primary btn-block" type="submit" value="Submit classification request"></div>
                                                </form:form>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </main>
                    </div>
                </div>
            </div>
        </main>
        <footer class="py-4 bg-light mt-auto">
            <div class="container-fluid">
                <div class="d-flex align-items-center justify-content-between small">
                    <div class="text-muted">Copyright &copy; Aleksander Machaj</div>
                    <div>
                        <a href="#">Privacy Policy</a>
                        &middot;
                        <a href="#">Terms &amp; Conditions</a>
                    </div>
                </div>
            </div>
        </footer>
    </div>
</div>
<script src="https://code.jquery.com/jquery-3.4.1.min.js" crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.bundle.min.js" crossorigin="anonymous"></script>
<script src="/resources/js/scripts.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/2.8.0/Chart.min.js" crossorigin="anonymous"></script>
<script src="/resources/dist/assets/demo/chart-area-demo.js"></script>
<script src="/resources/assets/demo/chart-bar-demo.js"></script>
<script src="https://cdn.datatables.net/1.10.20/js/jquery.dataTables.min.js" crossorigin="anonymous"></script>
<script src="https://cdn.datatables.net/1.10.20/js/dataTables.bootstrap4.min.js" crossorigin="anonymous"></script>
<script src="/resources/assets/demo/datatables-demo.js"></script>
</body>
</html>
