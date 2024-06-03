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
    <link rel="stylesheet" type="text/css" href="<c:url value="/resources/css/style.css"/>"/>
</head>
<body>
<div class="main-menu">
    <nav class="navbar">
        <div class="navbar-pages-buttons">
            <a class="page-button" id="mainPageButton" href="${pageContext.request.contextPath}/controller/">Main</a>
            <a class="page-button" href="${pageContext.request.contextPath}/controller/projects">Projects</a>
            <a class="page-button" href="${pageContext.request.contextPath}/controller/tasks">Tasks</a>
            <a class="page-button" href="${pageContext.request.contextPath}/controller/orders">Orders</a>
            <a class="page-button" href="${pageContext.request.contextPath}/controller/clients">Clients</a>
            <a class="page-button" href="${pageContext.request.contextPath}/controller/employees">Employees</a>
        </div>
        <div class="navbar-login">
            <a class="login-button" href="${pageContext.request.contextPath}/controller/login">Log in</a>
        </div>
    </nav>
</div>
<div class="container main-container">
