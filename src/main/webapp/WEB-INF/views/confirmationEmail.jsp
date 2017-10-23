<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="message" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link rel="stylesheet" href="/css/registrationConfirmation.css"/>
    <title>Confirmation Email</title>
</head>
<body>
<c:if test="${not empty message}">
    <div class="confirm-title">
        <h1><spring:message code="confirmation.email"/></h1>
    </div>
    <div id="success">
        <img alt="" src="/resources/images/rsz_success-icon.png"/>
        <h2>${message}</h2>
    </div>
    <h5><spring:message code="global.backToLogin"/></h5>
</c:if>

<c:if test="${empty message}">
    <div id="warning">
        <img alt="" src="/resources/images/rsz_icon-warning-png-11.png"/>
        <h2><spring:message code="global.invalidRequest" /></h2>
    </div>
    <h5><spring:message code="global.backToLogin"/></h5>
</c:if>
</body>
</html>
