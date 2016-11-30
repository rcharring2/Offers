
<%@ page language="java" contentType="text/html; charset=UTF-8"
  pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec"
  uri="http://www.springframework.org/security/tags"%>

<a class = "title" href="<c:url value='/' />">Offers</a>


<sec:authorize access="!isAuthenticated()">
  <a class = "login" href="<c:url value='login' />">Log in</a>
</sec:authorize>

<sec:authorize access="isAuthenticated()">
  <c:url var="logoutUrl" value="/logout" />
  <form id = "logout" action="${logoutUrl}" method="post">
    <input type="hidden"
      name="${_csrf.parameterName}" value="${_csrf.token}" />
  </form>
  
 <a class="login" href="#" onclick="document.getElementById('logout').submit();">Logout</a>
  
</sec:authorize>