<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<jsp:useBean id="currencies" class="com.epam.rd.jsp.currencies.CurrenciesOfCurrentTestCase" scope="request"/>

<!DOCTYPE html>
<html>
<head>
    <title>convert</title>
    <link href="Style.css" rel="stylesheet" type="text/css">
</head>
<body>
<c:set var="from" value="from" scope="request"/>
<c:set var="to" value="to" scope="request"/>
<c:set var="amount" value="amount" scope="request"/>
<c:set var="sourceCurrency" value="${param.get(from)}" scope="request"/>
<c:set var="targetCurrency" value="${param.get(to)}" scope="request"/>
<c:set var="amountVal" value="${param.get(amount)}" scope="request"/>
<c:set var="resultExchange" value="${currencies.convert(amountVal, sourceCurrency, targetCurrency)}"/>

<h1>Converting ${sourceCurrency} to ${targetCurrency}</h1>
<p>Amount ${sourceCurrency} = ${amountVal}</p>
<p>${targetCurrency} result value = ${resultExchange}</p>
</body>
</html>