
<%@ page import="tekdays.TekMessage" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'tekMessage.label', default: 'TekMessage')}" />
		<title><g:message code="default.show.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#show-tekMessage" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="list" action="index"><g:message code="default.list.label" args="[entityName]" /></g:link></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="show-tekMessage" class="content scaffold-show" role="main">
			<h1><g:message code="default.show.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<ol class="property-list tekMessage">
			
				<g:if test="${tekMessageInstance?.subject}">
				<li class="fieldcontain">
					<span id="subject-label" class="property-label"><g:message code="tekMessage.subject.label" default="Subject" /></span>
					
						<span class="property-value" aria-labelledby="subject-label"><g:fieldValue bean="${tekMessageInstance}" field="subject"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${tekMessageInstance?.content}">
				<li class="fieldcontain">
					<span id="content-label" class="property-label"><g:message code="tekMessage.content.label" default="Content" /></span>
					
						<span class="property-value" aria-labelledby="content-label"><g:fieldValue bean="${tekMessageInstance}" field="content"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${tekMessageInstance?.parent}">
				<li class="fieldcontain">
					<span id="parent-label" class="property-label"><g:message code="tekMessage.parent.label" default="Parent" /></span>
					
						<span class="property-value" aria-labelledby="parent-label"><g:link controller="tekMessage" action="show" id="${tekMessageInstance?.parent?.id}">${tekMessageInstance?.parent?.encodeAsHTML()}</g:link></span>
					
				</li>
				</g:if>
			
				<g:if test="${tekMessageInstance?.author}">
				<li class="fieldcontain">
					<span id="author-label" class="property-label"><g:message code="tekMessage.author.label" default="Author" /></span>
					
						<span class="property-value" aria-labelledby="author-label"><g:link controller="tekUser" action="show" id="${tekMessageInstance?.author?.id}">${tekMessageInstance?.author?.encodeAsHTML()}</g:link></span>
					
				</li>
				</g:if>
			
				<g:if test="${tekMessageInstance?.event}">
				<li class="fieldcontain">
					<span id="event-label" class="property-label"><g:message code="tekMessage.event.label" default="Event" /></span>
					
						<span class="property-value" aria-labelledby="event-label"><g:link controller="tekEvent" action="show" id="${tekMessageInstance?.event?.id}">${tekMessageInstance?.event?.encodeAsHTML()}</g:link></span>
					
				</li>
				</g:if>
			
			</ol>
			<g:form url="[resource:tekMessageInstance, action:'delete']" method="DELETE">
				<fieldset class="buttons">
					<g:link class="edit" action="edit" resource="${tekMessageInstance}"><g:message code="default.button.edit.label" default="Edit" /></g:link>
					<g:actionSubmit class="delete" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" />
				</fieldset>
			</g:form>
		</div>
	</body>
</html>
