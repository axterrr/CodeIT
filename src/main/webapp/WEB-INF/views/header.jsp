<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ page session="true"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en_US">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>CodeIT</title>
    <link rel="stylesheet" type="text/css" href="<c:url value="/resources/css/stylesheet.css"/>"/>
    <link rel="icon" href="<c:url value="/resources/image/icon.png" />">
    <script type="text/javascript">
        function confirmDeletion(url) {
            var result = confirm("Are you sure that you want to delete this element?");
            if (result) {
                location.href = url;
            }
        }
    </script>
</head>
<body>
<div class="main-menu">
    <img class="main-menu-icon" src="<c:url value="/resources/image/logo1.png" />" alt="">
    <nav class="navbar">
        <div class="navbar-pages-buttons">
            <a class="page-button" id="mainPageButton" href="${pageContext.request.contextPath}/controller/">Main</a>
            <c:if test="${not empty loggedEmployee}">
                <a class="page-button" href="${pageContext.request.contextPath}/controller/projects">
                    <c:choose>
                        <c:when test="${loggedEmployee.getRole() != 'CEO'}">
                            My Projects
                        </c:when>
                        <c:otherwise>
                            Projects
                        </c:otherwise>
                    </c:choose>
                </a>
                <a class="page-button" href="${pageContext.request.contextPath}/controller/tasks">
                    <c:choose>
                        <c:when test="${loggedEmployee.getRole() == 'DEVELOPER' or loggedEmployee.getRole() == 'TESTER'}">
                            My Tasks
                        </c:when>
                        <c:otherwise>
                            Tasks
                        </c:otherwise>
                    </c:choose>
                </a>
            </c:if>
            <c:if test="${not empty loggedClient or (not empty loggedEmployee and loggedEmployee.getRole() == 'CEO')}">
                <a class="page-button" href="${pageContext.request.contextPath}/controller/orders">
                    <c:choose>
                        <c:when test="${not empty loggedClient}">
                            My Orders
                        </c:when>
                        <c:otherwise>
                            Orders
                        </c:otherwise>
                    </c:choose>
                </a>
                <c:choose>
                    <c:when test="${not empty loggedClient}">
                        <a class="page-button" href="${pageContext.request.contextPath}/controller/clients/client?clientId=${loggedClient.getId()}">My Page</a>
                    </c:when>
                    <c:otherwise>
                        <a class="page-button" href="${pageContext.request.contextPath}/controller/clients">Clients</a>
                    </c:otherwise>
                </c:choose>
            </c:if>
            <c:if test="${not empty loggedEmployee}">
                <c:choose>
                    <c:when test="${loggedEmployee.getRole() == 'DEVELOPER' or loggedEmployee.getRole() == 'TESTER'}">
                        <a class="page-button" href="${pageContext.request.contextPath}/controller/employees/employee?employeeId=${loggedEmployee.getId()}">My Page</a>
                    </c:when>
                    <c:otherwise>
                        <a class="page-button" href="${pageContext.request.contextPath}/controller/employees">Employees</a>
                    </c:otherwise>
                </c:choose>
            </c:if>
        </div>
        <c:if test="${not empty loggedEmployee}">
            <label class="navbar-text" id="logged-in-label">Logged in as ${loggedEmployee.getRole().getValue()}<br>${loggedEmployee.getEmail()}</label>
        </c:if>
        <div class="navbar-login">
            <c:choose>
                <c:when test="${empty loggedEmployee and empty loggedClient}">
                    <a class="login-button" href="${pageContext.request.contextPath}/controller/login">Log in</a>
                </c:when>
                <c:otherwise>
                    <a class="login-button" href="${pageContext.request.contextPath}/controller/logout">Log out</a>
                </c:otherwise>
            </c:choose>
        </div>
    </nav>
</div>
<div class="container main-container">
