<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>


<div class="aboutUs">
	<h1><c:out value="${detail.num}"/> <c:out value="${detail.subject}"/></h1>
	<p>
		<img src="<c:url value="/file/image"/>/portfolio/1/<c:out value="${detail.num}"/>" class="picLeft" alt="" />
	</p>
	<br />
	<p>
		<c:out value="${detail.content}"/>
	</p>
	<div class="clearfloat"></div>
</div>
<div class="clearfloat"></div>
