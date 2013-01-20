<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<script src="<c:url value="/js/jquery-1.7.2.min.js"/>"></script>
<script src="<c:url value="/js/bbs.js"/>"></script>
	
<input type="button" value="<spring:message code="bbs.list"/>" class="buttonStyle"
	onClick="location.href='<c:url value='/bbs/'/>${bbsnum}/list/1'" />	
<div class="aboutUs">

	<form:form method="post" commandName="bbs" enctype="multipart/form-data">
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
				<td><input type="submit" value="Submit" /> </td>
			</tr>
		</table>
		
		<div id="file">
			<spring:message code="bbs.file"/> :<br/>
			<p id="file1">
				<input type="file" name="file" id="file"></input>
				<input type="button" value="<spring:message code="bbs.add"/>" class="buttonStyle"
	onClick="addFileNode()" />	
			</p>
		</div>	
		
		<input type="hidden" name="bbsnum" id="bbsnum" value="${bbsnum }"></input>
		<form:hidden path="num" />
	</form:form>
	
	<c:forEach var="file" items="${filelist}">
		<p id="<c:out value="${file.num}" />">
			<c:choose>
				<c:when test="${file.filetype == 'image'}">
					<img src="<c:out value="${file.url}" />" />
				</c:when>
				<c:otherwise>
					첨부 파일 : <a href="<c:out value="${file.url}" />"><c:out value="${file.filename}" /></a>
				</c:otherwise>
			</c:choose>
			<input type="button" value="<spring:message code="bbs.delete"/>" class="buttonStyle" onClick="javascript:deleteFile('<c:url value="/"/>','<c:out value="${file.num}" />', '<spring:message code="file.delete"/>')" />
			<br />
			<br />
		</p>
	</c:forEach>
	
</div>

<script>
	document.getElementById("subject").focus();
</script>

<!-- This clearing element should immediately follow the #mainContent div in order to force the #container div to contain all child floats -->
<br class="clearfloat" />
