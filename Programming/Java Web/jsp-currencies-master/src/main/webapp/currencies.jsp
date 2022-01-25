<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<jsp:useBean id="currencies" class="com.epam.rd.jsp.currencies.CurrenciesOfCurrentTestCase" scope="request"/>

<!DOCTYPE html>
<html>
<head>
    <title>currencies</title>
    <link href="Style.css" rel="stylesheet" type="text/css">
</head>
<body>
<h1>Currencies</h1>
<ul>
    <c:forEach var="currency" items="${currencies.currencies}">
        <li><c:out value="${currency}"/></li>
    </c:forEach>
</ul>
</body>
</html>