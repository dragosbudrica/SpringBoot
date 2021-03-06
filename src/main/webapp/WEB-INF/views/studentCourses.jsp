<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ page language="java"
         contentType="text/html; charset=windows-1256"
         pageEncoding="windows-1256"
%>
<%@ page session="true" %>
<c:set var="role" value="${sessionScope.user.role.roleName}" />
<script>
    var role = '${role}';
</script>
<!DOCTYPE html>
<html>
<head>
    <meta name="viewport" content="width=device-width,initial-scale=1.0">
    <script type="text/javascript" src="https://code.jquery.com/jquery-3.2.1.min.js"></script>
    <link rel="stylesheet" href="/css/table.css"/>
    <link rel="stylesheet" href="/css/studentCourses.css"/>
    <script type="text/javascript" src="/js/common.js"></script>
    <script type="text/javascript" src="/js/lectures.js"></script>
    <script type="text/javascript" src="/js/studentCourses.js"></script>
</head>
<body>

<div id="warning">
    <img alt="" src="/resources/images/rsz_icon-warning-png-11.png"/>
    <h2></h2>
</div>
<div id="studentCourses">
    <h2><spring:message code="global.myCourses"/></h2>
    <table id="grid">
        <thead>
        <tr>
            <th><spring:message code="global.courseName"/></th>
            <th><spring:message code="global.category"/></th>
            <th><spring:message code="global.professor"/></th>
        </tr>
        </thead>
        <tfoot>
        <tr>
            <td colspan="3" id="paginationCourses"></td>
        </tr>
        </tfoot>
        <tbody id="tbodyCourses"></tbody>
    </table>
    <div id="message"></div>
</div>

<div id="courseDetails">
    <h3 id="courseName"></h3>
    <table id="courseDetailsGrid">
        <thead>
        <tr>
            <th><spring:message code="global.lectureName"/></th>
            <th>PDF</th>
        </tr>
        </thead>
        <tfoot>
        <tr>
            <td colspan="2" id="paginationLectures"></td>
        </tr>
        </tfoot>
        <tbody id="tbodyLectures"></tbody>
    </table>
</div>
</body>
</html>
