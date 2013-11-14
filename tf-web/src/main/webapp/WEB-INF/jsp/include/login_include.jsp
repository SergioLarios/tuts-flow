<%@ include file="../init.jsp" %>

<div class="login">

	<h1><spring:message code="login.header"/></h1>

	<form action="<%=Mappings.LOGIN%>" method="post">
	
		<c:if test="${!view.home && view.loggedIn}">
			<div class="form-success">
				<spring:message code="login.success"/>
			</div>
		</c:if>
		
		<c:if test="${!view.loggedIn}">
		
			<%-- ----- Errors ----- --%>
			<c:if test="${!view.home && !view.valid}">
				<div class="form-errors">
					<ul>
						<c:forEach var="error" items="${view.errors}">
							<li><spring:message code="${error}"/></li>
						</c:forEach>
					</ul>
				</div>
			</c:if>
		
			<%-- ----- User Mail ----- --%>
			<p>
				<input type="text" name="<%=LoginForm.USER_MAIL%>" 
					value="${view.loginForm.userMail}" 
					placeholder="<spring:message code="login.placeholder.email"/>">
			</p>
			
			<%-- ----- Password ----- --%>
			<p>
				<input type="password" name="<%=LoginForm.USER_PASSWORD%>" 
					value="${view.loginForm.password}" 
					placeholder="<spring:message code="login.placeholder.password"/>">
			</p>
			
			<%-- ----- Submit ----- --%>
			<p class="submit">
				<input type="submit" name="commit" value="<spring:message code="login.submit"/>">
			</p>
		
		</c:if>
		
	</form>

</div>