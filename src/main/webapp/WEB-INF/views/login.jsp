<%@include file="/WEB-INF/views/header.jsp"%>
<div class="container log-in-view-container">
    <h1>Authorization</h1>
    <form class="login-form" action="./login" method="POST" role="form">
        <div class="form-group">
            <div class="input-container">
                <label class="input-label" for="email-input">Email</label>
                <input type="email" class="login-form-input" placeholder="Email" id="email-input" name="email"
                       value="<c:out value="${requestScope.loginUser.getEmail()}" />"/>
            </div>
            <div class="input-container">
                <label class="input-label" for="password-input">Password</label>
                <input type="password" class="login-form-input" id="password-input" placeholder="Password" name="password"/>
            </div>
        </div>
        <div class="submit-button-container">
            <button type="submit" class="button submit-button">
                Log In
            </button>
        </div>
    </form>
</div>
<%@include file="/WEB-INF/views/footer.jsp"%>
