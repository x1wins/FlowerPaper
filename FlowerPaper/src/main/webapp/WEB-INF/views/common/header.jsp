<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>	
<!-- begin #header -->
<div id="header">
	<div class="logoContainer">
<!-- 		<div> -->
<%-- 			<img src="<c:url value="/images/logo.png"/>" alt="" /> --%>
<!-- 		</div> -->
		<h3><spring:message code="header.logo"/></h3>
		<div class="slogan"><spring:message code="header.slogo"/></div>
	</div>
	<div class="icons">
		<ul>
			<li><c:out value="${sessionScope.userid}" /></li>
			<li><a href="<c:url value="/index?lang=en"/>"><img src="http://images.apple.com/global/elements/flags/30x30/usa.png" alt="USA" /></a></li>
			<li><a href="<c:url value="/index?lang=ko"/>"><img src="http://images.apple.com/global/elements/flags/30x30/south_korea.png" alt="KOREA" /></a></li>
			<li><a href=""><img src="<c:url value="/images/delicious.png"/>" alt="" /></a></li>
			<li><a href=""><img src="<c:url value="/images/facebook.png"/>" alt="" /></a></li>
			<li><a href=""><img src="<c:url value="/images/feed.png"/>" alt="" /></a></li>
			<li><a href=""><img src="<c:url value="/images/flickr.png"/>" alt="" /></a></li>
			<li><a href=""><img src="<c:url value="/images/linkedin.png"/>" alt="" /></a></li>
			<li><a href=""><img src="<c:url value="/images/myspace.png"/>" alt="" /></a></li>
			<li><a href=""><img src="<c:url value="/images/stumble.png"/>" alt="" /></a></li>
		</ul>
	</div>
	<div class="clearfloat"></div>
	<div class="menu">
		<ul>
			
			<li <c:if test="${menu=='index'}">id="active"</c:if> ><a href="<c:url value="/index"/>"><spring:message code="home"></spring:message></a></li>
			<li <c:if test="${menu=='bbs'}">id="active"</c:if> ><a href="<c:url value="/bbs/1/list/1"/>"><spring:message code="bbs"/></a></li>
			
			<c:choose>
				<c:when test="${not empty sessionScope.userid}">
				<li <c:if test="${menu=='signout'}">id="active"</c:if> ><a href="<c:url value="/user/signout"/>?currentUrl=${currentUrl}"><spring:message code="sign_out"/></a></li>
				</c:when>
				<c:otherwise>
				<li <c:if test="${menu=='signup'}">id="active"</c:if> ><a href="<c:url value="/user/signup"/>?currentUrl=${currentUrl}"><spring:message code="sign_up"/></a></li>
				<li <c:if test="${menu=='signin'}">id="active"</c:if> ><a href="<c:url value="/user/signin"/>?currentUrl=${currentUrl}"><spring:message code="sign_in"/></a></li>
				</c:otherwise>
				
			</c:choose>
			
			<li <c:if test="${menu=='about'}">id="active"</c:if> ><a href="<c:url value="/about"/>"><spring:message code="about_us"/></a></li>
			<li <c:if test="${menu=='contact'}">id="active"</c:if> ><a href="<c:url value="/contact"/>"><spring:message code="contact"/></a></li>
			<li <c:if test="${menu=='location'}">id="active"</c:if> ><a href="<c:url value="/location"/>"><spring:message code="location"/></a></li>
		</ul>
	</div>
	<div class="headerPicContainer">
<!-- 		<div class="headerPic"> -->
<%-- 			<img src="<c:url value="/images/headerPic.jpg"/>" alt="" /> --%>
<!-- 		</div> -->
		<div class="headerPicBody">
			<div class="headerText1">
				<spring:message code="header.title" />
			</div>
			<div class="headerText2">
				<spring:message code="header.subtitle" />
			</div>
		</div>
		<div class="clearfloat"></div>
	</div>
</div>
<!-- end #header -->