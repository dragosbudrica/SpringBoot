<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Dragos
  Date: 09.10.2017
  Time: 11:44
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="/css/registrationConfirmation.css"/>
    <title>Registration Confirmation</title>
</head>
<body>
<c:if test="${not empty message}">
    <div class="confirm-title">
        <h1><spring:message code="confirmation.title"/></h1>
    </div>
    <div id="success">
        <img alt="" src="/resources/images/rsz_success-icon.png"/>
        <h1>${message}</h1>
    </div>
    <h5><spring:message code="global.backToLogin"/></h5>
</c:if>

<c:if test="${empty message}">
    <div id="warning">
        <img alt="" src="/resources/images/rsz_icon-warning-png-11.png"/>
        <h1><spring:message code="global.invalidRequest" /></h1>
    </div>
    <h5><spring:message code="global.backToLogin"/></h5>
</c:if>
</body>
</html>
