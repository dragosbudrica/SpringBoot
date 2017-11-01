<%@ page isELIgnored="false" %>
<%@ page session="true" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<c:set var="firstName" value="${sessionScope.user.firstName}" />
<c:if test="${not empty sessionScope.user}">
    <form action="logout" method="post">
        <h2><spring:message code="welcome.message" arguments="${firstName}" /> </h2>
       <%-- <button id=logout><img src="/resources/images/rsz_56805.png"></button>--%>
        <button id=logout>Logout</button>
    </form>
</c:if>
