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
<div class="container full-info-container full-info-project-container">
    <div class="container full-container full-project-container">
        <div class="info-container project-info-container">
            <div class="project-status-icon">
                <c:choose>
                    <c:when test="${project.getStatus() == 'AWAITING_CONFIRMATION'}">
                        <img src="<c:url value="/resources/image/status/awaiting.png" />" alt=""/>
                    </c:when>
                    <c:when test="${project.getStatus() == 'CREATED'}">
                        <img src="<c:url value="/resources/image/status/created.png" />" alt=""/>
                    </c:when>
                    <c:when test="${project.getStatus() == 'DEVELOPING'}">
                        <img src="<c:url value="/resources/image/status/developing.png" />" alt=""/>
                    </c:when>
                    <c:when test="${project.getStatus() == 'FINISHED'}">
                        <img src="<c:url value="/resources/image/status/done.png" />" alt=""/>
                    </c:when>
                    <c:when test="${project.getStatus() == 'CANCELLED'}">
                        <img src="<c:url value="/resources/image/status/cancelled.png" />" alt=""/>
                    </c:when>
                </c:choose>
            </div>
            <div class="project-name-container">
                <label class="full-project-label project-name project-name-value">${project.getName()}</label>
            </div>
            <div class="project-status-container">
                <label class="full-project-label project-status full-project-info-label">Project Status:</label>
                <label class="full-project-label project-status project-status-value">${project.getStatus().getValue()}</label>
            </div>
            <div class="project-order-container">
                <label class="full-project-label project-order full-project-info-label">Order:</label>
                <c:choose>
                    <c:when test="${loggedEmployee.getRole() == 'CEO'}">
                        <a href="${pageContext.request.contextPath}/controller/orders/order?orderId=${project.getOrder().getId()}">
                            <label class="a-label full-project-label project-order project-order-value">${project.getOrder().getName()}</label>
                        </a>
                    </c:when>
                    <c:otherwise>
                        <label class="full-project-label project-order project-order-value">${project.getOrder().getName()}</label>
                    </c:otherwise>
                </c:choose>
            </div>
            <div class="project-manager-container">
                <label class="full-project-label project-manager full-project-info-label">Manager:</label>
                <c:if test="${not empty project.getManager()}">
                    <a href="${pageContext.request.contextPath}/controller/employees/employee?employeeId=${project.getManager().getId()}">
                        <label class="a-label full-project-label project-manager project-manager-value">${project.getManager().getFirstName()} ${project.getManager().getLastName()}</label>
                    </a>
                </c:if>
            </div>
            <div class="project-description-container">
                <label class="full-project-label project-description full-project-info-label">Description:</label>
                <label class="full-project-label project-description project-description-value">${project.getDescription()}</label>
            </div>
            <div class="project-link-container">
                <label class="full-project-label project-link full-project-info-label">Project Link:</label>
                <a href="${project.getGitHubLink()}">
                    <label class="a-label full-project-label project-link project-link-value">${project.getGitHubLink()}</label>
                </a>
            </div>
            <c:if test="${loggedEmployee.getRole() == 'CEO' or loggedEmployee.getRole() == 'PROJECT_MANAGER'}">
                <div class="project-budget-container">
                    <label class="full-project-label project-budget full-project-info-label">Budget:</label>
                    <label class="full-project-label project-budget project-budget-value">${project.getBudget()}</label>
                </div>
            </c:if>
            <div class="project-dates-container">
                <div class="project-date-container project-start-date-container">
                    <label class="full-project-label project-start-date full-project-info-label">Start date:</label>
                    <label class="full-project-label project-start-date project-start-date-value">${project.getStartDateString()}</label>
                </div>
                <div class="project-date-container project-due-date-container">
                    <label class="full-project-label project-due-date full-project-info-label">Due date:</label>
                    <label class="full-project-label project-due-date project-due-date-value">${project.getDueDateString()}</label>
                </div>
                <c:if test="${not empty project.getEndDate()}">
                    <div class="project-date-container project-end-date-container">
                        <label class="full-project-label project-end-date full-project-info-label">End date:</label>
                        <label class="full-project-label project-end-date project-end-date-value">${project.getEndDateString()}</label>
                    </div>
                </c:if>
            </div>
        </div>
    </div>
    <div class="container fixed-container project-fixed-container">
        <div class="project-tasks-container">
            <div class="project-tasks-header">
                <label class="full-project-label project-tasks">Tasks:</label>
                <c:if test="${loggedEmployee.getRole() == 'PROJECT_MANAGER' and (project.getStatus() == 'CREATED' or project.getStatus() == 'DEVELOPING')}">
                    <button class="button" onclick="location.href='${pageContext.request.contextPath}/controller/tasks/add?projectId=${project.getId()}';">Add New Task</button>
                </c:if>
            </div>
            <div class="project-tasks-list">
                <c:forEach items="${tasks}" var="task">
                    <div class="project-task-container">
                        <a href="${pageContext.request.contextPath}/controller/tasks/task?taskId=${task.getId()}">
                            <label class="a-label full-project-label project-task-label">${task.getName()}</label>
                        </a>
                        <label class="full-project-label project-task-status">${task.getStatus().getValue()}</label>
                    </div>
                </c:forEach>
            </div>
        </div>
        <div class="project-buttons-container">
            <c:if test="${not empty loggedEmployee and loggedEmployee.getRole() == 'PROJECT_MANAGER' and project.getStatus() == 'DEVELOPING'}">
                <div class="project-buttons-container pb-part pb-part1">
                    <button class="button" onclick="location.href='${pageContext.request.contextPath}/controller/projects/submit?projectId=${project.getId()}';">Submit Project</button>
                </div>
            </c:if>
            <c:if test="${not empty loggedEmployee and loggedEmployee.getRole() == 'CEO'}">
                <c:if test="${project.getStatus() == 'AWAITING_CONFIRMATION'}">
                    <div class="project-buttons-container pb-part pb-part3">
                        <button class="button" onclick="location.href='${pageContext.request.contextPath}/controller/projects/confirm?projectId=${project.getId()}';">Confirm Project</button>
                        <button class="button" onclick="location.href='${pageContext.request.contextPath}/controller/projects/reject?projectId=${project.getId()}';">Reject Project</button>
                    </div>
                </c:if>
                <div class="project-buttons-container pb-part pb-part2">
                    <button class="button" onclick="confirmDeletion('${pageContext.request.contextPath}/controller/projects/delete?projectId=${project.getId()}')">Delete Project</button>
                    <button class="button" onclick="location.href='${pageContext.request.contextPath}/controller/projects/update?projectId=${project.getId()}';">Edit Project</button>
                </div>
            </c:if>
        </div>
    </div>
</div>
<%@include file="/WEB-INF/views/footer.jsp"%>
