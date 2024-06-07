<%@include file="/WEB-INF/views/header.jsp"%>
<div class="container all-container orders-view-container">
    <div id="sideMenu" class="menu side-menu">
        <a href="#" class="button close-button" onclick="closeMenu()">&times;</a>
        <form class="filter-form" action="${pageContext.request.contextPath}/controller/employees/role"
              method="POST" role="form">
            <div class="form-group">
                <div class="filter-container">
                    <label for="filter-status-group" class="filter-label">Status</label>
                    <div class="checkbox-group" id="filter-status-group">
                        <label class="checkbox-label"><input type="checkbox" name="status" value="pending">Pending</label>
                        <label class="checkbox-label"><input type="checkbox" name="status" value="accepted">Accepted</label>
                        <label class="checkbox-label"><input type="checkbox" name="status" value="developing">Developing</label>
                        <label class="checkbox-label"><input type="checkbox" name="status" value="done">Done</label>
                        <label class="checkbox-label"><input type="checkbox" name="status" value="rejected">Rejected</label>
                        <label class="checkbox-label"><input type="checkbox" name="status" value="cancelled">Cancelled</label>
                    </div>
                </div>
                <div class="filter-container">
                    <label for="filter-client-group" class="filter-label">Client</label>
                    <div class="checkbox-group" id="filter-client-group">
                        <label class="checkbox-label"><input type="checkbox" name="status" value="tetiana">Tetiana</label>
                        <label class="checkbox-label"><input type="checkbox" name="status" value="vladyslav">Vladyslav</label>
                    </div>
                </div>
                <div class="filter-container">
                    <label for="filter-creation-date-group" class="filter-label">Creation Date</label>
                    <div class="dates-input-container" id="filter-creation-date-group">
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
            <input type="text" class="search-input" placeholder="Order Name"/>
            <button class="button">Search</button>
        </div>
        <div class="buttons-container">
            <div class="buttons-container">
                <button id="filter-button" class="button filter-button">Open Filters</button>
                <div class="button-container">
                    <label class="sort-label" for="sort-by-list">Sort by</label>
                    <select id="sort-by-list">
                        <option value="name">Name</option>
                        <option value="creationDate">Creation Date</option>
                        <option value="cost">Cost</option>
                        <option value="status">Status</option>
                    </select>
                    <label class="sort-label" for="sort-checkbox">descending</label>
                    <input type="checkbox" id="sort-checkbox"/>
                </div>
            </div>
            <div class="buttons-container">
                <button class="button" onclick="location.href='${pageContext.request.contextPath}/controller/orders/add';">Add new Order</button>
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
    <div class="cards-container orders-container">
        <c:forEach items="${orders}" var="order">
            <div class="card order-card">
                <div class="card-status-icon">
                    <img src="img/status.png" alt=""/>
                </div>
                <div class="card-name-container">
                    <span class="card-name">Order Name :
                        <span class="card-name card-name-value">${order.getName()}</span>
                    </span>
                </div>
                <div class="card-status-container">
                    <span class="card-status">Order Status :
                        <span class="card-status card-status-value">${order.getStatus().getValue()}</span>
                    </span>
                </div>
                <div class="card-cost-container">
                    <span class="card-cost">Cost :
                        <span class="card-cost card-cost-value">${order.getCost()}</span>
                    </span>
                </div>
                <div class="card-dates-container">
                    <span class="card-start-date">Creation date:<br>
                        <span class="card-start-date card-start-date-value">${order.getCreationDateString()}</span>
                    </span>
                    <span class="card-due-date">Due date:<br>
                        <span class="card-due-date card-due-date-value">${order.getDueDateString()}</span>
                    </span>
                </div>
                <div class="card-buttons-container">
                    <button class="button card-button" onclick="location.href='${pageContext.request.contextPath}/controller/orders/delete?orderId=${order.getId()}';">Delete Order</button>
                </div>
            </div>
        </c:forEach>
    </div>
</div>
<%@include file="/WEB-INF/views/footer.jsp"%>
