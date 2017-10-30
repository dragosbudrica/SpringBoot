<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>

<html>
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <title><tiles:getAsString name="title"/></title>
    <link rel="stylesheet" href="/css/layout.css"/>
    <link rel="shortcut icon" type="image/x-icon" href="/resources/images/favicon.ico"/>
</head>

<body>
<div id="layout" class="">

    <div id="menu">
        <tiles:insertAttribute name="menu"/>
    </div>

    <div id="container">
        <div id="header">
            <tiles:insertAttribute name="header"/>
        </div>

        <div id="content">
            <tiles:insertAttribute name="content"/>
        </div>

        <div id="footer">
            <tiles:insertAttribute name="footer"/>
        </div>
    </div>

</div>
</body>
</html>