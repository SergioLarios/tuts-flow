<%@ include file="../init.jsp" %>

<div class="login">

	<h1><spring:message code="register.header"/></h1>

	<form action="<%=Mappings.CREATE_ACCOUNT%>" method="post">
	
		<c:if test="${!view.home && view.loggedIn}">
		
			<div class="form-success">
				<spring:message code="register.success"/>
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
				<input type="text" name="<%=CreateAccountForm.USER_MAIL%>"
					value="${view.registerForm.userMail}"
					placeholder="<spring:message code="login.placeholder.email"/>">
			</p>
			
			<%-- ----- User name ----- --%>
			<p>
				<input type="text" name="<%=CreateAccountForm.USER_NAME%>"
					value="${view.registerForm.userName}"
					placeholder="<spring:message code="register.placeholder.name"/>">
			</p>
			
			<%-- ----- Password ----- --%>
			<p>
				<input type="password" name="<%=CreateAccountForm.USER_PASSWORD%>" 
					value="${view.registerForm.password}" 
					placeholder="<spring:message code="login.placeholder.password"/>">
			</p>
			
			<%-- ----- Password ----- --%>
			<p>
				<input type="password" name="<%=CreateAccountForm.USER_PASSWORD_REPEAT%>" 
					value="${view.registerForm.passwordRepeat}" 
					placeholder="<spring:message code="register.placeholder.repeat-password"/>">
			</p>
			
			<%-- ----- Submit ----- --%>
			<p class="submit">
				<input type="submit" name="commit" value="<spring:message code="register.submit"/>">
			</p>
		
		</c:if>
		
	</form>

</div>