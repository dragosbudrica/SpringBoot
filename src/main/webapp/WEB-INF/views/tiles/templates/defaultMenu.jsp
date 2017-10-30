<%@ page isELIgnored="false" %>
<%@ page session="true" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<link rel="stylesheet" href="/css/pure-min.css">

<!--[if lte IE 8]>
<link rel="stylesheet" href="/combo/1.18.13?/css/layouts/side-menu-old-ie.css">
<![endif]-->
<!--[if gt IE 8]><!-->
<link rel="stylesheet" href="/css/menu.css"/>
<!--<![endif]-->
<!--[if lt IE 9]>
<script src="http://cdnjs.cloudflare.com/ajax/libs/html5shiv/3.7/html5shiv.js"></script>
<![endif]-->
<script>
    (function(i,s,o,g,r,a,m){i['GoogleAnalyticsObject']=r;i[r]=i[r]||function(){
        (i[r].q=i[r].q||[]).push(arguments)},i[r].l=1*new Date();a=s.createElement(o),
        m=s.getElementsByTagName(o)[0];a.async=1;a.src=g;m.parentNode.insertBefore(a,m)
    })(window,document,'script','//www.google-analytics.com/analytics.js','ga');

    ga('create', 'UA-41480445-1', 'purecss.io');
    ga('send', 'pageview');
</script>

<c:set var="role" value="${sessionScope.user.role.roleName}"/>
<c:if test="${not empty sessionScope.user}">
    <!-- Menu toggle -->
    <a href="#menu" id="menuLink" class="menu-link">
        <!-- Hamburger icon -->
        <span></span>
    </a>
    <div id="menu" class="">
        <div class="pure-menu">
            <a class="pure-menu-heading" href="#">E-LEARNING</a>
            <ul class="pure-menu-list">
                <c:if test="${role eq 'Admin'}">
                    <li class="pure-menu-item"><a href="/registerProfessor" class="pure-menu-link"><spring:message
                            code="global.register"/></a></li>
                    <li class="pure-menu-item"><a href="/courseScheduling" class="pure-menu-link"><spring:message
                            code="navbar.scheduling"/></a></li>
                    <li class="pure-menu-item"><a href="/validation" class="pure-menu-link"><spring:message
                            code="navbar.validation"/></a></li>
                </c:if>
                <c:if test="${role eq 'Professor'}">
                    <li class="pure-menu-item"><a href="/professorCourses" class="pure-menu-link"><spring:message
                            code="global.myCourses"/></a></li>
                    <li class="pure-menu-item"><a href="/newCourse" class="pure-menu-link"><spring:message
                            code="navbar.newCourse"/></a></li>
                    <li class="pure-menu-item"><a href="/timetable" class="pure-menu-link"><spring:message
                            code="navbar.timetable"/></a></li>
                    <li class="pure-menu-item"><a href="/grades" class="pure-menu-link"><spring:message
                            code="navbar.grades"/></a></li>
                </c:if>

                <c:if test="${role eq 'Student'}">
                    <li class="pure-menu-item"><a href="/allCourses" class="pure-menu-link"><spring:message
                            code="global.allCourses"/></a></li>
                    <li class="pure-menu-item"><a href="/studentCourses" class="pure-menu-link"><spring:message
                            code="global.myCourses"/></a></li>
                    <li class="pure-menu-item"><a href="/timetable" class="pure-menu-link"><spring:message
                            code="navbar.timetable"/></a></li>
                    <li class="pure-menu-item"><a href="/myGrades" class="pure-menu-link"><spring:message
                            code="navbar.myGrades"/></a></li>
                </c:if>
            </ul>
        </div>
    </div>
    <script type="text/javascript" src="/js/menu.js" charset="UTF-8"></script>
</c:if>