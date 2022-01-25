<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<jsp:useBean id="currencies" class="com.epam.rd.jsp.currencies.CurrenciesOfCurrentTestCase" scope="request"/>

<!DOCTYPE html>
<html>
<head>
    <title>Exchange Rates</title>
    <link href="Style.css" rel="stylesheet" type="text/css">
</head>
<body>
<c:set var="from" value="from" scope="request"/>
<c:set var="referenceCurrency" value="${param.get(from)}" scope="request"/>
<c:set var="listCurrencies" value="${currencies.getExchangeRates(referenceCurrency)}" scope="request"/>

<h1>Exchange Rates for ${referenceCurrency}</h1>
<table>
    <tr>
        <td>currency</td>
        <td>value</td>
    </tr>
    <c:forEach var="currencyWithValue" items="${listCurrencies}">
        <c:if test="${!currencyWithValue.getKey().equals(referenceCurrency)}">
            <tr>
                <td>${currencyWithValue.getKey()}</td>
                <td>${currencyWithValue.getValue()}</td>
            </tr>
        </c:if>
    </c:forEach>
</table>
</body>
</html>