<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<table  WIDTH="100%">
<tr>
<td align="left">
<img src="/InvoiceSystem/images/${sessionScope.login.tenant.tenantId}.jpg" height="80" width="180" />
</td>
<td align="right">
Welcome, ${sessionScope['login'].userId}<br>
<a href="/InvoiceSystem/j_spring_security_logout">Sign Out</a>
</td>
</tr>
</table>
<c:choose>
    <c:when test="${sessionScope.login.role == 'ROLE_ADMIN'}">
		<script type="text/javascript" src="/InvoiceSystem/js/menu_admin.js"></script>

	</c:when>
	<c:when test="${sessionScope.login.role == 'ROLE_ADMIN'}">
		<script type="text/javascript" src="/InvoiceSystem/js/menu_comp.js"></script>
	</c:when>
	<c:otherwise>
		<script type="text/javascript" src="/InvoiceSystem/js/menu_user.js"></script>

	</c:otherwise>
</c:choose>