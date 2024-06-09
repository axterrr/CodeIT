<%@include file="/WEB-INF/views/header.jsp"%>
<div class="container all-container projects-view-container">
    <div id="forSideMenu"></div>
    <div id="sideMenu" class="menu side-menu">
        <a href="#" class="button close-button" onclick="closeMenu()">&times;</a>
        <form class="filter-form" action="${pageContext.request.contextPath}/controller/projects" method="GET" role="form">
            <div class="form-group">
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
                    <label for="filter-manager-group" class="filter-label">Project Manager</label>
                    <select class="filter-select filter-multiple-select" id="filter-manager-group">
                        <option></option>
                        <c:forEach items="${managers}" var="manager">
                            <option value=${manager.getId()}>${manager.getFirstName()} ${manager.getLastName()}</option>
                        </c:forEach>
                    </select>
                    <div id="managersFilterContainer">
                        <c:forEach items="${selectedManagers}" var="manager">
                            <div class="client-module" id="${manager.getId()}">
                                <p class="module-label">${manager.getFirstName()} ${manager.getLastName()}</p>
                                <input type="hidden" name="selectedManagers" value="${manager.getId()}">
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
        <form action="${pageContext.request.contextPath}/controller/projects" method="GET" role="form">
            <div class="button-container container-with-button">
                <input type="text" class="search-input" placeholder="Project Name" name="name" value="${requestScope.name}"/>
                <button type="submit" class="button">Search</button>
            </div>
        </form>
        <div class="buttons-container">
            <div class="buttons-container">
                <button id="filter-button" class="button filter-button">Open Filters</button>
                <form action="${pageContext.request.contextPath}/controller/projects" method="GET" role="form">
                    <div class="button-container">
                        <label class="sort-label" for="sort-by-list">Sort by</label>
                        <select id="sort-by-list" name="sortBy" onchange="this.closest('form').submit();">
                            <option value="name" <c:if test="${requestScope.sortBy == 'name'}">selected</c:if>>Name</option>
                            <option value="startDate" <c:if test="${requestScope.sortBy == 'startDate'}">selected</c:if>>Start Date</option>
                            <option value="dueDate" <c:if test="${requestScope.sortBy == 'dueDate'}">selected</c:if>>Due Date</option>
                            <option value="status" <c:if test="${requestScope.sortBy == 'status'}">selected</c:if>>Status</option>
                            <option value="budget" <c:if test="${requestScope.sortBy == 'budget'}">selected</c:if>>Budget</option>
                            <option value="tasksNumber" <c:if test="${requestScope.sortBy == 'tasksNumber'}">selected</c:if>>Tasks Number</option>
                            <option value="employeesNumber" <c:if test="${requestScope.sortBy == 'employeesNumber'}">selected</c:if>>Employees Number</option>
                        </select>
                        <label class="sort-label" for="sort-checkbox">descending</label>
                        <input type="checkbox" id="sort-checkbox" name="descending" onchange="this.closest('form').submit();"
                               <c:if test="${requestScope.descending == 'on'}" >checked</c:if>/>
                    </div>
                </form>
            </div>
            <div class="buttons-container">
                <button class="button" onclick="location.href='${pageContext.request.contextPath}/controller/projects/add';">Add new Project</button>
                <button class="button" onclick="printTable()">Create Report</button>
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
    <div class="cards-container projects-container">
        <c:forEach items="${projects}" var="project">
            <div class="card project-card" onclick="location.href='${pageContext.request.contextPath}/controller/projects/project?projectId=${project.getId()}';">
                <div class="card-status-icon">
                    <img src="img/status.png" alt=""/>
                </div>
                <div class="card-name-container">
                    <span class="card-name">Project Name :
                        <span class="card-name card-name-value">${project.getName()}</span>
                    </span>
                </div>
                <div class="card-status-container">
                    <span class="card-status">Project Status :
                        <span class="card-status card-status-value">${project.getStatus().getValue()}</span>
                    </span>
                </div>
                <div class="card-manager-container">
                    <span class="card-manager">Manager Name :
                        <span class="card-manager card-manager-value">${project.getManager().getFirstName()} ${project.getManager().getLastName()}</span>
                    </span>
                </div>
                <div class="card-dates-container">
                    <span class="card-start-date">Start date:<br>
                        <span class="card-start-date card-start-date-value">${project.getStartDateString()}</span>
                    </span>
                    <span class="card-due-date">Due date:<br>
                        <span class="card-due-date card-due-date-value">${project.getDueDateString()}</span>
                    </span>
                </div>
                <div class="card-buttons-container">
                    <button class="button card-button" onclick="event.stopImmediatePropagation(); confirmDeletion('${pageContext.request.contextPath}/controller/projects/delete?projectId=${project.getId()}')">Delete Project</button>
                    <button class="button card-button" onclick="event.stopImmediatePropagation(); location.href='${pageContext.request.contextPath}/controller/projects/update?projectId=${project.getId()}';">Edit Project</button>
                </div>
            </div>
        </c:forEach>
    </div>
</div>
<script>
    function printTable() {
        var iframe = document.createElement('iframe');
        iframe.style.display = 'none';
        document.body.appendChild(iframe);
        var iframeDoc = iframe.contentWindow.document;
        iframeDoc.write('<style>body {padding-top: 50px; padding-bottom: 50px; justify-content: stretch;} table {width: 100%; border-collapse: collapse;} th, td {border: 1px solid black; padding: 8px; text-align: left;} th {background-color: #f2f2f2;}</style>');
        iframeDoc.write('<h2 align="center">Projects</h2>');

        iframeDoc.write('<table>');
        iframeDoc.write('<tr><th>Name</th><th>Status</th><th>Manager</th><th>Link</th><th>Budget</th><th>Start Date</th><th>Due Date</th><th>End Date</th></tr>');
        <c:forEach items="${projects}" var="project">
        iframeDoc.write('<tr>');
        iframeDoc.write('<td>${project.getName()}</td>');
        iframeDoc.write('<td>${project.getStatus().getValue()}</td>');
        iframeDoc.write('<td>${project.getManager().getFirstName()} ${project.getManager().getLastName()}</td>');
        iframeDoc.write('<td>${project.getGitHubLink()}</td>');
        iframeDoc.write('<td>${project.getBudget()}</td>');
        iframeDoc.write('<td>${project.getStartDateString()}</td>');
        iframeDoc.write('<td>${project.getDueDateString()}</td>');
        iframeDoc.write('<td>${project.getEndDateString()}</td>');
        iframeDoc.write('</tr>');
        </c:forEach>
        iframeDoc.write('</table>');

        iframeDoc.close();
        iframe.onload = function() {
            iframe.contentWindow.print();
        };
    }
</script>
<%@include file="/WEB-INF/views/footer.jsp"%>
