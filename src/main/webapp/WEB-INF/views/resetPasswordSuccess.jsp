<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="/css/resetPasswordSuccess.css"/>
    <title>Reset Password Success</title>
</head>
<body>
<c:if test="${not empty message}">
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
