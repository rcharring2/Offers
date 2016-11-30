<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec"
  uri="http://www.springframework.org/security/tags"%>
    

<c:choose>
  <c:when test="${hasOffer}">

    <a href="${pageContext.request.contextPath}/createoffer">Edit or
      Delete your current offer</a>

  </c:when>
  <c:otherwise>

    <a href="${pageContext.request.contextPath}/createoffer">Add new
      offer</a>

  </c:otherwise>
</c:choose>
&nbsp;
<sec:authorize access="hasRole('ROLE_ADMIN')">
  <a class="login" href="${pageContext.request.contextPath}/admin">admin</a>
</sec:authorize>
&nbsp;
<sec:authorize access="isAuthenticated()">
  <a href="${pageContext.request.contextPath}/messages">Messages (<span
    id="numberMessages">0</span>)
  </a>
</sec:authorize>
