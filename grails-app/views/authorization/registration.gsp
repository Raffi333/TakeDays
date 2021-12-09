<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <meta name="layout" content="main"/>
    <title>Registration</title>
</head>

<body>
<g:if test="${flash.message}">
    <div class="message">${flash.message}</div>
</g:if>
<div>
    <g:form url="[action: 'validate_register']">
        <fieldset class="form">
            <g:render template="form"/>
        </fieldset>
        <g:submitButton name="create" class="save"
                        value="${message(code: 'default.button.create.label', default: 'Create')}"/>
    </g:form>
</div>

</body>
</html>
