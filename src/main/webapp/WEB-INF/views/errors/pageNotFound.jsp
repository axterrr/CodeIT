<%@ page isErrorPage="true" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en_US">
<head>
    <title>Error Page</title>
    <link rel="stylesheet" type="text/css" href="<c:url value="/resources/css/style.css" />" />
</head>
<body>
<div class="error-container container-fluid " align="center">
    <div class="row-fluid ">
        <div class=" error-labels alert alert-danger ">
            <label><strong>404</strong></label>
            <label><strong>Page is not found !</strong></label>
        </div>
    </div>
    <div class="row-fluid ">
        <button class="button home-button" onclick="location.href='${pageContext.request.contextPath}/controller/';">Back to home page</button>
    </div>
</div>
</body>
</html>
