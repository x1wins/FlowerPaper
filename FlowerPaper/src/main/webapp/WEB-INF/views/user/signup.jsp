<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<div class="aboutUs">
	<h1>Sign Up</h1>


	<form:form method="post" action="signup" commandName="user" enctype="multipart/form-data">
		<table>
			<tr>
				<td width="20%"><spring:message code="signup.userid"/> : </td><td width="20%"> <form:input path="userid" /> </td><td> <font color="red"><form:errors path="userid" /></font></td>
			</tr>
			<tr>
				<td><spring:message code="signup.name"/> : </td><td> <form:input path="name" /> </td><td>  <font color="red"><form:errors path="name" /></font></td>
			</tr>
			<tr>
				<td><spring:message code="signup.password"/> : </td><td> <form:input path="password" /> </td><td>  <font color="red"><form:errors path="password" /></font></td>
			</tr>
			<tr>
				<td><spring:message code="signup.phone"/> : </td><td> <form:input path="phone" /> </td><td>  <font color="red"><form:errors path="phone" /></font></td>
			</tr>
			<tr>
				<td><spring:message code="signup.email"/> : </td><td> <form:input path="email" /> </td><td>  <font color="red"><form:errors path="email" /></font></td>
			</tr>
			<tr>
				<td><spring:message code="signup.picture"/> : </td><td> <input type="file" name="file" id="file"></input> </td><td> </td>
<%-- 				<td>image file : </td><td> <form:input path="image" type="file"/></td><td><font color="red"><form:errors path="image" /></font></td> --%>
<%-- 				<td>image file : </td><td> <form:input path="file" type="file"/></td><td><font color="red"><form:errors path="file" /></font></td> --%>
			</tr>
			<tr>
				<td> </td><td><input type="submit" value="Submit" /> </td><td> </td>
			</tr>
		</table>
	</form:form>
</div>

<script>
	document.getElementById("userid").focus();
</script>

