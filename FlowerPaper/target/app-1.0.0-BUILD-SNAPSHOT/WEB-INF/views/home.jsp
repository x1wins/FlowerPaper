<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<title>Home</title>
</head>
<body>
<h1>
	Hello world!  
</h1>
<!-- classpath:net/changwoo/app/properties/skin.properties -->
<%-- <fmt:setBundle basename="classpath:net/changwoo/app/properties/skin.properties"/> --%>
<%-- <fmt:bundle basename="net.changwoo.x1wins.properties.skin"/> --%>
<%-- <fmt:setBundle basename="net.changwoo.x1wins.properties.skin" />  --%>
<%-- <fmt:setBundle basename="classpath:net/changwoo/app/properties/skin.properties" />  --%>
<%-- <fmt:setBundle basename="net.changwoo.x1wins.properties.skin"/> --%>
<fmt:setBundle basename="skin" /> 
<fmt:message key="skin.name"/>

<%-- <spring:message code="skin.name" /> --%>

<P>  The time on the server is ${serverTime}. </P>
</body>
</html>
