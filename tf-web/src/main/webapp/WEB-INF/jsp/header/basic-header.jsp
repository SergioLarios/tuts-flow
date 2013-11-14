<%@ include file="../init.jsp" %>
<html>
<head>
	
	<%-- CSS List --%>
	<link rel="stylesheet" type="text/css" href="/resource/style/main.css"/>
	
	<%-- JS List --%>
	<script type="text/javascript" src="/resource/js/main.js"></script>
	
	<%-- Title --%>
	<title>${view.title}</title>
	
</head>
<body>

	<section class="header">
	
		<%-- Logo --%>
		<div class="header-logo">
			<a href="/"><img alt="logo" src="/resource/img/header/Logo_Tuts4u.png" /></a>
		</div>
	
		<%-- Navigation --%>
		<nav class="header-nav">
			<ul class="nav">
			
				<li <c:if test="${sessionScope.page.type == 0}">class="active"</c:if> >
					<a href="<%=Mappings.HOME%>" title="<spring:message code="menu.home"/>">
						<spring:message code="menu.home"/>
					</a>
				</li>
				
				<li <c:if test="${sessionScope.page.type == 1}">class="active"</c:if> >
					<a href="<%=Mappings.EXPLORE%>" title="<spring:message code="menu.explore"/>">
						<spring:message code="menu.explore"/>
					</a>
				</li>
				
				<li <c:if test="${sessionScope.page.type == 2}">class="active"</c:if> >
					<a href="<%=Mappings.HOW_TO%>" title="<spring:message code="menu.how-to"/>">
						<spring:message code="menu.how-to"/>
					</a>
				</li>
				
			</ul>
		</nav>
		
		<%-- User box --%>
		<div class="user-box">
		
			<c:if test="${sessionScope.user.type == 0}">
				<spring:message code="header.user-guest"/>
			</c:if>
			
			<c:if test="${sessionScope.user.type != 0}">
			
				<spring:message code="header.user-logged" 
					arguments="${sessionScope.user.userName},${sessionScope.user.userNameSimple}"/>
					
			</c:if>
			
		</div>		
		
		<%-- Search Box --%>
		<form action="index.html" method="post" class="search">
			<input type="text" placeholder="Search..." name="q">
		</form>

		<span class="clear"></span>
	</section>

