<%@ page import="java.io.PrintWriter" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:useBean id="date" class="java.util.Date" />
<html>
<head>
  <title>JSP Page</title>
</head>
<body>
  <%-- This is jsp comment --%>
  <h1>List</h1>
  <p>Value with EL: ${name}</p>
  <!-- This is html comment -->
  <p>Value with JSTL: <c:out value="${name}" /></p>


  <%! private int accessCount = 0; %>
  <p>Amount of access to current page: <%= ++accessCount %></p>

  <br>
  Now: <c:out value="${date}" />
  <br>
  Host name: <%= request.getRemoteHost() %>
  <br>
  <p>
    <%
      String queryMethod = request.getMethod();
      PrintWriter writer = response.getWriter();
      writer.println("Method: " + queryMethod);
    %>
  </p>

  <br>
  <table>
    <thead>
      <th>Id</th>
      <th>First Name</th>
      <th>Last Name</th>
      <th>Email</th>
    </thead>
    <tbody>
  <c:forEach var="user" items="${users}">
    <tr>
      <td>${user.id}</td>
      <td>${user.firstName}</td>
      <td>${user.lastName}</td>
      <td>${user.email}</td>
    </tr>
  </c:forEach>
    </tbody>
  </table>

</body>
</html>
