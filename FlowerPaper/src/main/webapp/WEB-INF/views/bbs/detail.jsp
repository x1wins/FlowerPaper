<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<script src="<c:url value="/js/jquery-1.7.2.min.js"/>"></script>
<script src="<c:url value="/js/bbs.js"/>"></script>

<script type="text/javascript">
	function doAjaxPost(bbsnum) {
		
		
		// get the form values
		var content = $('#content').val();

		
		
		$.ajax({
			type : "POST",
			url : "/x1wins/bbs/"+bbsnum+"/reply",
			data : "content=" + content,
			success : function(response) {
				// we have the response
				$('#info').html(response);
				$('#content').val('');
			},
			error : function(e) {
				alert('Error: ' + e);
			}
		});
	}
</script>

<c:if test="${sessionScope.userid == detail.userid }">
	<input type="button" value="<spring:message code="bbs.update"/>"
		class="buttonStyle"
		onClick="location.href='<c:url value='/bbs/'/>${bbsnum}/updateform/${detail.num}'" />
	<input type="button" value="<spring:message code="bbs.delete"/>"
		class="buttonStyle"
		onClick="location.href='<c:url value='/bbs/'/>${bbsnum}/delete/${detail.num}'" />
</c:if>
<input type="button" value="<spring:message code="bbs.list"/>"
	class="buttonStyle"
	onClick="location.href='<c:url value='/bbs/'/>${bbsnum}/list/1'" />


<div class="memo">
	<h1>
		<c:out value="${detail.num}" />
		|
		<c:out value="${detail.subject}" />
		|
		<c:out value="${detail.userid}" />
		|
		<c:out value="${detail.regdate}" />
	</h1>

	<c:forEach var="file" items="${filelist}">
		<p>
			<c:choose>
				<c:when test="${file.filetype == 'image'}">
					<img src="<c:out value="${file.url}" />" />
				</c:when>
				<c:otherwise>
					첨부 파일 : <a href="<c:out value="${file.url}" />"><c:out
							value="${file.filename}" /></a>
				</c:otherwise>
			</c:choose>
		</p>
		<br />
	</c:forEach>

	<p>
		<c:out value="${detail.content}" />
	</p>
	<br/>
	<p>
		<!-- AddThis Button BEGIN -->
		<div class="addthis_toolbox addthis_default_style ">
			<a class="addthis_button_facebook_like" fb:like:layout="button_count"></a>
			<a class="addthis_button_tweet"></a> <a
				class="addthis_button_google_plusone" g:plusone:size="medium"></a> <a
				class="addthis_counter addthis_pill_style"></a>
		</div>
		<script type="text/javascript" src="http://s7.addthis.com/js/250/addthis_widget.js#pubid=xa-4ea9465e0a83b146"></script>
		<!-- AddThis Button END -->
	</p>
	
</div>




<div class="memo">

<input type="button" value="Add Users" onclick="doAjaxPost('<c:out value="${detail.num}"/>')">

<form:form method="post" action="form" commandName="reply">
	<form:input path="content" />
	<input type="submit" value="Submit" />
</form:form>

<div id="info"></div>
</div>
<div class="memo" id="71">
	이창우(x1wins) | 2012-05-09 | <a href="javascript:delReply(71,529)">삭제</a><br />
	http://www.mkyong.com/hibernate/hibernate-cascade-example-save-update-delete-and-delete-orphan/
</div>
<div class="memo" id="71">
	이창우(x1wins) | 2012-05-09 | <a href="javascript:delReply(71,529)">삭제</a><br />
	http://www.mkyong.com/hibernate/hibernate-cascade-example-save-update-delete-and-delete-orphan/
</div>
<div class="clearfloat"></div>
