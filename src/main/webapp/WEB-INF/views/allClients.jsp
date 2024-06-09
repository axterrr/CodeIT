<%@include file="/WEB-INF/views/header.jsp"%>
<div class="container all-container employees-view-container">
    <div class="functions-buttons-container">
        <form action="${pageContext.request.contextPath}/controller/clients" method="GET" role="form">
            <div class="button-container container-with-button">
                <input type="text" class="search-input" placeholder="Client Name" name="name" value="${requestScope.name}"/>
                <button type="submit" class="button">Search</button>
            </div>
        </form>
        <div class="buttons-container">
            <div class="buttons-container">
                <form action="${pageContext.request.contextPath}/controller/clients" method="GET" role="form">
                    <div class="button-container">
                        <label class="sort-label" for="sort-by-list">Sort by</label>
                        <select id="sort-by-list" name="sortBy" onchange="this.closest('form').submit();">
                            <option value="name" <c:if test="${requestScope.sortBy == 'name'}">selected</c:if>>Name</option>
                            <option value="registrationDate" <c:if test="${requestScope.sortBy == 'registrationDate'}">selected</c:if>>Registration Date</option>
                            <option value="ordersAmount" <c:if test="${requestScope.sortBy == 'ordersAmount'}">selected</c:if>>Orders Amount</option>
                        </select>
                        <label class="sort-label" for="sort-checkbox">descending</label>
                        <input type="checkbox" id="sort-checkbox" name="descending" onchange="this.closest('form').submit();"
                               <c:if test="${requestScope.descending == 'on'}" >checked</c:if>/>
                    </div>
                </form>
            </div>
            <div class="buttons-container">
                <button class="button" onclick="location.href='${pageContext.request.contextPath}/controller/clients/add';">Add new Client</button>
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
    <div class="cards-container clients-container">
        <c:forEach items="${clients}" var="client">
            <div class="card client-card" onclick="location.href='${pageContext.request.contextPath}/controller/clients/client?clientId=${client.getId()}';">
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
                    <button class="button card-button" onclick="event.stopImmediatePropagation(); confirmDeletion('${pageContext.request.contextPath}/controller/clients/delete?clientId=${client.getId()}')">Delete Client</button>
                    <button class="button card-button" onclick="event.stopImmediatePropagation(); location.href='${pageContext.request.contextPath}/controller/clients/update?clientId=${client.getId()}';">Edit Client</button>
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
        iframeDoc.write('<h2 align="center">Clients</h2>');

        iframeDoc.write('<table>');
        iframeDoc.write('<tr><th>Name</th><th>Contact Person</th><th>Email</th><th>Phone</th><th>Address</th><th>Registration Date</th></tr>');
        <c:forEach items="${clients}" var="client">
        iframeDoc.write('<tr>');
        iframeDoc.write('<td>${client.getName()}</td>')
        iframeDoc.write('<td>${client.getPerson()}</td>');
        iframeDoc.write('<td>${client.getEmail()}</td>');
        iframeDoc.write('<td>${client.getPhone()}</td>');
        iframeDoc.write('<td>${client.getAddress()}</td>');
        iframeDoc.write('<td>${client.getRegistrationDateString()}</td>');
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
