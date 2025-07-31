<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>JSP - Hello World</title>
    <link href="css/style.css" rel="stylesheet">
</head>
<body>
<div class="signup-container">
    <h2>Sign up</h2>
    <form action="servlet" method="post">
        <% String message = (String) request.getAttribute("message"); %>
        <% if (message != null) { %>
        <p style="color: green; font-weight: bold;"><%= message %>
        </p>
        <% } %>
        <input type="text" name="username" placeholder="Username"
               value="<%= request.getAttribute("usernameValue") != null ? request.getAttribute("usernameValue") : "" %>">
        <% String usernameError = (String) request.getAttribute("usernameError"); %>
        <% if (usernameError != null) { %>
        <p style="color: red; font-weight: bold;"><%= usernameError %>
        </p>
        <% } %>

        <input type="email" name="email" placeholder="Email"
               value="<%= request.getAttribute("emailValue") != null ? request.getAttribute("emailValue") : "" %>">

        <% String emailError = (String) request.getAttribute("emailError"); %>
        <% if (emailError != null) { %>
        <p style="color: red; font-weight: bold;"><%= emailError %>
        </p>
        <% } %>
        <input type="password" name="password" placeholder="Password">
        <% String passwordError = (String) request.getAttribute("passwordError"); %>
        <% if (passwordError != null) { %>
        <p style="color: red; font-weight: bold;"><%= passwordError %>
        </p>
        <% } %>
        <input type="password" name="confirm-password" placeholder="Confirm password">
        <% String confirmPasswordError = (String) request.getAttribute("confirmPasswordError"); %>
        <% if (confirmPasswordError != null) { %>
        <p style="color: red; font-weight: bold;"><%= confirmPasswordError %>
        </p>
        <% } %>
        <button type="submit">Sign up</button>
    </form>
</div>
</body>
</html>