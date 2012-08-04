<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>



<div class="aboutUs">
	<h1>Sign Up Success</h1>
	<p>

		<table>
			<tr>
				<td width="20%">userid : </td><td width="20%"> <c:out value="${user.userid}"/></td><td></td>
			</tr>
			
			<tr>
				<td>name : </td><td> <c:out value="${user.name}"/></td><td></td>
			</tr>
			<tr>
				<td>password : </td><td> <c:out value="${user.password}"/></td><td></td>
			</tr>
			<tr>
				<td>phone : </td><td> <c:out value="${user.phone}"/></td><td></td>
			</tr>
			<tr>
				<td>email : </td><td> <c:out value="${user.email}"/></td><td></td>
			</tr>

			
			
		</table>

</div>


<script>
	document.getElementById("userid").focus();
</script>

