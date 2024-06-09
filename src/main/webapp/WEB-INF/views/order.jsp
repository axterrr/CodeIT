<%@include file="/WEB-INF/views/header.jsp"%>
<c:if test="${not empty param.success}">
    <div class="row-fluid" style="margin-top: 50px;">
        <div class="alert alert-success">
            <p>${param.success}</p>
        </div>
    </div>
</c:if>
<c:if test="${not empty param.error}">
    <div class="row-fluid" style="margin-top: 50px;">
        <div class="alert alert-danger">
            <p>${param.error}</p>
        </div>
    </div>
</c:if>
<div class="container full-info-container full-info-order-container">
    <div class="container full-container full-order-container">
        <div class="info-container order-info-container">
            <div class="order-status-icon">
                <img src="img/status.png" alt=""/>
            </div>
            <div class="order-name-container">
                <label class="full-order-label order-name order-name-value">${order.getName()}</label>
            </div>
            <div class="order-status-container">
                <label class="full-order-label order-status full-order-info-label">Order Status:</label>
                <label class="full-order-label order-status order-status-value">${order.getStatus().getValue()}</label>
            </div>
            <c:if test="${not empty project}">
                <div class="order-project-container">
                    <label class="full-order-label order-project full-order-info-label">Project:</label>
                    <a href="${pageContext.request.contextPath}/controller/projects/project?projectId=${project.getId()}">
                        <label class="a-label full-order-label order-project order-project-value">${project.getName()}</label>
                    </a>
                </div>
            </c:if>
            <div class="order-client-container">
                <label class="full-order-label order-client full-order-info-label">Client:</label>
                <a href="${pageContext.request.contextPath}/controller/clients/client?clientId=${order.getClient().getId()}">
                    <label class="a-label full-order-label order-client order-client-value">${order.getClient().getName()}</label>
                </a>
            </div>
            <div class="order-description-container">
                <label class="full-order-label order-description full-order-info-label">Description:</label>
                <label class="full-order-label order-description order-description-value">${order.getDescription()}</label>
            </div>
            <div class="order-cost-container">
                <label class="full-order-label order-cost full-order-info-label">Cost:</label>
                <label class="full-order-label order-cost order-cost-value">${order.getCost()}</label>
            </div>
            <div class="order-dates-container">
                <div class="order-date-container order-creation-date-container">
                    <label class="full-order-label order-creation-date full-order-info-label">Creation date:</label>
                    <label class="full-order-label order-creation-date order-creation-date-value">${order.getCreationDateString()}</label>
                </div>
                <div class="order-date-container order-due-date-container">
                    <label class="full-order-label order-due-date full-order-info-label">Due date:</label>
                    <label class="full-order-label order-due-date order-due-date-value">${order.getDueDateString()}</label>
                </div>
            </div>
        </div>
    </div>
    <div class="container fixed-container order-fixed-container">
        <div class="order-buttons-container">
            <div class="order-buttons-container ob-part ob-part1">
                <button class="button" onclick="location.href='${pageContext.request.contextPath}/controller/projects/add?orderId=${order.getId()}';">Add Project</button>
            </div>
            <div class="order-buttons-container ob-part ob-part3">
                <button class="button" onclick="location.href='${pageContext.request.contextPath}/controller/orders/accept?orderId=${order.getId()}';">Accept Order</button>
                <button class="button" onclick="location.href='${pageContext.request.contextPath}/controller/orders/reject?orderId=${order.getId()}';">Reject Order</button>
            </div>
            <div class="order-buttons-container ob-part ob-part2">
                <button class="button" onclick="location.href='${pageContext.request.contextPath}/controller/orders/cancel?orderId=${order.getId()}';">Cancel Order</button>
                <button class="button" onclick="confirmDeletion('${pageContext.request.contextPath}/controller/orders/delete?orderId=${order.getId()}')">Delete Order</button>
            </div>
        </div>
    </div>
</div>
<%@include file="/WEB-INF/views/footer.jsp"%>
