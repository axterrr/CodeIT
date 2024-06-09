<%@include file="/WEB-INF/views/header.jsp"%>
<div class="container full-info-container full-info-task-container">
    <div class="container full-container full-task-container">
        <div class="info-container task-info-container">
            <div class="task-status-icon">
                <img src="img/status.png" alt=""/>
            </div>
            <div class="task-name-container">
                <label class="full-task-label task-name task-name-value">${task.getName()}</label>
            </div>
            <div class="task-status-container">
                <label class="full-task-label task-status full-task-info-label">Task Status:</label>
                <label class="full-task-label task-status task-status-value">${task.getStatus().getValue()}</label>
            </div>
            <div class="task-project-container">
                <label class="full-task-label task-project full-task-info-label">Project:</label>
                <a href="${pageContext.request.contextPath}/controller/projects/project?projectId=${task.getProject().getId()}">
                    <label class="a-label full-task-label task-project task-project-value">${task.getProject().getName()}</label>
                </a>
            </div>
            <div class="task-developer-container">
                <label class="full-task-label task-developer full-task-info-label">Developer:</label>
                <c:if test="${not empty task.getDeveloper()}">
                    <a href="${pageContext.request.contextPath}/controller/employees/employee?employeeId=${task.getDeveloper().getId()}">
                        <label class="a-label full-task-label task-developer task-developer-value">${task.getDeveloper().getFirstName()} ${task.getDeveloper().getLastName()}</label>
                    </a>
                </c:if>
            </div>
            <div class="task-tester-container">
                <label class="full-task-label task-tester full-task-info-label">Tester:</label>
                <c:if test="${not empty task.getTester()}">
                    <a href="${pageContext.request.contextPath}/controller/employees/employee?employeeId=${task.getTester().getId()}">
                        <label class="a-label full-task-label task-tester task-tester-value">${task.getTester().getFirstName()} ${task.getTester().getLastName()}</label>
                    </a>
                </c:if>
            </div>
            <div class="task-description-container">
                <label class="full-task-label task-description full-task-info-label">Description:</label>
                <label class="full-task-label task-description task-description-value">${task.getDescription()}</label>
            </div>
            <div class="task-link-container">
                <label class="full-task-label task-link full-task-info-label">Task Link:</label>
                <a href="${task.getBranchLink()}">
                    <label class="a-label full-task-label task-link task-link-value">${task.getBranchLink()}</label>
                </a>
            </div>
            <div class="task-dates-container">
                <div class="task-date-container task-start-date-container">
                    <label class="full-task-label task-start-date full-task-info-label">Start date:</label>
                    <label class="full-task-label task-start-date task-start-date-value">${task.getStartDateString()}</label>
                </div>
                <div class="task-date-container task-due-date-container">
                    <label class="full-task-label task-due-date full-task-info-label">Due date:</label>
                    <label class="full-task-label task-due-date task-due-date-value">${task.getDueDateString()}</label>
                </div>
                <c:if test="${not empty task.getEndDate()}">
                    <div class="task-date-container task-end-date-container">
                        <label class="full-task-label task-end-date full-task-info-label">End date:</label>
                        <label class="full-task-label task-end-date task-end-date-value">${task.getEndDateString()}</label>
                    </div>
                </c:if>
            </div>
        </div>
    </div>
    <div class="container fixed-container task-fixed-container">
        <div class="task-comment-container">
            <div class="task-comment-header">
                <label class="full-project-label project-tasks" for="comment">Comment:</label>
                <button id="editCommentButton" class="button">Edit Comment</button>
            </div>
            <div class="task-comment-value-container">
                <textarea readonly class="full-task-label task-comment task-comment-value" id="comment">${task.getComment()}</textarea>
            </div>
            <div class="comment-buttons-container">
                <button id="saveCommentButton" class="button" style="display:none;">Save Comment</button>
            </div>
        </div>
        <div class="task-buttons-container">
            <div class="task-buttons-container tb-part tb-part1">
                <button class="button" onclick="location.href='${pageContext.request.contextPath}/controller/tasks/submit?taskId=${task.getId()}';">Submit Task</button>
                <button class="button" onclick="location.href='${pageContext.request.contextPath}/controller/tasks/confirmTest?taskId=${task.getId()}';">Test Passed</button>
                <button class="button" onclick="location.href='${pageContext.request.contextPath}/controller/tasks/reject?taskId=${task.getId()}';">Test Failed</button>
            </div>
            <div class="task-buttons-container tb-part tb-part3">
                <button class="button" onclick="location.href='${pageContext.request.contextPath}/controller/tasks/confirm?taskId=${task.getId()}';">Confirm Task</button>
                <button class="button" onclick="location.href='${pageContext.request.contextPath}/controller/tasks/reject?taskId=${task.getId()}';">Reject Task</button>
            </div>
            <div class="task-buttons-container tb-part tb-part2">
                <button class="button" onclick="location.href='${pageContext.request.contextPath}/controller/tasks/delete?taskId=${task.getId()}';">Delete Task</button>
                <button class="button" onclick="location.href='${pageContext.request.contextPath}/controller/tasks/update?taskId=${task.getId()}';">Edit Task</button>
            </div>
        </div>
    </div>
</div>

<script>
    const editButton = document.getElementById('editCommentButton');
    const saveButton = document.getElementById('saveCommentButton');
    const textarea = document.getElementById('comment');

    editButton.addEventListener('click', () => {
        if (textarea.hasAttribute('readonly')) {
            textarea.removeAttribute('readonly');
            editButton.textContent = 'Cancel';
            saveButton.style.display = 'inline-block';
        } else {
            textarea.setAttribute('readonly', 'readonly');
            editButton.textContent = 'Edit Comment';
            saveButton.style.display = 'none';
            textarea.value = textarea.defaultValue;
        }
    });

    saveButton.addEventListener('click', () => {
        textarea.setAttribute('readonly', 'readonly');
        editButton.textContent = 'Edit Comment';
        saveButton.style.display = 'none';
        textarea.defaultValue = textarea.value;
    });
</script>

<%@include file="/WEB-INF/views/footer.jsp"%>
