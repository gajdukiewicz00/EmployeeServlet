<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link rel="stylesheet" href="style.css">
    <title>Welcome Page</title>
</head>
<body>
<%
    response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
    response.setHeader("Pragma", "no-cache"); // for HTTP 1.0
    response.setDateHeader("Expires", 0); // Proxy settings

    if (session.getAttribute("login") == null){
        response.sendRedirect("login.jsp");
    }
%>

<div class="header">
    <a href="welcome.jsp" class="logo">CompanyLogo</a>
    <div class="header-right">
        <a class="active" href="welcome.jsp">Home</a>
        <a href="controller?action=data">Employees</a>
        <a href="controller?action=logout" class="logout-link">Logout</a>
    </div>
</div>

<form action="controller" method="post" class="welcome-container">
    <h1>Welcome, ${login}</h1>

    <table>
        <tr>
            <th>Name:</th>
            <td><input type="text" name="name" required /></td>
        </tr>
        <tr>
            <th>Role:</th>
            <td><input type="text" name="role" required /></td>
        </tr>
        <tr>
            <td colspan="2">
                <input type="hidden" name="action" value="save" />
                <input type="submit" value="Save"  />
            </td>
        </tr>
    </table>
</form>

</body>
</html>
