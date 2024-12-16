<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <title>Login</title>
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
    .login-container input[type="password"] {
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
    .login-container input[type="password"]:focus {
      border-color: #4CAF50;
      outline: none;
    }
  </style>
</head>
<body>

<div class="login-container">
  <h1>Login</h1>

  <form action="controller" method="post">
    <label for="login">Username:</label>
    <input type="text" name="login" id="login" required />

    <label for="password">Password:</label>
    <input type="password" name="password" id="password" required />
    <a href="registration.jsp">Create account?</a>
    <input type="hidden" name="action" value="login" />
    <input type="submit" value="Login" />
  </form>
</div>

<!-- Display error message if login fails -->
<c:if test="${not empty errorMessage}">
  <div style="color: red; font-weight: bold;">
      ${errorMessage}
  </div>
</c:if>

</body>
</html>
