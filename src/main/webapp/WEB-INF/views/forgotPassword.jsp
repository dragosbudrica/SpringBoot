<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page language="java"
         contentType="text/html; charset=windows-1256"
         pageEncoding="windows-1256"
%>
<%@ page session="true" %>
<!DOCTYPE html>

<html>
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="/css/forgotPassword.css"/>
    <script type="text/javascript" src="https://code.jquery.com/jquery-3.2.1.min.js"></script>
    <script type="text/javascript" src="/js/common.js"></script>
    <script type="text/javascript" src="/js/forgotPassword.js"></script>
</head>
<body>
<div class="module form-module">
    <div class="toggle">
    </div>
    <div class="form">
        <h2><spring:message code="account.forgotPasswordTitle"/></h2>
        <form:form id="sendEmailForgotPassword" action="sendEmailForgotPassword" modelAttribute="forgotPasswordDto"  method="post">

            <h5><spring:message code="account.forgotPasswordMessage"/></h5>

            <!--Email-->
            <div class="input_with_error">
                <form:label path="email" for="email">Email</form:label>
                <form:input path="email" type="text" id="email" name="email"/>
                <span class="error1"><spring:message code="global.required"/></span>
                <span class="error2"><spring:message code="global.invalidEmail"/></span>
            </div>
            <button type="submit" onclick="return ForgotPassword.validate();" id="submit"><spring:message code="global.submit"/></button>
        </form:form>
        <h5 style="text-align: center"><spring:message code="global.backToLogin"/></h5>
    </div>
    <div id="message"></div>
</div>
</body>
</html>

