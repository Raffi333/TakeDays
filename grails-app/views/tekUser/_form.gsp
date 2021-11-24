<%@ page import="tekdays.TekUser" %>



<div class="fieldcontain ${hasErrors(bean: tekUserInstance, field: 'fullName', 'error')} required">
	<label for="fullName">
		<g:message code="tekUser.fullName.label" default="Full Name" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="fullName" required="" value="${tekUserInstance?.fullName}"/>

</div>

<div class="fieldcontain ${hasErrors(bean: tekUserInstance, field: 'userName', 'error')} required">
	<label for="userName">
		<g:message code="tekUser.userName.label" default="User Name" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="userName" required="" value="${tekUserInstance?.userName}"/>

</div>

<div class="fieldcontain ${hasErrors(bean: tekUserInstance, field: 'email', 'error')} required">
	<label for="email">
		<g:message code="tekUser.email.label" default="Email" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="email" required="" value="${tekUserInstance?.email}"/>

</div>

<div class="fieldcontain ${hasErrors(bean: tekUserInstance, field: 'website', 'error')} required">
	<label for="website">
		<g:message code="tekUser.website.label" default="Website" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="website" required="" value="${tekUserInstance?.website}"/>

</div>

<div class="fieldcontain ${hasErrors(bean: tekUserInstance, field: 'bio', 'error')} required">
	<label for="bio">
		<g:message code="tekUser.bio.label" default="Bio" />
		<span class="required-indicator">*</span>
	</label>
	<g:textArea name="bio" cols="40" rows="5" maxlength="5000" required="" value="${tekUserInstance?.bio}"/>

</div>

<div class="fieldcontain ${hasErrors(bean: tekUserInstance, field: 'password', 'error')} required">
	<label for="password">
		<g:message code="tekUser.password.label" default="Password" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="password" required="" value="${tekUserInstance?.password}"/>

</div>

