<%@include file="WEB-INF/views/header.jsp"%>
    <c:if test="${not empty param.success}">
        <div class="row-fluid">
            <div class="alert alert-success">
                <p>${param.success}</p>
            </div>
        </div>
    </c:if>
    <c:if test="${not empty param.error}">
        <div class="row-fluid">
            <div class="alert alert-danger">
                <p>${param.error}</p>
            </div>
        </div>
    </c:if>
    <div class="container">
        <h2>
            Welcome to CodeIT !
        </h2>
    </div>
<%@include file="WEB-INF/views/footer.jsp"%>
