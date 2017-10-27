<%@ page language="java"
         contentType="text/html; charset=windows-1256"
         pageEncoding="windows-1256"
%>
<%@ page session="true" %>
<html>
<head>
    <meta name="viewport" content="width=device-width,initial-scale=1.0">
    <link href='https://cdnjs.cloudflare.com/ajax/libs/fullcalendar/3.4.0/fullcalendar.min.css' rel='stylesheet'/>
    <link href='https://cdnjs.cloudflare.com/ajax/libs/fullcalendar/3.4.0/fullcalendar.print.css' rel='stylesheet'
          media='print'/>
    <link rel="stylesheet" href="/css/timetable.css"/>

    <script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/moment.js/2.18.1/moment.min.js"></script>
    <script type="text/javascript" src="https://code.jquery.com/jquery-3.2.1.min.js"></script>
    <script type="text/javascript"
            src="https://cdnjs.cloudflare.com/ajax/libs/fullcalendar/3.4.0/fullcalendar.min.js"></script>
    <script type="text/javascript" src="/js/timetable.js"></script>
</head>
<body>
<div id="content">
    <div id='calendar'></div>
  <%--  <div id='imageEn'><img src="/resources/images/rsz_under-construction-en.png" alt=""/></div>
    <div id='imageFr'><img src="/resources/images/rsz_under-construction-fr.png" alt=""/></div>
    <div id='imageRo'><img src="/resources/images/rsz_under-construction-ro.png" alt=""/></div>--%>
    <div id="warning">
        <img alt="" src="/resources/images/rsz_icon-warning-png-11.png"/>
        <h1></h1>
    </div>
</div>
</body>
</html>
