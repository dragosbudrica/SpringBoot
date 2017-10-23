<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page language="java"
         contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"
%>
<%@ page session="true" %>
<!DOCTYPE html>

<html>
<head>
    <link rel="stylesheet" href="/css/resetPassword.css"/>
    <script type="text/javascript" src="https://code.jquery.com/jquery-3.2.1.min.js"></script>
    <script type="text/javascript" src="/js/resetPassword.js"></script>
</head>
<body>
<c:if test="${not empty message}">
    <div id="warning">
        <img alt="" src="/resources/images/rsz_icon-warning-png-11.png"/>
        <h2>${message}</h2>
    </div>
    <h5><spring:message code="global.backToLogin"/></h5>
</c:if>

<c:if test="${empty message}">
    <div class="module form-module">
        <div class="toggle">
        </div>
        <div class="form">
            <h2><spring:message code="account.resetPasswordTitle"/></h2>
            <form:form id="sendNewPassword" action="sendNewPassword" modelAttribute="resetPasswordDto" method="post">

                <!--New Password-->
                <div class="input_with_error">
                    <form:label for="password" path="password"><spring:message code="resetPassword.new"/></form:label>
                    <form:input type="password" id="password" name="password" path="password"/>
                    <span class="error1"><spring:message code="global.required"/></span>
                    <span class="error2"><spring:message code="global.match"/></span>
                </div>

                <!--Confirm Password-->
                <div class="input_with_error">
                    <form:label for="confirmedPassword" path="confirmedPassword"><spring:message code="resetPassword.confirm"/></form:label>
                    <form:input type="password" id="confirmedPassword" name="confirmedPassword" path="confirmedPassword"/>
                    <span class="error1"><spring:message code="global.required"/></span>
                    <span class="error2"><spring:message code="global.match"/></span>
                </div>

                <form:input type="hidden" path="token" id="token"/>
                <button type="submit" onclick="return ResetPassword.validate();" id="submit"><spring:message code="global.submit"/></button>
            </form:form>
        </div>
        <div id="message"></div>
    </div>
</c:if>
</body>
</html>

