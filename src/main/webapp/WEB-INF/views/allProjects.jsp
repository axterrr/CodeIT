<%@include file="/WEB-INF/views/header.jsp"%>
<div class="container all-container projects-view-container">
    <div id="sideMenu" class="menu side-menu">
        <a href="#" class="button close-button" onclick="closeMenu()">&times;</a>
        <form class="filter-form" action="${pageContext.request.contextPath}/controller/employees/role"
              method="POST" role="form">
            <div class="form-group">
                <div class="filter-container">
                    <label for="filter-status-group" class="filter-label">Status</label>
                    <div class="checkbox-group" id="filter-status-group">
                        <label class="checkbox-label"><input type="checkbox" name="status" value="created">Created</label>
                        <label class="checkbox-label"><input type="checkbox" name="status" value="developing">Developing</label>
                        <label class="checkbox-label"><input type="checkbox" name="status" value="awaiting">Awaiting Confirmation</label>
                        <label class="checkbox-label"><input type="checkbox" name="status" value="finished">Finished</label>
                        <label class="checkbox-label"><input type="checkbox" name="status" value="cancelled">Cancelled</label>
                    </div>
                </div>
                <div class="filter-container">
                    <label for="filter-manager-group" class="filter-label">Project Manager</label>
                    <div class="checkbox-group" id="filter-manager-group">
                        <c:forEach items="${managers}" var="manager">
                            <option value=${manager.getId()}>${manager.getFirstName()} ${manager.getLastName()}</option>
                        </c:forEach>
                    </div>
                </div>
                <div class="filter-container">
                    <label for="filter-start-date-group" class="filter-label">Start Date</label>
                    <div class="dates-input-container" id="filter-start-date-group">
                        <label class="date-label">From<input type="date" class="date-input from-date"/></label>
                        <label class="date-label">To<input type="date" class="date-input to-date"/></label>
                    </div>
                </div>
                <div class="filter-container">
                    <label for="filter-due-date-group" class="filter-label">Due Date</label>
                    <div class="dates-input-container" id="filter-due-date-group">
                        <label class="date-label">From<input type="date" class="date-input from-date"/></label>
                        <label class="date-label">To<input type="date" class="date-input to-date"/></label>
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
        <div class="button-container container-with-button">
            <input type="text" class="search-input" placeholder="Project Name"/>
            <button class="button">Search</button>
        </div>
        <div class="buttons-container">
            <div class="buttons-container">
                <button id="filter-button" class="button filter-button">Open Filters</button>
                <div class="button-container">
                    <label class="sort-label" for="sort-by-list">Sort by</label>
                    <select id="sort-by-list">
                        <option value="name">Name</option>
                        <option value="startDate">Start Date</option>
                        <option value="dueDate">Due Date</option>
                        <option value="status">Status</option>
                        <option value="budget">Budget</option>
                        <option value="tasksNumber">Tasks Number</option>
                        <option value="employeesNumber">Employees Number</option>
                    </select>
                    <label class="sort-label" for="sort-checkbox">descending</label>
                    <input type="checkbox" id="sort-checkbox"/>
                </div>
            </div>
            <div class="buttons-container">
                <button class="button" onclick="location.href='${pageContext.request.contextPath}/controller/projects/add';">Add new Project</button>
                <button class="button">Create Report</button>
            </div>
        </div>
    </div>
    <div class="cards-container projects-container">
        <c:forEach items="${projects}" var="project">
            <div class="card project-card">
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
                    <button class="button card-button" onclick="location.href='${pageContext.request.contextPath}/controller/projects/delete?projectId=${project.getId()}';">Delete Project</button>
                    <button class="button card-button" onclick="location.href='${pageContext.request.contextPath}/controller/projects/update?projectId=${project.getId()}';">Edit Project</button>
                </div>
            </div>
        </c:forEach>
    </div>
</div>
<%@include file="/WEB-INF/views/footer.jsp"%>
