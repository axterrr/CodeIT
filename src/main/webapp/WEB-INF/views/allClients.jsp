<%@include file="/WEB-INF/views/header.jsp"%>
<div class="container all-container employees-view-container">
    <div class="functions-buttons-container">
        <div class="button-container container-with-button">
            <input type="text" class="search-input" placeholder="Client Name"/>
            <button class="button">Search</button>
        </div>
        <div class="buttons-container">
            <div class="buttons-container">
                <div class="button-container">
                    <label class="sort-label" for="sort-by-list">Sort by</label>
                    <select id="sort-by-list">
                        <option value="name">Name</option>
                        <option value="registrationDate">Registration Date</option>
                        <option value="ordersAmount">Orders Amount</option>
                    </select>
                    <label class="sort-label" for="sort-checkbox">descending</label>
                    <input type="checkbox" id="sort-checkbox"/>
                </div>
            </div>
            <div class="buttons-container">
                <button class="button" onclick="location.href='${pageContext.request.contextPath}/controller/clients/add';">Add new Client</button>
                <button class="button">Create Report</button>
            </div>
        </div>
    </div>
    <div class="cards-container clients-container">
        <c:forEach items="${clients}" var="client">
            <div class="card client-card">
                <div class="card-name-container">
                    <span class="card-name">Client Name :
                        <span class="card-name card-name-value">${client.getName()}</span>
                    </span>
                </div>
                <div class="card-email-container">
                    <span class="card-email">Email :
                        <span class="card-email card-email-value">${client.getEmail()}</span>
                    </span>
                </div>
                <div class="card-phone-container">
                    <span class="card-phone">Phone Number :
                        <span class="card-phone card-phone-value">${client.getPhone()}</span>
                    </span>
                </div>
                <div class="card-address-container">
                    <span class="card-address">Address:
                        <span class="card-address card-address-value">${client.getAddress()}</span>
                    </span>
                </div>
                <div class="card-buttons-container">
                    <button class="button card-button" onclick="location.href='${pageContext.request.contextPath}/controller/clients/delete?clientId=${client.getId()}';">Delete Client</button>
                    <button class="button card-button" onclick="location.href='${pageContext.request.contextPath}/controller/clients/update?clientId=${client.getId()}';">Edit Client</button>
                </div>
            </div>
        </c:forEach>
    </div>
</div>
<%@include file="/WEB-INF/views/footer.jsp"%>