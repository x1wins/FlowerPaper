<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>



<div class="aboutUs">
	<h1>Sign In Success</h1>
	<p>

		<table>
			<tr>
				<td width="20%">userid : </td><td width="20%"> <c:out value="${signin.userid}"/></td><td></td>
			</tr>
			
			<tr>
				<td>password : </td><td> <c:out value="${signin.password}"/></td><td></td>
			</tr>
			
		</table>

</div>
