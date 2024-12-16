<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Sign Up</title>
    <style>
        /* Center the form on the page */
        body {
            display: flex;
            justify-content: center;
            align-items: center;
            min-height: 100vh;
            margin: 0;
            font-family: Arial, sans-serif;
            background-color: #f0f0f5;
        }

        /* Style the form container */
        .login-container {
            width: 300px;
            padding: 20px;
            background-color: #fff;
            box-shadow: 0px 0px 10px rgba(0, 0, 0, 0.1);
            border-radius: 8px;
            text-align: center;
        }

        /* Style the form elements */
        .login-container h1 {
            margin-bottom: 20px;
            font-size: 24px;
            color: #333;
        }

        .login-container label {
            display: block;
            font-weight: bold;
            margin-bottom: 5px;
            text-align: left;
        }

        .login-container input[type="text"],
        .login-container input[type="password"],
        .login-container input[type="repeatPassword"] {
            width: 100%;
            padding: 10px;
            margin-bottom: 15px;
            border: 1px solid #ccc;
            border-radius: 5px;
        }

        .login-container input[type="submit"] {
            width: 100%;
            padding: 10px;
            border: none;
            border-radius: 5px;
            background-color: #4CAF50;
            color: white;
            font-weight: bold;
            cursor: pointer;
        }

        .login-container input[type="submit"]:hover {
            background-color: #45a049;
        }

        a {
            display: block;
            color: #4CAF50;
            font-weight: bold;
            margin-top: 15px;
            padding-bottom: 10px;
            text-decoration: none;
        }

        /* Focused input fields */
        .login-container input[type="text"]:focus,
        .login-container input[type="password"]:focus,
        .login-container input[type="repeatPassword"]:focus {
            border-color: #4CAF50;
            outline: none;
        }

        @media (max-width: 600px) {
            .login-container {
                width: 90%;
            }
        }
    </style>
</head>
<script>
    function matchPassword() {
        var pw1 = document.getElementById("password").value;
        var pw2 = document.getElementById("repeatPassword").value;

        // Check if passwords match
        if (pw1 !== pw2) {
            alert("Passwords do not match. Please try again.");
            return false;
        }

        // Check password strength (e.g., minimum 8 characters)
        if (pw1.length < 8) {
            alert("Password should be at least 8 characters long.");
            return false;
        }

        return true;
    }
</script>
<body>

<div class="login-container">
    <h1>Sign Up</h1>

    <c:if test="${not empty errorMessage}">
        <div style="color: red; font-weight: bold;">
                ${errorMessage}
        </div>
    </c:if>

    <form action="controller?action=signUp" method="post" autocomplete="off">
        <label for="username">Username:</label>
        <input type="text" name="login" id="username" autocomplete="off" required/>

        <label for="password">Create a password:</label>
        <input type="password" name="password" id="password" required />

        <label for="repeatPassword">Repeat password:</label>
        <input type="password" name="repeatPassword" id="repeatPassword" required>

        <a href="login.jsp">I am already registered</a>

<%--        <input type="hidden" name="action" value="registration" />--%>
        <input type="submit" value="Sign Up" onclick="return matchPassword()"/>
    </form>
</div>

</body>
</html>
