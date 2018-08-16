<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<h3>Welcome to Spring</h3>
<ul>
	<li>Spring Core</li>
	<li>Spring MVC</li>
	<li>Spring Batch</li>
	<li>Spring Boot</li>
</ul>

<c:url value="/logout" var="logoutUrl" />
<form id="logout" action="${logoutUrl}" method="post">
	<input type="hidden" name="${_csrf.parameterName}"
		value="${_csrf.token}" />
</form>
<c:if test="${pageContext.request.userPrincipal.name != null}">
	<a href="javascript:document.getElementById('logout').submit()">Logout</a>
</c:if>