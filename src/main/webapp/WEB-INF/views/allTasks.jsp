<%@include file="/WEB-INF/views/header.jsp"%>
<div class="container all-container tasks-view-container">
    <div id="forSideMenu"></div>
    <div id="sideMenu" class="menu side-menu">
        <a href="#" class="button close-button" onclick="closeMenu()">&times;</a>
        <form class="filter-form" action="${pageContext.request.contextPath}/controller/tasks" method="GET" role="form">
            <div class="form-group">
                <div class="filter-container">
                    <label for="filter-project-group" class="filter-label">Project</label>
                    <select class="filter-select filter-multiple-select" id="filter-project-group">
                        <option></option>
                        <c:forEach items="${projects}" var="project">
                            <option value=${project.getId()}>${project.getName()}</option>
                        </c:forEach>
                    </select>
                    <div id="projectsFilterContainer">
                        <c:forEach items="${selectedProjects}" var="project">
                            <div class="client-module" id="${project.getId()}">
                                <p class="module-label">${project.getName()}</p>
                                <input type="hidden" name="selectedProjects" value="${project.getId()}">
                                <button class="button module-button" type="button">&times;</button>
                            </div>
                        </c:forEach>
                    </div>
                </div>
                <div class="filter-container">
                    <label for="filter-status-group" class="filter-label">Status</label>
                    <div class="checkbox-group" id="filter-status-group">
                        <label class="checkbox-label">
                            <input type="checkbox" name="statuses" value="Created"
                                   <c:if test="${requestScope.statuses.contains('Created')}">checked</c:if>/>
                            Created
                        </label>
                        <label class="checkbox-label">
                            <input type="checkbox" name="statuses" value="Developing"
                                   <c:if test="${requestScope.statuses.contains('Developing')}">checked</c:if>/>
                            Developing
                        </label>
                        <label class="checkbox-label">
                            <input type="checkbox" name="statuses" value="Testing"
                                   <c:if test="${requestScope.statuses.contains('Testing')}">checked</c:if>/>
                            Testing
                        </label>
                        <label class="checkbox-label">
                            <input type="checkbox" name="statuses" value="Awaiting for Confirmation"
                                   <c:if test="${requestScope.statuses.contains('Awaiting for Confirmation')}">checked</c:if>/>
                            Awaiting for Confirmation
                        </label>
                        <label class="checkbox-label">
                            <input type="checkbox" name="statuses" value="Finished"
                                   <c:if test="${requestScope.statuses.contains('Finished')}">checked</c:if>/>
                            Finished
                        </label>
                        <label class="checkbox-label">
                            <input type="checkbox" name="statuses" value="Cancelled"
                                   <c:if test="${requestScope.statuses.contains('Cancelled')}">checked</c:if>/>
                            Cancelled
                        </label>
                    </div>
                </div>
                <div class="filter-container">
                    <label for="filter-developer-group" class="filter-label">Developer</label>
                    <select class="filter-select filter-multiple-select" id="filter-developer-group">
                        <option></option>
                        <c:forEach items="${developers}" var="developer">
                            <option value=${developer.getId()}>${developer.getFirstName()} ${developer.getLastName()}</option>
                        </c:forEach>
                    </select>
                    <div id="developersFilterContainer">
                        <c:forEach items="${selectedDevelopers}" var="developer">
                            <div class="developer-module" id="${developer.getId()}">
                                <p class="module-label">${developer.getFirstName()} ${developer.getLastName()}</p>
                                <input type="hidden" name="selectedDevelopers" value="${developer.getId()}">
                                <button class="button module-button" type="button">&times;</button>
                            </div>
                        </c:forEach>
                    </div>
                </div>
                <div class="filter-container">
                    <label for="filter-tester-group" class="filter-label">Tester</label>
                    <select class="filter-select filter-multiple-select" id="filter-tester-group">
                        <option></option>
                        <c:forEach items="${testers}" var="tester">
                            <option value=${tester.getId()}>${tester.getFirstName()} ${tester.getLastName()}</option>
                        </c:forEach>
                    </select>
                    <div id="testersFilterContainer">
                        <c:forEach items="${selectedTesters}" var="tester">
                            <div class="client-module" id="${tester.getId()}">
                                <p class="module-label">${tester.getFirstName()} ${tester.getLastName()}</p>
                                <input type="hidden" name="selectedTesters" value="${tester.getId()}">
                                <button class="button module-button" type="button">&times;</button>
                            </div>
                        </c:forEach>
                    </div>
                </div>
                <div class="filter-container">
                    <label for="filter-start-date-group" class="filter-label">Start Date</label>
                    <div class="dates-input-container" id="filter-start-date-group">
                        <label class="date-label">
                            From
                            <input type="date" class="date-input from-date" name="startDateFrom"
                                   value="${requestScope.startDateFrom}"/>
                        </label>
                        <label class="date-label">
                            To
                            <input type="date" class="date-input to-date" name="startDateTo"
                                   value="${requestScope.startDateTo}"/>
                        </label>
                    </div>
                </div>
                <div class="filter-container">
                    <label for="filter-due-date-group" class="filter-label">Due Date</label>
                    <div class="dates-input-container" id="filter-due-date-group">
                        <label class="date-label">
                            From
                            <input type="date" class="date-input from-date" name="dueDateFrom"
                                   value="${requestScope.dueDateFrom}"/>
                        </label>
                        <label class="date-label">
                            To
                            <input type="date" class="date-input to-date" name="dueDateTo"
                                   value="${requestScope.dueDateTo}"/>
                        </label>
                    </div>
                </div>
            </div>
            <div class="menu-footer">
                <button type="submit" class="button submit-button">
                    Apply Filters
                </button>
            </div>
        </form>
    </div>
    <div class="functions-buttons-container">
        <form action="${pageContext.request.contextPath}/controller/tasks" method="GET" role="form">
            <div class="button-container container-with-button">
                <input type="text" class="search-input" placeholder="Task Name" name="name" value="${requestScope.name}"/>
                <button type="submit" class="button">Search</button>
            </div>
        </form>
        <div class="buttons-container">
            <div class="buttons-container">
                <button id="filter-button" class="button filter-button">Open Filters</button>
                <form action="${pageContext.request.contextPath}/controller/tasks" method="GET" role="form">
                    <div class="button-container">
                        <label class="sort-label" for="sort-by-list">Sort by</label>
                        <select id="sort-by-list" name="sortBy" onchange="this.closest('form').submit();">
                            <option value="name" <c:if test="${requestScope.sortBy == 'name'}">selected</c:if>>Name</option>
                            <option value="startDate" <c:if test="${requestScope.sortBy == 'startDate'}">selected</c:if>>Start Date</option>
                            <option value="dueDate" <c:if test="${requestScope.sortBy == 'dueDate'}">selected</c:if>>Due Date</option>
                            <option value="status" <c:if test="${requestScope.sortBy == 'status'}">selected</c:if>>Status</option>
                        </select>
                        <label class="sort-label" for="sort-checkbox">descending</label>
                        <input type="checkbox" id="sort-checkbox" name="descending" onchange="this.closest('form').submit();"
                               <c:if test="${requestScope.descending == 'on'}" >checked</c:if>/>
                    </div>
                </form>
            </div>
            <div class="buttons-container">
                <button class="button" onclick="location.href='${pageContext.request.contextPath}/controller/tasks/add';">Add new Task</button>
                <button class="button">Create Report</button>
            </div>
        </div>
    </div>
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
    <div class="cards-container tasks-container">
        <c:forEach items="${tasks}" var="task">
            <div class="card task-card" onclick="location.href='${pageContext.request.contextPath}/controller/tasks/task?taskId=${task.getId()}';">
                <div class="card-status-icon">
                    <img src="img/status.png" alt=""/>
                </div>
                <div class="card-name-container">
                    <span class="card-name">Task Name :
                        <span class="card-name card-name-value">${task.getName()}</span>
                    </span>
                </div>
                <div class="card-status-container">
                    <span class="card-status">Task Status :
                        <span class="card-status card-status-value">${task.getStatus().getValue()}</span>
                    </span>
                </div>
                <div class="card-task-project-container">
                    <span class="card-task-project">Project :
                        <span class="card-task-project card-task-project-value">${task.getProject().getName()}</span>
                    </span>
                </div>
                <div class="card-developer-container">
                    <span class="card-developer">Developer Name :
                        <span class="card-developer card-developer-value">${task.getDeveloper().getFirstName()} ${task.getDeveloper().getLastName()}</span>
                    </span>
                </div>
                <div class="card-tester-container">
                    <span class="card-tester">Tester Name :
                        <span class="card-tester card-tester-value">${task.getTester().getFirstName()} ${task.getTester().getLastName()}</span>
                    </span>
                </div>
                <div class="card-dates-container">
                    <span class="card-start-date">Start date:<br>
                        <span class="card-start-date card-start-date-value">${task.getStartDateString()}</span>
                    </span>
                    <span class="card-due-date">Due date:<br>
                        <span class="card-due-date card-due-date-value">${task.getDueDateString()}</span>
                    </span>
                </div>
                <div class="card-buttons-container">
                    <button class="button card-button" onclick="event.stopImmediatePropagation(); location.href='${pageContext.request.contextPath}/controller/tasks/delete?taskId=${task.getId()}';">Delete Task</button>
                    <button class="button card-button" onclick="event.stopImmediatePropagation(); location.href='${pageContext.request.contextPath}/controller/tasks/update?taskId=${task.getId()}';">Edit Task</button>
                </div>
            </div>
        </c:forEach>
    </div>
</div>
<%@include file="/WEB-INF/views/footer.jsp"%>
