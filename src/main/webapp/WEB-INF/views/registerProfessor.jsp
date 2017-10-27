<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="role" value="${sessionScope.user.role.roleName}"/>
<%--
  Created by IntelliJ IDEA.
  User: Dragos
  Date: 28.07.2017
  Time: 17:26
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java"
         contentType="text/html; charset=windows-1256"
         pageEncoding="windows-1256"
%>
<%@ page session="true" %>
<script>
    var role = '${role}';
</script>
<!DOCTYPE html>

<html>
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="/css/register.css"/>
    <script type="text/javascript" src="https://code.jquery.com/jquery-3.2.1.min.js"></script>
    <script type="text/javascript" src="/js/register.js"></script>
</head>
<body>
<div class="module form-module">
    <div class="toggle">
    </div>
    <div class="form">
        <h2><spring:message code="newAccount.message"/></h2>
        <form id="newAccount">
            <!--First Name-->
            <div class="input_with_error">
                <label for="firstName"><spring:message code="global.firstName"/></label>
                <input type="text" id="firstName" name="firstName"/>
                <span class="error1"><spring:message code="global.required"/></span>
            </div>

            <!--Last Name-->
            <div class="input_with_error">
                <label for="lastName"><spring:message code="global.lastName"/></label>
                <input type="text" id="lastName" name="lastName"/>
                <span class="error1"><spring:message code="global.required"/></span>
            </div>

            <!--SSN-->
            <div class="input_with_error">
                <label for="ssn"><spring:message code="newAccount.ssn"/></label>
                <input type="text" id="ssn" name="ssn"/>
                <span class="error1"><spring:message code="global.required"/></span>
                <span class="error2"><spring:message code="global.invalidSsn"/></span>
            </div>

            <!--Email-->
            <div class="input_with_error">
                <label for="email">Email</label>
                <input type="text" id="email" name="email"/>
                <span class="error1"><spring:message code="global.required"/></span>
                <span class="error1"><spring:message code="global.invalidEmail"/></span>
            </div>

            <!--Password-->
            <div class="input_with_error">
                <label for="password"><spring:message code="global.password"/></label>
                <input type="password" id="password" name="password"/>
                <span class="error1"><spring:message code="global.required"/></span>
            </div>

            <!--Role-->
            <label for="role"><spring:message code="newAccount.role"/></label>
            <select id="role" name="role" disabled>
                <option value="Professor"><spring:message code="global.professor"/></option>
            </select>
            <button type="button" id="submit"><spring:message code="newAccount.button"/></button>
        </form>
    </div>
    <div id="message"></div>
</div>
</body>
</html>

