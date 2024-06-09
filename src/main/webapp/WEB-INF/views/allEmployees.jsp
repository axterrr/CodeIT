<%@include file="/WEB-INF/views/header.jsp"%>
<div class="container all-container employees-view-container">
    <div id="forSideMenu"></div>
    <div id="sideMenu" class="menu side-menu">
        <a href="#" class="button close-button" onclick="closeMenu()">&times;</a>
        <form class="filter-form" action="${pageContext.request.contextPath}/controller/employees" method="GET" role="form">
            <div class="form-group">
                <div class="filter-container">
                    <label for="filter-role-group" class="filter-label">Role</label>
                    <div class="checkbox-group" id="filter-role-group">
                        <label class="checkbox-label">
                            <input type="checkbox" name="roles" value="Project Manager"
                                   <c:if test="${requestScope.roles.contains('Project Manager')}">checked</c:if>/>
                            Project Manager
                        </label>
                        <label class="checkbox-label">
                            <input type="checkbox" name="roles" value="Developer"
                                   <c:if test="${requestScope.roles.contains('Developer')}">checked</c:if>/>
                            Developer
                        </label>
                        <label class="checkbox-label">
                            <input type="checkbox" name="roles" value="Tester"
                                   <c:if test="${requestScope.roles.contains('Tester')}">checked</c:if>/>
                            Tester
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
        <form action="${pageContext.request.contextPath}/controller/employees" method="GET" role="form">
            <div class="button-container container-with-button">
                <input type="text" class="search-input" placeholder="Employee Name" name="name" value="${requestScope.name}"/>
                <button type="submit" class="button">Search</button>
            </div>
        </form>
        <div class="buttons-container">
            <div class="buttons-container">
                <button id="filter-button" class="button filter-button">Open Filters</button>
                <form action="${pageContext.request.contextPath}/controller/employees" method="GET" role="form">
                    <div class="button-container">
                        <label class="sort-label" for="sort-by-list">Sort by</label>
                        <select id="sort-by-list" name="sortBy" onchange="this.closest('form').submit();">
                            <option value="name" <c:if test="${requestScope.sortBy == 'name'}">selected</c:if>>Name</option>
                            <option value="projectsAmount" <c:if test="${requestScope.sortBy == 'projectsAmount'}">selected</c:if>>Projects Amount</option>
                            <option value="tasksAmount" <c:if test="${requestScope.sortBy == 'tasksAmount'}">selected</c:if>>Tasks Amount</option>
                        </select>
                        <label class="sort-label" for="sort-checkbox">descending</label>
                        <input type="checkbox" id="sort-checkbox" name="descending" onchange="this.closest('form').submit();"
                               <c:if test="${requestScope.descending == 'on'}" >checked</c:if>/>
                    </div>
                </form>
            </div>
            <div class="buttons-container">
                <button class="button" onclick="location.href='${pageContext.request.contextPath}/controller/employees/add';">Add new Employee</button>
                <button class="button" onclick="printTable('Employees')">Create Report</button>
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
    <div class="cards-container employees-container">
        <c:forEach items="${employees}" var="employee">
            <div class="card employee-card" onclick="location.href='${pageContext.request.contextPath}/controller/employees/employee?employeeId=${employee.getId()}';">
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
                    <button class="button card-button" onclick="event.stopImmediatePropagation(); confirmDeletion('${pageContext.request.contextPath}/controller/employees/delete?employeeId=${employee.getId()}')">Delete Employee</button>
                    <button class="button card-button" onclick="event.stopImmediatePropagation(); location.href='${pageContext.request.contextPath}/controller/employees/update?employeeId=${employee.getId()}';">Edit Employee</button>
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
        iframeDoc.write('<h2 align="center">Employees</h2>');

        iframeDoc.write('<table>');
        iframeDoc.write('<tr><th>First Name</th><th>Last Name</th><th>Role</th><th>Specialisation</th><th>Email</th><th>Phone</th><th>Address</th><th>Hire Date</th><th>Birth Date</th></tr>');
        <c:forEach items="${employees}" var="employee">
        iframeDoc.write('<tr>');
        iframeDoc.write('<td>${employee.getFirstName()}</td>');
        iframeDoc.write('<td>${employee.getLastName()}</td>');
        iframeDoc.write('<td>${employee.getRole().getValue()}</td>');
        iframeDoc.write('<td>${employee.getSpecialisation()}</td>');
        iframeDoc.write('<td>${employee.getEmail()}</td>');
        iframeDoc.write('<td>${employee.getPhone()}</td>');
        iframeDoc.write('<td>${employee.getAddress()}</td>');
        iframeDoc.write('<td>${employee.getHireDateString()}</td>');
        iframeDoc.write('<td>${employee.getBirthDateString()}</td>');
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
