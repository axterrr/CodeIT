<%@include file="/WEB-INF/views/header.jsp"%>
<div class="container all-container employees-view-container">
    <div id="sideMenu" class="menu side-menu">
        <a href="#" class="button close-button" onclick="closeMenu()">&times;</a>
        <form class="filter-form" action="${pageContext.request.contextPath}/controller/employees/role"
              method="POST" role="form">
            <div class="form-group">
                <div class="filter-container">
                    <label for="filter-role-group" class="filter-label">Role</label>
                    <div class="checkbox-group" id="filter-role-group">
                        <label class="checkbox-label"><input type="checkbox" name="role" value="projectManager">Project Manager</label>
                        <label class="checkbox-label"><input type="checkbox" name="role" value="developer">Developer</label>
                        <label class="checkbox-label"><input type="checkbox" name="role" value="tester">Tester</label>
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
            <input type="text" class="search-input" placeholder="Employee Name"/>
            <button class="button">Search</button>
        </div>
        <div class="buttons-container">
            <div class="buttons-container">
                <button id="filter-button" class="button filter-button">Open Filters</button>
                <div class="button-container">
                    <label class="sort-label" for="sort-by-list">Sort by</label>
                    <select id="sort-by-list">
                        <option value="name">Name</option>
                        <option value="projectsAmount">Projects Amount</option>
                        <option value="tasksAmount">Tasks Amount</option>
                    </select>
                    <label class="sort-label" for="sort-checkbox">descending</label>
                    <input type="checkbox" id="sort-checkbox"/>
                </div>
            </div>
            <div class="buttons-container">
                <button class="button" onclick="location.href='${pageContext.request.contextPath}/controller/employees/add';">Add new Employee</button>
                <button class="button">Create Report</button>
            </div>
        </div>
    </div>
    <div class="cards-container employees-container">
        <c:forEach items="${employees}" var="employee">
            <div class="card employee-card">
                <div class="card-name-container">
                    <span class="card-name">Employee Name :
                        <span class="card-name card-name-value">${employee.getFirstName()} ${employee.getLastName()}</span>
                    </span>
                </div>
                <div class="card-role-container">
                    <span class="card-role">Role :
                        <span class="card-role card-role-value">${employee.getRole().getValue()}</span>
                    </span>
                </div>
                <div class="card-email-container">
                    <span class="card-email">Email :
                        <span class="card-email card-email-value">${employee.getEmail()}</span>
                    </span>
                </div>
                <div class="card-phone-container">
                    <span class="card-phone">Phone Number :
                        <span class="card-phone card-phone-value">${employee.getPhone()}</span>
                    </span>
                </div>
                <div class="card-address-container">
                    <span class="card-address">Address:
                        <span class="card-address card-address-value">${employee.getAddress()}</span>
                    </span>
                </div>
                <div class="card-buttons-container">
                    <button class="button card-button" onclick="location.href='${pageContext.request.contextPath}/controller/employees/delete?employeeId=${employee.getId()}';">Delete Employee</button>
                    <button class="button card-button" onclick="location.href='${pageContext.request.contextPath}/controller/employees/update?employeeId=${employee.getId()}';">Edit Employee</button>
                </div>
            </div>
        </c:forEach>
    </div>
</div>
<%@include file="/WEB-INF/views/footer.jsp"%>
