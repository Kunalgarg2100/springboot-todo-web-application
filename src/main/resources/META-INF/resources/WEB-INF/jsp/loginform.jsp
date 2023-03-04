<%@ include file="common/headers.jspf"%>
<%@ include file="common/navigation.jspf"%>
<div class="container">
Welcome to login page!
<pre>${errorMsg}</pre>
<form:form method="post">
    <fieldset class="mb-3">
        Name : <input type="text" name="name">
    </fieldset>
    <fieldset class="mb-3">
        Password : <input type="password" name="password">
    </fieldset>
    <input type="submit">
</form:form>
</div>
<%@ include file="common/footers.jspf"%>