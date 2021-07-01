<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
  <head>
    <title>Dodbook Errors</title>
  </head>
  <body>
    <h1>Errors</h1>
    <ul style="color: red;">
      <c:forEach items="${requestScope.errors}" var="error">
      <li>
         <c:out value="${error.propertyPath}"/>: <c:out value="${error.message}"/>
       </li>
      </c:forEach>
    </ul>
    <a href="/dogs/new">Try Again?</a>
  </body>
</html>