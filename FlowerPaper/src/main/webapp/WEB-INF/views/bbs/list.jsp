<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<div class="memo">
<h3>bbs list</h3>
<c:choose>
	<c:when test="${not empty configs}">
		<c:forEach var="detail" items="${configs}">
				
				<c:if test="${bbsnum==detail.bbsnum}">
				<b>
				</c:if>
				<a href="<c:url value="/bbs/"/><c:out value="${detail.bbsnum}"/>/list/1" />
				<c:out value="${detail.bbsname}"/>
				</a>
				<c:if test="${bbsnum==detail.bbsnum}">
				</b>
				</c:if>
				 | 
		</c:forEach>
	</c:when>
	<c:otherwise>
		<p>등록된 게시판이 없습니다.</p>
	</c:otherwise>
</c:choose>
</div>

<input type="button" value="<spring:message code="bbs.write"/>" class="buttonStyle"
	onClick="location.href='<c:url value='/bbs/'/>${bbsnum}/form'" />

<c:choose>
	<c:when test="${listtypenum == 1}">
		<div class="content">
			<h3>
				<c:out value="${bbsname}" />
			</h3>

			<c:choose>
				<c:when test="${not empty bbss}">
					<table>
						<tbody>
							<tr>
								<th>번호</th>
								<th>제목</th>
								<th>등록일</th>
								<th>작성자</th>
								<th>조회수</th>
							</tr>
							<c:forEach var="detail" items="${bbss}">
								<tr>
									<td><c:out value="${detail.num}" /></td>
									<td>[<a
										href="<c:url value="/bbs"/>/<c:out value="${bbsnum}"/>/detail/<c:out value="${detail.num}"/>"><c:out
												value="${detail.config.bbsname}" /></a>] <a
										href="<c:url value="/bbs"/>/<c:out value="${bbsnum}"/>/detail/<c:out value="${detail.num}"/>"><c:out
												value="${detail.subject}" /></a>
									</td>
									<td><c:out value="${detail.regdate}" /></td>
									<td><c:out value="${detail.userid}" /></td>
									<td><c:out value="${detail.count}" /></td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
				</c:when>
				<c:otherwise>
					<p>등록된 게시물이 없습니다.</p>
				</c:otherwise>
			</c:choose>

		</div>

	</c:when>
	<c:when test="${listtypenum == 2}">

		<c:choose>
			<c:when test="${not empty bbss}">
				<div class="boxes">
					<c:forEach var="detail" items="${bbss}">
						<div class="box">
							<h3>
								<img width="273" height="198" src="<c:url value="/file/image"/>/bbs/<c:out value="${bbsnum}"/>/<c:out value="${detail.num}"/>" alt="" />
								<c:out value="${detail.num}" />
								<c:out value="${detail.subject}" />
							</h3>
							<p>
								<c:out value="${detail.content}" />
							</p>
							<div class="boxButton">
								<input type="button" value="DETAILS..." class="buttonStyle"
									onClick="location.href='<c:url value="/bbs"/>/<c:out value="${bbsnum}"/>/detail/<c:out value="${detail.num}"/>'" />
							</div>
						</div>
					</c:forEach>
				</div>
			</c:when>
			<c:otherwise>
				<p>등록된 게시물이 없습니다.</p>
			</c:otherwise>
		</c:choose>

	</c:when>

	<c:otherwise>
	</c:otherwise>
</c:choose>




<br class="clearfloat" />

<div class="welcome">
	<p>
		<c:out value="${paging}" escapeXml="false" />
	</p>
</div>

<br class="clearfloat" />