<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<meta http-equiv="content-type" content="text/html;charset=utf-8" />
<!DOCTYPE html>
<html>
<head>
    <link rel="stylesheet" href="/css/login.css"/>
    <script type="text/javascript" src="https://code.jquery.com/jquery-3.2.1.min.js"></script>
    <script type="text/javascript" src="/js/common.js"></script>
    <script type="text/javascript" src="/js/login.js"></script>
    <title>Login Page</title>
</head>

<body>
<div class="app-title">
    <h1>E-Learning App.</h1>
</div>
<!-- Form Module-->
<div class="module form-module">
    <div class="toggle">
    </div>
    <div class="form">
        <h2><spring:message code="login.message"/></h2>
        <form:form id="doLogin" modelAttribute="loginDto" action="doLogin" method="post">
            <!--Email-->
            <div class="input_with_error">
                <form:label for="email" path="email">Email</form:label>
                <form:input type="text" id="email" name="email" path="email"/>
                <span class="error1"><spring:message code="global.required"/></span>
                <span class="error2"><spring:message code="global.invalidEmail"/></span>
            </div>
            <!--Password-->
            <div class="input_with_error">
                <form:label for="password" path="password"><spring:message code="global.password"/></form:label>
                <form:input type="password" id="password" name="password" path="password"/>
                <span class="error1"><spring:message code="global.required"/></span>
            </div>
            <form:button type="submit" onclick="return Login.validate();" id="login" name="login"><spring:message code="login.button"/></form:button>
        </form:form>
        <a style="float:left" href="/registerStudent"><spring:message code="global.register"/></a>  <a style="float:right" href="/forgotPassword"><spring:message code="login.forgotPassword"/></a>
    </div>
    <c:if test="${not empty message}">
        <div class="errors">${message}</div>
    </c:if>
</div>
</body>
</html>



