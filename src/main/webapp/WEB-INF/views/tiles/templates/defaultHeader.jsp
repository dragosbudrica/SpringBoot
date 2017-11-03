<%@ page isELIgnored="false" %>
<%@ page session="true" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<c:set var="firstName" value="${sessionScope.user.firstName}"/>
<c:set var="role" value="${sessionScope.user.role.roleName}"/>
<c:if test="${not empty sessionScope.user}">
    <div class="topnav" id="myTopnav">
        <c:if test="${role eq 'Admin'}">
            <a href="/registerProfessor"><spring:message
                    code="global.register"/></a>
            <a href="/courseScheduling"><spring:message
                    code="navbar.scheduling"/></a>
            <a href="/validation"><spring:message
                    code="navbar.validation"/></a>
            <a href="javascript:void(0);" class="icon" onclick="myFunction()">&#9776;</a>
        </c:if>
        <c:if test="${role eq 'Professor'}">
            <a href="/professorCourses"><spring:message
                    code="global.myCourses"/></a>
            <a href="/newCourse"><spring:message
                    code="navbar.newCourse"/></a>
            <a href="/timetable"><spring:message
                    code="navbar.timetable"/></a>
            <a href="/grades"><spring:message
                    code="navbar.grades"/></a>
            <a href="javascript:void(0);" class="icon" onclick="myFunction()">&#9776;</a>
        </c:if>
        <c:if test="${role eq 'Student'}">
            <a href="/allCourses"><spring:message
                    code="global.allCourses"/></a>
            <a href="/studentCourses"><spring:message
                    code="global.myCourses"/></a>
            <a href="/timetable"><spring:message
                    code="navbar.timetable"/></a>
            <a href="/myGrades"><spring:message
                    code="navbar.myGrades"/></a>
            <a href="javascript:void(0);" class="icon" onclick="myFunction()">&#9776;</a>
        </c:if>
    </div>
    <form action="logout" method="post">
        <h4 id="welcomeMsg"><spring:message code="welcome.message" arguments="${firstName}"/></h4>
        <button id=logout>Logout</button>
    </form>
</c:if>
