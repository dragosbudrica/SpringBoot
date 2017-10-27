<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page language="java"
         contentType="text/html; charset=windows-1256"
         pageEncoding="windows-1256"
%>
<%@ page session="true" %>
<%@ page isELIgnored="false" %>
<meta http-equiv="content-type" content="text/html;charset=utf-8" />
<!DOCTYPE html>

<html>
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="/css/register.css"/>
    <script type="text/javascript" src="https://code.jquery.com/jquery-3.2.1.min.js"></script>
    <script type="text/javascript" src="/js/common.js"></script>
    <script type="text/javascript" src="/js/register.js"></script>
</head>
<body>
<div class="module form-module">
    <div class="toggle">
    </div>
    <div class="form">
        <h2><spring:message code="newAccount.message"/></h2>
        <form:form id="addNewStudentAccount" modelAttribute="registerStudentDto" action="addNewStudentAccount" method="post">
            <!--First Name-->
        <div class="input_with_error">
            <form:label for="firstName" path="firstName"><spring:message code="global.firstName"/></form:label>
            <form:input type="text" id="firstName" name="firstName" path="firstName"/>
            <span class="error1"><spring:message code="global.required"/></span>
        </div>

            <!--Last Name-->
        <div class="input_with_error">
            <form:label for="lastName" path="lastName"><spring:message code="global.lastName"/></form:label>
            <form:input type="text" id="lastName" name="lastName" path="lastName"/>
            <span class="error1"><spring:message code="global.required"/></span>
        </div>

            <!--SSN-->
        <div class="input_with_error">
            <form:label for="ssn" path="ssn"><spring:message code="newAccount.ssn"/></form:label>
            <form:input type="text" id="ssn" name="ssn" path="ssn"/>
            <span class="error1"><spring:message code="global.required"/></span>
            <span class="error2"><spring:message code="global.invalidSsn"/></span>
        </div>

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

            <!--Role-->
            <form:label for="role" path="role"><spring:message code="newAccount.role"/></form:label>
            <form:input type="text" id="role" name="role" path="role" value="Student" readonly="true" title="You can create only Student account!"/>

            <form:button type="submit" onclick="return NewAccount.validate();"><spring:message code="newAccount.button"/></form:button>
        </form:form>
        <h5 style="text-align: center"><spring:message code="newAccount.alreadyAnAccount"/><a href="/login"><spring:message code="newAccount.login" /></a></h5>
    </div>
    <c:if test="${not empty message}">
        <div class="errors">${message}</div>
    </c:if>
</div>
</body>
</html>

