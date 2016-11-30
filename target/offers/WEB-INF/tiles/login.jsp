<%@ page language="java" contentType="text/html; charset=UTF-8"
  pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<script type="text/javascript">
$(document).ready(function(){
	document.f.username.focus();

})
</script>
  <h3>Login with Username and Password</h3>

  <c:if test="${param.error != null}">

    <p class="error">Login failed. Check to make sure login name and
      password is correct</p>

  </c:if>

  <c:url value="/login" var="loginUrl" />
  <form name='f' action='${loginUrl}' method='POST'>
    <table class="formtable">
      <tr>
        <td>User:</td>
        <td><input type='text' name='username' value=''></td>
      </tr>
      <tr>
        <td>Password:</td>
        <td><input type='password' name='password' /></td>
      </tr>
      <tr>
        <td>Remember me:</td>
        <td><input type='checkbox' name='remember-me'
          checked='checked' /></td>
      </tr>
      <tr>
        <td colspan='2'><input name="submit" type="submit"
          value="Login" /></td>
      </tr>
      <input name="${_csrf.parameterName}" type="hidden"
        value="${_csrf.token}" />
    </table>
  </form>

  <p>
    <a href="<c:url value="/newaccount"/>">Create new account</a>
  </p>
