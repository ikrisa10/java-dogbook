<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="firstName" value="${requestScope.firstName}" scope="request" />
<!DOCTYPE html>
<html>
  <head>
    <title>Dodbook</title>
  </head>
  <body>
    <c:choose>
      <c:when test = "${not empty firstName}" >
        <h1>Find Friends for <c:out value="${firstName}"/></h1>
      </c:when>
      <c:otherwise>
        <h1>Dogbook Profiles</h1>
      </c:otherwise>
    </c:choose>


    <ul>
      <c:forEach items="${requestScope.dogs}" var="dog">
      <li>
        <c:out value="${dog.firstName}"/>, <c:out value="${dog.lastName}"/></br>
        <img src="<c:out value="${dog.photoUrl}"/>" height="300" width="300"/>
       </li>
      </c:forEach>
    </ul>
  </body>
</html>