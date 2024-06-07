<%@include file="/WEB-INF/views/header.jsp"%>
<div class="container add-container add-order-view-container">
    <h1>Add Order</h1>
    <form class="add-form" action="./add" method="POST">
        <c:if test="${not empty requestScope.errors}">
            <div class="alert alert-danger">
                <c:forEach items="${requestScope.errors}" var="error">
                    <p>${error}</p>
                </c:forEach>
            </div>
        </c:if>
        <div class="form-group">
            <div class="input-container">
                <label class="input-label" for="name-input">Order Name</label>
                <input type="text" class="add-form-input" placeholder="Order Name" id="name-input" name="name"
                       value="<c:out value="${requestScope.orderDto.getName()}" />"/>
            </div>
            <div class="input-container">
                <label class="input-label" for="description-input">Description</label>
                <input type="text" class="add-form-input" placeholder="Order Description" id="description-input" name="description"
                       value="<c:out value="${requestScope.orderDto.getDescription()}" />"/>
            </div>
            <div class="input-container">
                <label class="input-label" for="cost-input">Cost</label>
                <input type="number" class="add-form-input" placeholder="Cost" id="cost-input" name="cost"
                       value="<c:out value="${requestScope.orderDto.getCost()}" />"/>
            </div>
            <div class="input-container">
                <label class="input-label" for="due-date-input">Due Date</label>
                <input type="date" class="add-form-input" id="due-date-input" name="dueDate"
                       value="<c:out value="${requestScope.orderDto.getDueDate()}" />"/>
            </div>
        </div>
        <div class="submit-button-container">
            <button type="submit" class="button submit-button">
                Add Order
            </button>
        </div>
    </form>
</div>
<%@include file="/WEB-INF/views/footer.jsp"%>
