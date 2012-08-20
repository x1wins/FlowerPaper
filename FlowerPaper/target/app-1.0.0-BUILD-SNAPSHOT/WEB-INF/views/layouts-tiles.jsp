<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
 
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>
	
	<c:choose>
		<c:when test="${not empty detail.subject }">
			<c:out value="${detail.subject}"/>
		</c:when>
		<c:otherwise>
			Changwoo.net
		</c:otherwise>
	</c:choose>
	
</title>
<link rel="stylesheet" href="<c:url value="/styles.css" />" type="text/css" />
</head>
 
<body>
<div id="container">
    <tiles:insertAttribute name="header" />
    <tiles:insertAttribute name="content" />
    <tiles:insertAttribute name="sidebar" />
    <tiles:insertAttribute name="footer" />
</div>
</body>
</html>