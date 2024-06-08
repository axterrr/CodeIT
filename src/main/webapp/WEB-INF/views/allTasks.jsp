<%@include file="/WEB-INF/views/header.jsp"%>
<div class="container all-container tasks-view-container">
    <div id="forSideMenu"></div>
    <div id="sideMenu" class="menu side-menu">
        <a href="#" class="button close-button" onclick="closeMenu()">&times;</a>
        <form class="filter-form" action="${pageContext.request.contextPath}/controller/tasks" method="GET" role="form">
            <div class="form-group">
                <div class="filter-container">
                    <label for="filter-project-group" class="filter-label">Project</label>
                    <select class="filter-select" id="filter-project-group">
                        <option></option>
                        <c:forEach items="${projects}" var="project">
                            <option value=${project.getId()}>${project.getName()}</option>
                        </c:forEach>
                    </select>
                    <div id="projectsFilterContainer">
                        <c:forEach items="${selectedProjects}" var="project">
                            <div class="client-module" id="pm${project.getId()}" style="width: 100%; height: fit-content;
                            display: flex; justify-content: space-between; align-items: center;">
                                <p style="margin: 0; font-size: 16px;">${project.getName()}</p>
                                <input type="hidden" name="selectedProjects" value="${project.getId()}">
                                <button class="button modal-button" type="button" style="border: none;">&times;</button>
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
                    <select class="filter-select" id="filter-developer-group">
                        <option></option>
                        <c:forEach items="${developers}" var="developer">
                            <option value=${developer.getId()}>${developer.getFirstName()} ${developer.getLastName()}</option>
                        </c:forEach>
                    </select>
                    <div id="developersFilterContainer">
                        <c:forEach items="${selectedDevelopers}" var="developer">
                            <div class="client-module" id="dm${developer.getId()}" style="width: 100%; height: fit-content;
                            display: flex; justify-content: space-between; align-items: center;">
                                <p style="margin: 0; font-size: 16px;">${developer.getFirstName()} ${developer.getLastName()}</p>
                                <input type="hidden" name="selectedDevelopers" value="${developer.getId()}">
                                <button class="button modal-button" type="button" style="border: none;">&times;</button>
                            </div>
                        </c:forEach>
                    </div>
                </div>
                <div class="filter-container">
                    <label for="filter-tester-group" class="filter-label">Tester</label>
                    <select class="filter-select" id="filter-tester-group">
                        <option></option>
                        <c:forEach items="${testers}" var="tester">
                            <option value=${tester.getId()}>${tester.getFirstName()} ${tester.getLastName()}</option>
                        </c:forEach>
                    </select>
                    <div id="testersFilterContainer">
                        <c:forEach items="${selectedTesters}" var="tester">
                            <div class="client-module" id="dm${tester.getId()}" style="width: 100%; height: fit-content;
                            display: flex; justify-content: space-between; align-items: center;">
                                <p style="margin: 0; font-size: 16px;">${tester.getFirstName()} ${tester.getLastName()}</p>
                                <input type="hidden" name="selectedTesters" value="${tester.getId()}">
                                <button class="button modal-button" type="button" style="border: none;">&times;</button>
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
        <div class="button-container container-with-button">
            <input type="text" class="search-input" placeholder="Task Name"/>
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
                    </select>
                    <label class="sort-label" for="sort-checkbox">descending</label>
                    <input type="checkbox" id="sort-checkbox"/>
                </div>
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

<script>
    function addProjectModule(projectId, projectName) {
        let projectModule = document.getElementById('pm'+projectId);
        if(!projectModule)
        {
            let projectModule = document.createElement('div');
            projectModule.className = 'project-module';
            projectModule.id = 'pm'+projectId;
            projectModule.style.width = '100%';
            projectModule.style.height = 'fit-content';
            projectModule.style.display = 'flex';
            projectModule.style.justifyContent = 'space-between';
            projectModule.style.alignItems = 'center';
            projectModule.style.paddingLeft = '10px 25px';

            let projectNameLabel = document.createElement('p');
            projectNameLabel.textContent = projectName;
            projectNameLabel.style.margin = '0px';
            projectNameLabel.style.fontSize = '16px';
            projectModule.appendChild(projectNameLabel);

            let idProject = document.createElement('input');
            idProject.type = 'hidden';
            idProject.name = 'selectedProjects';
            idProject.value = projectId;
            projectModule.appendChild(idProject);

            let deleteButton = document.createElement('button');
            deleteButton.classList.add('button');
            deleteButton.classList.add('modal-button');
            deleteButton.type = 'button';
            deleteButton.innerHTML = '&times;';
            deleteButton.style.border = 'none';
            deleteButton.onclick = function () {
                projectModule.remove();
            };
            projectModule.appendChild(deleteButton);

            document.getElementById('projectsFilterContainer').appendChild(projectModule);
        }
    }

    function addDeveloperModule(developerId, developerName) {
        let developerModule = document.getElementById('dm'+developerId);
        if(!developerModule)
        {
            let developerModule = document.createElement('div');
            developerModule.className = 'developer-module';
            developerModule.id = 'pm'+developerId;
            developerModule.style.width = '100%';
            developerModule.style.height = 'fit-content';
            developerModule.style.display = 'flex';
            developerModule.style.justifyContent = 'space-between';
            developerModule.style.alignItems = 'center';
            developerModule.style.paddingLeft = '10px 25px';

            let developerNameLabel = document.createElement('p');
            developerNameLabel.textContent = developerName;
            developerNameLabel.style.margin = '0px';
            developerNameLabel.style.fontSize = '16px';
            developerModule.appendChild(developerNameLabel);

            let idDeveloper = document.createElement('input');
            idDeveloper.type = 'hidden';
            idDeveloper.name = 'selectedDevelopers';
            idDeveloper.value = developerId;
            developerModule.appendChild(idDeveloper);

            let deleteButton = document.createElement('button');
            deleteButton.classList.add('button');
            deleteButton.classList.add('modal-button');
            deleteButton.type = 'button';
            deleteButton.innerHTML = '&times;';
            deleteButton.style.border = 'none';
            deleteButton.onclick = function () {
                developerModule.remove();
            };
            developerModule.appendChild(deleteButton);

            document.getElementById('developersFilterContainer').appendChild(developerModule);
        }
    }

    function addTesterModule(testerId, testerName) {
        let testerModule = document.getElementById('tm'+testerId);
        if(!testerModule)
        {
            let testerModule = document.createElement('div');
            testerModule.className = 'project-module';
            testerModule.id = 'tm'+testerId;
            testerModule.style.width = '100%';
            testerModule.style.height = 'fit-content';
            testerModule.style.display = 'flex';
            testerModule.style.justifyContent = 'space-between';
            testerModule.style.alignItems = 'center';
            testerModule.style.paddingLeft = '10px 25px';

            let testerNameLabel = document.createElement('p');
            testerNameLabel.textContent = testerName;
            testerNameLabel.style.margin = '0px';
            testerNameLabel.style.fontSize = '16px';
            testerModule.appendChild(testerNameLabel);

            let idTester = document.createElement('input');
            idTester.type = 'hidden';
            idTester.name = 'selectedTesters';
            idTester.value = testerId;
            testerModule.appendChild(idTester);

            let deleteButton = document.createElement('button');
            deleteButton.classList.add('button');
            deleteButton.classList.add('modal-button');
            deleteButton.type = 'button';
            deleteButton.innerHTML = '&times;';
            deleteButton.style.border = 'none';
            deleteButton.onclick = function () {
                testerModule.remove();
            };
            testerModule.appendChild(deleteButton);

            document.getElementById('testersFilterContainer').appendChild(testerModule);
        }
    }

    document.getElementById('filter-project-group').addEventListener('change', function() {
        if(this.options[this.selectedIndex].value === '') return;
        let projectId = this.options[this.selectedIndex].value;
        let projectName = this.options[this.selectedIndex].textContent;
        addProjectModule(projectId, projectName);
    });

    document.getElementById('filter-developer-group').addEventListener('change', function() {
        if(this.options[this.selectedIndex].value === '') return;
        let developerId = this.options[this.selectedIndex].value;
        let developerName = this.options[this.selectedIndex].textContent;
        addDeveloperModule(developerId, developerName);
    });

    document.getElementById('filter-tester-group').addEventListener('change', function() {
        if(this.options[this.selectedIndex].value === '') return;
        let testerId = this.options[this.selectedIndex].value;
        let testerName = this.options[this.selectedIndex].textContent;
        addTesterModule(testerId, testerName);
    });
</script>

<%@include file="/WEB-INF/views/footer.jsp"%>
