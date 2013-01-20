<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
	
	
<div class="aboutUs">

	<form:form method="post" action="form" commandName="bbs" enctype="multipart/form-data">
		<table>
			<tr>
				<td width="100%"><spring:message code="bbs.subject"/> <font color="red"><form:errors path="subject" /></font></td>
			</tr>
			<tr>
				<td> <form:input path="subject" /> </td>
			</tr>
			<tr>
				<td><spring:message code="bbs.content"/> <font color="red"><form:errors path="content" /></font></td>
			</tr>
			<tr>
				<td> <form:textarea class="ckeditor" path="content" /> </td>
			</tr>
			<tr>
				<td><spring:message code="bbs.file"/> : </td>
			</tr>
			
			<tr>
				<td><input type="file" name="file" id="file"></input></td>
			</tr>
			
			<tr>
				<td><input type="file" name="file" id="file"></input></td>
			</tr>
			<tr>
				<td><input type="submit" value="Submit" /> </td>
			</tr>
		</table>
		<input type="hidden" name="bbsnum" id="bbsnum" value="${bbsnum }"></input>
	</form:form>
</div>

<script>
	document.getElementById("subject").focus();
</script>

<!-- This clearing element should immediately follow the #mainContent div in order to force the #container div to contain all child floats -->
<br class="clearfloat" />
