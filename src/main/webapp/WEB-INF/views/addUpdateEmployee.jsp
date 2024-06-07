<%@include file="/WEB-INF/views/header.jsp"%>
<div class="container add-container add-employee-view-container">
    <h1>
        <c:choose>
            <c:when test="${not empty requestScope.employeeDto.getId()}">
                Edit Employee
            </c:when>
            <c:otherwise>
                Add Employee
            </c:otherwise>
        </c:choose>
    </h1>
    <c:choose>
        <c:when test="${not empty requestScope.employeeDto.getId()}">
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
            <c:if test="${not empty requestScope.employeeDto.getId()}">
                <input type="hidden" name="employeeId" value="<c:out value="${requestScope.employeeDto.getId()}"/>">
            </c:if>
            <div class="input-container">
                <label class="input-label" for="first-name-input">First Name</label>
                <input type="text" class="add-form-input" placeholder="First Name" id="first-name-input" name="firstName"
                       value="<c:out value="${requestScope.employeeDto.getFirstName()}" />"/>
            </div>
            <div class="input-container">
                <label class="input-label" for="last-name-input">Last Name</label>
                <input type="text" class="add-form-input" placeholder="Last Name" id="last-name-input" name="lastName"
                       value="<c:out value="${requestScope.employeeDto.getLastName()}" />"/>
            </div>
            <div class="input-container">
                <label class="input-label" for="role-input">Role</label>
                <select class="add-form-input form-select" id="role-input" name="role">
                    <option value="Project Manager" <c:if test="${requestScope.employeeDto.getRoleString() == 'Project Manager'}">selected</c:if>>Project Manager</option>
                    <option value="Developer" <c:if test="${requestScope.employeeDto.getRoleString() == 'Developer'}">selected</c:if>>Developer</option>
                    <option value="Tester" <c:if test="${requestScope.employeeDto.getRoleString() == 'Tester'}">selected</c:if>>Tester</option>
                </select>
            </div>
            <div class="input-container">
                <label class="input-label" for="specialisation-input">Specialisation</label>
                <input type="text" class="add-form-input" placeholder="Specialisation" id="specialisation-input" name="specialisation"
                       value="<c:out value="${requestScope.employeeDto.getSpecialisation()}" />"/>
            </div>
            <div class="input-container">
                <label class="input-label" for="salary-input">Salary</label>
                <input type="number" class="add-form-input" placeholder="Salary" id="salary-input" name="salary"
                       value="<c:out value="${requestScope.employeeDto.getSalary()}" />"/>
            </div>
            <div class="input-container">
                <label class="input-label" for="email-input">Email</label>
                <input type="email" class="add-form-input" placeholder="Email" id="email-input" name="email"
                       value="<c:out value="${requestScope.employeeDto.getEmail()}" />"/>
            </div>
            <div class="input-container">
                <label class="input-label" for="phone-input">Phone Number</label>
                <input type="text" class="add-form-input" placeholder="Phone Number" id="phone-input" name="phone"
                       value="<c:out value="${requestScope.employeeDto.getPhone()}" />"/>
            </div>
            <div class="input-container">
                <label class="input-label" for="address-input">Address</label>
                <input type="text" class="add-form-input" placeholder="Address" id="address-input" name="address"
                       value="<c:out value="${requestScope.employeeDto.getAddress()}" />"/>
            </div>
            <div class="input-container">
                <label class="input-label" for="birth-date-input">Birth Date</label>
                <input type="date" class="add-form-input" id="birth-date-input" name="birthDate"
                       value="<c:out value="${requestScope.employeeDto.getBirthDateString()}" />"/>
            </div>
            <div class="input-container">
                <label class="input-label" for="password-input">Password</label>
                <input type="password" class="add-form-input" placeholder="Password" id="password-input" name="password"/>
            </div>
            <div class="input-container">
                <label class="input-label" for="confirm-password-input">Confirm Password</label>
                <input type="password" class="add-form-input" placeholder="Confirm Password" id="confirm-password-input" name="confirmPassword"/>
            </div>
        </div>
        <div class="submit-button-container">
            <button type="submit" class="button submit-button">
                <c:choose>
                    <c:when test="${not empty requestScope.employeeDto.getId()}">
                        Update Employee
                    </c:when>
                    <c:otherwise>
                        Add Employee
                    </c:otherwise>
                </c:choose>
            </button>
        </div>
    </form>
</div>
<%@include file="/WEB-INF/views/footer.jsp"%>

