<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>	

<div class="aboutUs">
	<h1>Sign In</h1>


	<form:form method="post" action="signin" commandName="signin">
		<table>
			<tr>
				<td width="20%"><spring:message code="signin.userid"/> : </td><td width="20%"> <form:input path="userid" /> </td><td> <font color="red"><form:errors path="userid" /></font></td>
			</tr>
			<tr>
				<td><spring:message code="signin.password"/> : </td><td> <form:input path="password" /> </td><td>  <font color="red"><form:errors path="password" /></font></td>
			</tr>
			<tr>
				<td> </td><td><input type="submit" value="Submit" /> </td><td> </td>
			</tr>
		</table>
		<input type="hidden" name="currentUrl" value="${currentUrl}"/>
	</form:form>
</div>

<script>
	document.getElementById("userid").focus();
</script>

