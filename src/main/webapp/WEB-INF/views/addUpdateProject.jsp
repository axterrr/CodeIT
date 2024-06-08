<%@include file="/WEB-INF/views/header.jsp"%>
<div class="container add-container add-project-view-container">
    <h1>
        <c:choose>
            <c:when test="${not empty requestScope.projectDto.getId()}">
                Edit Project
            </c:when>
            <c:otherwise>
                Add Project
            </c:otherwise>
        </c:choose>
    </h1>
    <c:choose>
        <c:when test="${not empty requestScope.projectDto.getId()}">
            <form class="add-form" action="./update" method="POST">
        </c:when>
        <c:otherwise>
            <form class="add-form" action="./add" method="POST">
        </c:otherwise>
    </c:choose>
        <c:if test="${not empty requestScope.errors}">
            <div class="alert alert-danger">
                <c:forEach items="${requestScope.errors}" var="error">
                    <p>${error}</p>
                </c:forEach>
            </div>
        </c:if>
        <div class="form-group">
            <c:if test="${not empty requestScope.projectDto.getId()}">
                <input type="hidden" name="projectId" value="<c:out value="${requestScope.projectDto.getId()}"/>">
            </c:if>
            <div class="input-container">
                <label class="input-label" for="name-input">Project Name</label>
                <input type="text" class="add-form-input" placeholder="Project Name" id="name-input" name="name"
                       value="<c:out value="${requestScope.projectDto.getName()}" />"/>
            </div>
            <c:if test="${empty requestScope.projectDto.getId()}">
                <div class="input-container">
                    <label class="input-label" for="order-input">Order</label>
                    <select class="add-form-input form-select" id="order-input" name="orderId">
                        <option value=""></option>
                        <c:forEach items="${orders}" var="order">
                            <option value=${order.getId()} <c:if test="${requestScope.projectDto.getOrderId() == order.getId()}">selected</c:if>>${order.getName()}</option>
                        </c:forEach>
                    </select>
                </div>
            </c:if>
            <div class="input-container">
                <label class="input-label" for="manager-input">Project Manager</label>
                <select class="add-form-input form-select" id="manager-input" name="managerId">
                    <option value=""></option>
                    <c:forEach items="${managers}" var="manager">
                        <option value=${manager.getId()} <c:if test="${requestScope.projectDto.getManagerId() == manager.getId()}">selected</c:if>>${manager.getFirstName()} ${manager.getLastName()}</option>
                    </c:forEach>
                </select>
            </div>
            <div class="input-container">
                <label class="input-label" for="description-input">Description</label>
                <textarea class="add-form-input" placeholder="Project Description" id="description-input" name="description"><c:out value="${requestScope.projectDto.getDescription()}"/></textarea>
            </div>
            <div class="input-container">
                <label class="input-label" for="link-input">Project Link</label>
                <input type="url" class="add-form-input" placeholder="Project Link" id="link-input" name="projectLink"
                       value="<c:out value="${requestScope.projectDto.getGitHubLink()}" />"/>
            </div>
            <div class="input-container">
                <label class="input-label" for="budget-input">Budget</label>
                <input type="number" class="add-form-input" placeholder="Budget" id="budget-input" name="budget"
                       value="<c:out value="${requestScope.projectDto.getBudget()}" />"/>
            </div>
            <div class="input-container">
                <label class="input-label" for="due-date-input">Due Date</label>
                <input type="date" class="add-form-input" id="due-date-input" name="dueDate"
                       value="<c:out value="${requestScope.projectDto.getDueDateString()}" />"/>
            </div>
        </div>
        <div class="submit-button-container">
            <button type="submit" class="button submit-button">
                <c:choose>
                    <c:when test="${not empty requestScope.projectDto.getId()}">
                        Update Project
                    </c:when>
                    <c:otherwise>
                        Add Project
                    </c:otherwise>
                </c:choose>
            </button>
        </div>
    </form>
</div>
<%@include file="/WEB-INF/views/footer.jsp"%>
