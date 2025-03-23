<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>AIPO - User Management</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet">
    <link href="<c:url value='/resources/css/fStyle.css'/>" rel="stylesheet">
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"></script>
</head>
<body>
    <!-- Banner Section -->
    <div class="banner">
        <tiles:insertAttribute name="banner" />
    </div>

    <!-- Menu Section -->
    <div class="menu">
        <tiles:insertAttribute name="menu" />
    </div>

    <!-- Body Section -->
    <div class="body-content">
        <tiles:insertAttribute name="body" />
    </div>

    <!-- Footer Section -->
    <div class="footer">
        <tiles:insertAttribute name="footer" />
    </div>
</body>
</html> 