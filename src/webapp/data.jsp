<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <link rel="stylesheet" href="style.css">
    <link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta.2/css/bootstrap.min.css">
    <title>Database</title>
    <style>
        table {
            border-collapse: collapse;
            width: 50%;
            margin: 20px auto;
        }
        th, td {
            border: 1px solid black;
            padding: 10px;
            text-align: left;
        }
        th {
            background-color: #f2f2f2;
        }
        table tr:hover td {
            background-color: #dddddd;
        }
        h1 {
            text-align: center;
        }
        form {
            text-align: center;
            margin-top: 20px;
        }
        .create-new {
            width: 100%;
            padding: 10px;
            border: none;
            border-radius: 5px;
            background-color: #4CAF50;
            color: white;
            font-weight: bold;
            cursor: pointer;
            text-align: center;
        }
    </style>
</head>
<body>

<%
    // Prevent caching of the page to ensure that content is always up-to-date
    response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
    response.setHeader("Pragma", "no-cache"); // for HTTP 1.0
    response.setDateHeader("Expires", 0); // Proxy settings
%>

<!-- Header section with logo and navigation links -->
<div class="header">
    <a href="welcome.jsp" class="logo">CompanyLogo</a>
    <div class="header-right">
        <a class="active" href="welcome.jsp">Home</a>
        <a href="controller?action=data">Employees</a>
        <a href="controller?action=logout" class="logout-link">Logout</a>
    </div>
</div>

<!-- Welcome heading -->
<h1>Welcome to the Database</h1>

<!-- Search form to search employees by name -->
<div class="container">
    <form class="form-inline" method="post" action="controller?action=search">
        <input type="text" name="sName" class="form-control" value="${searchName}" placeholder="Search by name...">
        <button type="submit" name="save" class="btn">Search</button>
    </form>
</div>

<!-- Employees table displaying employee data -->
<table>
    <thead>
    <tr>
        <th>&#8470</th>
        <th>eID</th>
        <th>Name</th>
        <th>Role</th>
        <th>Action</th>
    </tr>
    </thead>
    <tbody>
    <!-- Loop through employee list and display each employee -->
    <c:forEach var="e" items="${employeesList}" varStatus="status">
        <tr>
            <td>${status.count}</td>
            <td><c:out value="${e.id}" /></td>
            <td><c:out value="${e.name}" /></td>
            <td><c:out value="${e.role}" /></td>
            <td>
                <!-- Update Action -->
                <form action="controller" method="post" style="display: inline;">
                    <input type="hidden" name="id" value="${e.id}">
                    <input type="hidden" name="action" value="update-employee">
                    <button type="submit" style="background: none; border: none; color: #4CAF50; cursor:pointer;">
                        <i class="material-icons">update</i>
                    </button>
                </form>

                <!-- Delete Action -->
                <form action="controller" method="post" style="display:inline;" onsubmit="return confirmDelete();">
                    <input type="hidden" name="id" value="${e.id}" />
                    <input type="hidden" name="action" value="delete-employee">
                    <button type="submit" style="background:none; border:none; color:red; cursor:pointer;">
                        <i class="material-icons">delete</i>
                    </button>
                </form>
            </td>
        </tr>
    </c:forEach>
    </tbody>
</table>

<!-- Button to create a new employee -->
<button class="create-new" type="button" onclick="window.location.href='controller?action=create-new'">
    Create new
</button>

<script>
    // Confirmation dialog for delete action
    function confirmDelete() {
        return confirm("Are you sure you want to delete this employee?");
    }
</script>

</body>
</html>
