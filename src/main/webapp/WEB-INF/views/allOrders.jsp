<%@include file="/WEB-INF/views/header.jsp"%>
<div class="container all-container orders-view-container">
    <div id="forSideMenu"></div>
    <div id="sideMenu" class="menu side-menu">
        <a href="#" class="button close-button" onclick="closeMenu()">&times;</a>
        <form class="filter-form" action="${pageContext.request.contextPath}/controller/orders" method="GET" role="form">
            <div class="form-group">
                <div class="filter-container">
                    <label for="filter-status-group" class="filter-label">Status</label>
                    <div class="checkbox-group" id="filter-status-group">
                        <label class="checkbox-label">
                            <input type="checkbox" name="statuses" value="Pending"
                                   <c:if test="${requestScope.statuses.contains('Pending')}">checked</c:if>/>
                            Pending
                        </label>
                        <label class="checkbox-label">
                            <input type="checkbox" name="statuses" value="Accepted"
                                   <c:if test="${requestScope.statuses.contains('Accepted')}">checked</c:if>/>
                            Accepted
                        </label>
                        <label class="checkbox-label">
                            <input type="checkbox" name="statuses" value="Developing"
                                   <c:if test="${requestScope.statuses.contains('Developing')}">checked</c:if>/>
                            Developing
                        </label>
                        <label class="checkbox-label">
                            <input type="checkbox" name="statuses" value="Done"
                                   <c:if test="${requestScope.statuses.contains('Done')}">checked</c:if>/>
                            Done
                        </label>
                        <label class="checkbox-label">
                            <input type="checkbox" name="statuses" value="Rejected"
                                   <c:if test="${requestScope.statuses.contains('Rejected')}">checked</c:if>/>
                            Rejected
                        </label>
                        <label class="checkbox-label">
                            <input type="checkbox" name="statuses" value="Cancelled"
                                   <c:if test="${requestScope.statuses.contains('Cancelled')}">checked</c:if>/>
                            Cancelled
                        </label>
                    </div>
                </div>
                <c:if test="${not empty loggedEmployee and loggedEmployee.getRole() == 'CEO'}">
                    <div class="filter-container">
                        <label for="filter-client-group" class="filter-label">Client</label>
                        <select class="filter-select filter-multiple-select" id="filter-client-group">
                            <option></option>
                            <c:forEach items="${clients}" var="client">
                                <option value="${client.getId()}">${client.getName()}</option>
                            </c:forEach>
                        </select>
                        <div id="clientsFilterContainer">
                        <c:forEach items="${selectedClients}" var="client">
                            <div class="client-module" id="${client.getId()}">
                                <p class="module-label">${client.getName()}</p>
                                <input type="hidden" name="selectedClients" value="${client.getId()}">
                                <button class="button module-button" type="button">&times;</button>
                            </div>
                        </c:forEach>
                        </div>
                    </div>
                </c:if>
                <div class="filter-container">
                    <label for="filter-creation-date-group" class="filter-label">Creation Date</label>
                    <div class="dates-input-container" id="filter-creation-date-group">
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
        <form action="${pageContext.request.contextPath}/controller/orders" method="GET" role="form">
            <div class="button-container container-with-button">
                <input type="text" class="search-input" placeholder="Order Name" name="name" value="${requestScope.name}"/>
                <button type="submit" class="button">Search</button>
            </div>
        </form>
        <div class="buttons-container">
            <div class="buttons-container">
                <button id="filter-button" class="button filter-button">Open Filters</button>
                <form action="${pageContext.request.contextPath}/controller/orders" method="GET" role="form">
                    <div class="button-container">
                        <label class="sort-label" for="sort-by-list">Sort by</label>
                        <select id="sort-by-list" name="sortBy" onchange="this.closest('form').submit();">
                            <option value="name" <c:if test="${requestScope.sortBy == 'name'}">selected</c:if>>Name</option>
                            <option value="creationDate" <c:if test="${requestScope.sortBy == 'creationDate'}">selected</c:if>>Creation Date</option>
                            <option value="cost" <c:if test="${requestScope.sortBy == 'cost'}">selected</c:if>>Cost</option>
                            <option value="status" <c:if test="${requestScope.sortBy == 'status'}">selected</c:if>>Status</option>
                        </select>
                        <label class="sort-label" for="sort-checkbox">descending</label>
                        <input type="checkbox" id="sort-checkbox" name="descending" onchange="this.closest('form').submit();"
                               <c:if test="${requestScope.descending == 'on'}" >checked</c:if>/>
                    </div>
                </form>
            </div>
            <div class="buttons-container">
                <c:if test="${not empty loggedClient}">
                    <button class="button" onclick="location.href='${pageContext.request.contextPath}/controller/orders/add';">Add new Order</button>
                </c:if>
                <c:if test="${not empty loggedEmployee and loggedEmployee.getRole() == 'CEO'}">
                    <button class="button" onclick="printTable()">Create Report</button>
                </c:if>
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
            <div class="card order-card" onclick="location.href='${pageContext.request.contextPath}/controller/orders/order?orderId=${order.getId()}';">
                <div class="card-status-icon">
                    <c:choose>
                        <c:when test="${order.getStatus() == 'PENDING'}">
                            <img src="<c:url value="/resources/image/status/awaiting.png" />" alt=""/>
                        </c:when>
                        <c:when test="${order.getStatus() == 'ACCEPTED'}">
                            <img src="<c:url value="/resources/image/status/created.png" />" alt=""/>
                        </c:when>
                        <c:when test="${order.getStatus() == 'DEVELOPING'}">
                            <img src="<c:url value="/resources/image/status/developing.png" />" alt=""/>
                        </c:when>
                        <c:when test="${order.getStatus() == 'DONE'}">
                            <img src="<c:url value="/resources/image/status/done.png" />" alt=""/>
                        </c:when>
                        <c:when test="${order.getStatus() == 'REJECTED'}">
                            <img src="<c:url value="/resources/image/status/rejected.png" />" alt=""/>
                        </c:when>
                        <c:when test="${order.getStatus() == 'CANCELLED'}">
                            <img src="<c:url value="/resources/image/status/cancelled.png" />" alt=""/>
                        </c:when>
                    </c:choose>
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
                <div class="card-client-container">
                    <span class="card-client">Client :
                        <span class="card-client card-client-value">${order.getClient().getName()}</span>
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
                    <c:if test="${not empty loggedEmployee and loggedEmployee.getRole() == 'CEO'}">
                        <button class="button card-button" onclick="event.stopImmediatePropagation(); confirmDeletion('${pageContext.request.contextPath}/controller/orders/delete?orderId=${order.getId()}')">Delete Order</button>
                    </c:if>
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
        iframeDoc.write('<h2 align="center">Orders</h2>');

        iframeDoc.write('<table>');
        iframeDoc.write('<tr><th>Name</th><th>Status</th><th>Client</th><th>Cost</th><th>Creation Date</th><th>Due Date</th></tr>');
        <c:forEach items="${orders}" var="order">
        iframeDoc.write('<tr>');
        iframeDoc.write('<td>${order.getName()}</td>');
        iframeDoc.write('<td>${order.getStatus().getValue()}</td>');
        iframeDoc.write('<td>${order.getClient().getName()}</td>');
        iframeDoc.write('<td>${order.getCost()}</td>');
        iframeDoc.write('<td>${order.getCreationDateString()}</td>');
        iframeDoc.write('<td>${order.getDueDateString()}</td>');
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
