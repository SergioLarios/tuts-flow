<%@ include file="../header/basic-header.jsp"%>

<section class="container">

	<div class="content-wrapper">
	
		<h1 class="home-h1"><spring:message code="home.main"/></h1>
		
		<c:if test="${!view.loggedIn}">
		
			<div class="home-credentials" >
				<div class="box">
					<%@ include file="../include/login_include.jsp"%>
				</div>
				<div class="box">
					<%@ include file="../include/registrer_include.jsp"%>
				</div>
				<div class="clear"></div>
			</div>
			
		</c:if>
		
	</div>
	
</section>

<%@ include file="../footer/basic-footer.jsp"%>