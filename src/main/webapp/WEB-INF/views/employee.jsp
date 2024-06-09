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
<div class="container full-info-container full-info-employee-container">
    <div class="container full-container full-employee-container">
        <div class="info-container employee-info-container">
            <div class="employee-name-container">
                <label class="full-employee-label employee-name employee-name-value">${employee.getFirstName()} ${employee.getLastName()}</label>
            </div>
            <div class="employee-role-container">
                <label class="full-employee-label employee-role full-employee-info-label">Role:</label>
                <label class="full-employee-label employee-role employee-role-value">${employee.getRole().getValue()}</label>
            </div>
            <c:if test="${not empty employee.getSpecialisation()}">
                <div class="employee-specialisation-container">
                    <label class="full-employee-label employee-specialisation full-employee-info-label">Specialisation:</label>
                    <label class="full-employee-label employee-specialisation employee-specialisation-value">${employee.getSpecialisation()}</label>
                </div>
            </c:if>
            <div class="employee-email-container">
                <label class="full-employee-label employee-email full-employee-info-label">Email:</label>
                <label class="full-employee-label employee-email employee-email-value">${employee.getEmail()}</label>
            </div>
            <div class="employee-phone-container">
                <label class="full-employee-label employee-phone full-employee-info-label">Phone Number:</label>
                <label class="full-employee-label employee-phone employee-phone-value">${employee.getPhone()}</label>
            </div>
            <div class="employee-address-container">
                <label class="full-employee-label employee-address full-employee-info-label">Address:</label>
                <label class="full-employee-label employee-address employee-address-value">${employee.getAddress()}</label>
            </div>
            <div class="employee-hire-date-container">
                <label class="full-employee-label employee-hire-date full-employee-info-label">Hire date:</label>
                <label class="full-employee-label employee-hire-date employee-hire-date-value">${employee.getHireDateString()}</label>
            </div>
            <div class="employee-birth-date-container">
                <label class="full-employee-label employee-birth-date full-employee-info-label">Birth date:</label>
                <label class="full-employee-label employee-birth-date employee-birth-date-value">${employee.getBirthDateString()}</label>
            </div>
        </div>
    </div>
    <div class="container fixed-container employee-fixed-container">
        <div class="employee-tasks-container">
            <div class="employee-tasks-header">
                <c:choose>
                    <c:when test="${employee.getRole() == 'PROJECT_MANAGER'}">
                        <label class="full-project-label project-tasks">Projects:</label>
                    </c:when>
                    <c:when test="${employee.getRole() == 'DEVELOPER' || employee.getRole() == 'TESTER'}">
                        <label class="full-project-label project-tasks">Tasks:</label>
                    </c:when>
                </c:choose>
            </div>
            <div class="employee-tasks-list">
                <c:choose>
                    <c:when test="${employee.getRole() == 'PROJECT_MANAGER'}">
                        <c:forEach items="${projects}" var="project">
                            <div class="employee-task-container">
                                <a href="${pageContext.request.contextPath}/controller/projects/project?projectId=${project.getId()}">
                                    <label class="a-label full-employee-label employee-task-label">${project.getName()}</label>
                                </a>
                                <label class="full-employee-label employee-task-status">${project.getStatus().getValue()}</label>
                            </div>
                        </c:forEach>                    </c:when>
                    <c:when test="${employee.getRole() == 'DEVELOPER' || employee.getRole() == 'TESTER'}">
                        <c:forEach items="${tasks}" var="task">
                            <div class="employee-task-container">
                                <a href="${pageContext.request.contextPath}/controller/tasks/task?taskId=${task.getId()}">
                                    <label class="a-label full-employee-label employee-task-label">${task.getName()}</label>
                                </a>
                                <label class="full-employee-label employee-task-status">${task.getStatus().getValue()}</label>
                            </div>
                        </c:forEach>
                    </c:when>
                </c:choose>
            </div>
        </div>
        <div class="employee-buttons-container">
            <button class="button" onclick="event.stopImmediatePropagation(); confirmDeletion('${pageContext.request.contextPath}/controller/employees/delete?employeeId=${employee.getId()}')" >Delete Employee</button>
            <button class="button" onclick="event.stopImmediatePropagation(); location.href='${pageContext.request.contextPath}/controller/employees/update?employeeId=${employee.getId()}';">Edit Employee</button>
        </div>
    </div>
</div>
<%@include file="/WEB-INF/views/footer.jsp"%>
