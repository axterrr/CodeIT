<%@include file="/WEB-INF/views/header.jsp"%>
<div class="container full-info-container full-info-client-container">
    <div class="container full-container full-client-container">
        <div class="info-container client-info-container">
            <div class="client-name-container">
                <label class="full-client-label client-name client-name-value">${client.getName()}</label>
            </div>
            <c:if test="${not empty client.getPerson()}">
                <div class="client-contact-container">
                    <label class="full-client-label client-contact full-client-info-label">Contact Person:</label>
                    <label class="full-client-label client-contact client-contact-value">${client.getPerson()}</label>
                </div>
            </c:if>
            <div class="client-email-container">
                <label class="full-client-label client-email full-client-info-label">Email:</label>
                <label class="full-client-label client-email client-email-value">${client.getEmail()}</label>
            </div>
            <div class="client-phone-container">
                <label class="full-client-label client-phone full-client-info-label">Phone Number:</label>
                <label class="full-client-label client-phone client-phone-value">${client.getPhone()}</label>
            </div>
            <div class="client-address-container">
                <label class="full-client-label client-address full-client-info-label">Address:</label>
                <label class="full-client-label client-address client-address-value">${client.getAddress()}</label>
            </div>
            <div class="client-reg-date-container">
                <label class="full-client-label client-reg-date full-client-info-label">Registration date:</label>
                <label class="full-client-label client-reg-date client-reg-date-value">${client.getRegistrationDateString()}</label>
            </div>
            <c:if test="${not empty client.getDescription()}">
                <div class="client-description-container">
                    <label class="full-client-label client-description full-client-info-label">Description:</label>
                    <label class="full-client-label client-description client-description-value">${client.getDescription()}</label>
                </div>
            </c:if>
        </div>
    </div>
    <div class="container fixed-container client-fixed-container">
        <div class="client-orders-container">
            <div class="client-orders-header">
                <label class="full-client-label project-orders">Orders:</label>
            </div>
            <div class="client-orders-list">
                <c:forEach items="${orders}" var="order">
                    <div class="client-order-container">
                        <a href="${pageContext.request.contextPath}/controller/orders/order?orderId=${order.getId()}">
                            <label class="a-label full-client-label client-order-label">${order.getName()}</label>
                        </a>
                        <label class="full-client-label client-order-status">${order.getStatus().getValue()}</label>
                    </div>
                </c:forEach>
            </div>
        </div>
        <div class="client-buttons-container">
            <button class="button" onclick="location.href='${pageContext.request.contextPath}/controller/clients/delete?clientId=${client.getId()}';" >Delete Client</button>
            <button class="button" onclick="location.href='${pageContext.request.contextPath}/controller/clients/update?clientId=${client.getId()}';">Edit Client</button>
        </div>
    </div>
</div>
<%@include file="/WEB-INF/views/footer.jsp"%>
