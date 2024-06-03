<%@include file="/WEB-INF/views/header.jsp"%>
<div class="container add-container add-client-view-container">
    <h1>
        <c:choose>
            <c:when test="${not empty requestScope.clientDto.getId()}">
                Edit Client
            </c:when>
            <c:otherwise>
                Add Client
            </c:otherwise>
        </c:choose>
    </h1>
    <c:choose>
        <c:when test="${not empty requestScope.clientDto.getId()}">
            <form class="add-form" action="./update" method="POST">
        </c:when>
        <c:otherwise>
            <form class="add-form" action="./add" method="POST">
        </c:otherwise>
    </c:choose>
        <div class="form-group">
            <c:if test="${not empty requestScope.clientDto.getId()}">
                <input type="hidden" name="clientId" value="<c:out value="${requestScope.clientDto.getId()}"/>">
            </c:if>
            <div class="input-container">
                <label class="input-label" for="name-input">Client Name</label>
                <input type="text" class="add-form-input" placeholder="Client Name" id="name-input" name="name"
                       value="<c:out value="${requestScope.clientDto.getName()}" />"/>
            </div>
            <div class="input-container">
                <label class="input-label" for="contact-person-input">Contact Person</label>
                <input type="text" class="add-form-input" placeholder="Contact Person" id="contact-person-input" name="person"
                       value="<c:out value="${requestScope.clientDto.getPerson()}" />"/>
            </div>
            <div class="input-container">
                <label class="input-label" for="email-input">Email</label>
                <input type="email" class="add-form-input" placeholder="Email" id="email-input" name="email"
                       value="<c:out value="${requestScope.clientDto.getEmail()}" />"/>
            </div>
            <div class="input-container">
                <label class="input-label" for="phone-input">Phone Number</label>
                <input type="text" class="add-form-input" placeholder="Phone Number" id="phone-input" name="phone"
                       value="<c:out value="${requestScope.clientDto.getPhone()}" />"/>
            </div>
            <div class="input-container">
                <label class="input-label" for="address-input">Address</label>
                <input type="text" class="add-form-input" placeholder="Address" id="address-input" name="address"
                       value="<c:out value="${requestScope.clientDto.getAddress()}" />"/>
            </div>
            <div class="input-container">
                <label class="input-label" for="description-input">Description</label>
                <input type="text" class="add-form-input" placeholder="Description" id="description-input" name="description"
                       value="<c:out value="${requestScope.clientDto.getDescription()}" />"/>
            </div>
            <div class="input-container">
                <label class="input-label" for="password-input">Password</label>
                <input type="password" class="add-form-input" placeholder="Password" id="password-input" name="password"/>
            </div>
            <div class="input-container">
                <label class="input-label" for="confirm-password-input">Confirm Password</label>
                <input type="password" class="add-form-input" placeholder="Confirm Password" id="confirm-password-input" name="confirmPassword"/>
            </div>
        </div>
        <div class="submit-button-container">
            <button type="submit" class="button submit-button">
                <c:choose>
                    <c:when test="${not empty requestScope.clientDto.getId()}">
                        Update Client
                    </c:when>
                    <c:otherwise>
                        Add Client
                    </c:otherwise>
                </c:choose>
            </button>
        </div>
    </form>
</div>
<%@include file="/WEB-INF/views/footer.jsp"%>

