<%@include file="/WEB-INF/views/header.jsp"%>
<div class="container add-container add-task-view-container">
    <h1>
        <c:choose>
            <c:when test="${not empty requestScope.taskDto.getId()}">
                Edit Task
            </c:when>
            <c:otherwise>
                Add Task
            </c:otherwise>
        </c:choose>
    </h1>
    <c:choose>
        <c:when test="${not empty requestScope.taskDto.getId()}">
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
            <c:if test="${not empty requestScope.taskDto.getId()}">
                <input type="hidden" name="taskId" value="<c:out value="${requestScope.taskDto.getId()}"/>">
            </c:if>
            <div class="input-container">
                <label class="input-label" for="name-input">Task Name</label>
                <input type="text" class="add-form-input" placeholder="Task Name" id="name-input" name="name"
                       value="<c:out value="${requestScope.taskDto.getName()}" />"/>
            </div>
            <c:if test="${empty requestScope.taskDto.getId()}">
                <div class="input-container">
                    <label class="input-label" for="project-input">Project</label>
                    <select class="add-form-input form-select" id="project-input" name="projectId">
                        <c:choose>
                            <c:when test="${not empty requestScope.projectId}">
                                <c:forEach items="${projects}" var="project">
                                    <option value=${project.getId()} <c:if test="${requestScope.projectId == project.getId()}">selected</c:if>>${project.getName()}</option>
                                </c:forEach>
                            </c:when>
                            <c:otherwise>
                                <c:forEach items="${projects}" var="project">
                                    <option value=${project.getId()} <c:if test="${requestScope.taskDto.getProjectId() == project.getId()}">selected</c:if>>${project.getName()}</option>
                                </c:forEach>
                            </c:otherwise>
                        </c:choose>
                    </select>
                </div>
            </c:if>
            <div class="input-container">
                <label class="input-label" for="developer-input">Developer</label>
                <select class="add-form-input form-select" id="developer-input" name="developerId">
                    <option value=""></option>
                    <c:forEach items="${developers}" var="developer">
                        <option value=${developer.getId()} <c:if test="${requestScope.taskDto.getDeveloperId() == developer.getId()}">selected</c:if>>${developer.getFirstName()} ${developer.getLastName()}</option>
                    </c:forEach>
                </select>
            </div>
            <div class="input-container">
                <label class="input-label" for="tester-input">Tester</label>
                <select class="add-form-input form-select" id="tester-input" name="testerId">
                    <option value=""></option>
                    <c:forEach items="${testers}" var="tester">
                        <option value=${tester.getId()} <c:if test="${requestScope.taskDto.getTesterId() == tester.getId()}">selected</c:if>>${tester.getFirstName()} ${tester.getLastName()}</option>
                    </c:forEach>
                </select>
            </div>
            <div class="input-container">
                <label class="input-label" for="description-input">Description</label>
                <textarea class="add-form-input" placeholder="Task Description" id="description-input" name="description"><c:out value="${requestScope.taskDto.getDescription()}"/></textarea>
            </div>
            <div class="input-container">
                <label class="input-label" for="link-input">Task Link</label>
                <input type="url" class="add-form-input" placeholder="Task Link" id="link-input" name="branchLink"
                       value="<c:out value="${requestScope.taskDto.getBranchLink()}" />"/>
            </div>
            <div class="input-container">
                <label class="input-label" for="due-date-input">Due Date</label>
                <input type="date" class="add-form-input" id="due-date-input" name="dueDate"
                       value="<c:out value="${requestScope.taskDto.getDueDateString()}" />"/>
            </div>
        </div>
        <div class="submit-button-container">
            <button type="submit" class="button submit-button">
                <c:choose>
                    <c:when test="${not empty requestScope.taskDto.getId()}">
                        Update Task
                    </c:when>
                    <c:otherwise>
                        Add Task
                    </c:otherwise>
                </c:choose>
            </button>
        </div>
    </form>
</div>
<%@include file="/WEB-INF/views/footer.jsp"%>
