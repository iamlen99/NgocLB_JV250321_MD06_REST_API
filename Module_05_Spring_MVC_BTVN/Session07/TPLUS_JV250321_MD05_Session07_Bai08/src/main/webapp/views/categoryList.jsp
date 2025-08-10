<%--
  Created by IntelliJ IDEA.
  User: ngoclb
  Date: 8/7/25
  Time: 3:14 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<html>
<head>
    <title>Danh sach danh muc</title>
    <link href="https://fonts.googleapis.com/css2?family=Roboto&display=swap" rel="stylesheet">
    <style>
        body {
            margin: 30px;
            font-family: 'Roboto', sans-serif;
            background-color: #f5f5f5;
        }

        h2 {
            text-align: center;
            color: #333;
        }

        .message {
            text-align: center;
            margin: 10px 0;
            font-weight: bold;
        }

        .message.success {
            color: green;
        }

        .message.error {
            color: red;
        }

        .add-button {
            display: inline-block;
            margin-bottom: 15px;
            padding: 10px 20px;
            background-color: #28a745;
            color: white;
            text-decoration: none;
            border-radius: 5px;
        }

        .add-button:hover {
            background-color: #218838;
        }

        table {
            width: 100%;
            border-collapse: collapse;
            background-color: white;
        }

        th, td {
            border: 1px solid #ccc;
            padding: 12px;
        }

        td:nth-child(1), td:nth-child(4), td:nth-child(5) {
            text-align: center;
        }

        th {
            background-color: #007bff;
            color: white;
            text-align: center;
        }

        tr:hover {
            background-color: #f1f1f1;
        }

        img {
            width: 100px;
            height: 150px;
            object-fit: cover;
            border-radius: 4px;
        }

        .action-buttons a {
            margin: 0 5px;
            padding: 6px 12px;
            text-decoration: none;
            border-radius: 4px;
            color: white;
        }

        .update-btn {
            background-color: #ffc107;
        }

        .update-btn:hover {
            background-color: #e0a800;
        }

        .delete-btn {
            background-color: #dc3545;
        }

        .delete-btn:hover {
            background-color: #c82333;
        }
    </style>
</head>
<body>

<c:if test="${not empty message}">
    <p class="message success">${message}</p>
</c:if>
<c:if test="${not empty error}">
    <p class="message error">${error}</p>
</c:if>

<h2>Category List</h2>

<a href="goToAddPage" class="add-button">+ Add category</a>

<table>
    <thead>
    <tr>
        <th>ID</th>
        <th>Category name</th>
        <th>Description</th>
        <th>Status</th>
        <th>Action</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach var="category" items="${categories}">
        <tr>
            <td>${category.id}</td>
            <td>${category.categoryName}</td>
            <td>${category.description}</td>
            <td>${category.status ? "Active" : "Deactive"}</td>
            <td class="action-buttons">
                <a href="goToUpdatePage?id=${category.id}" class="update-btn">Edit</a>
                <a onclick="confirmDelete(${category.id})" class="delete-btn" style="cursor: pointer;">Delete</a>
            </td>
        </tr>
    </c:forEach>
    </tbody>
</table>

<script>
    function confirmDelete(id) {
        if (confirm("Ban co chac chan muon xoa danh muc nay?")) {
            window.location.href = "/deleteCategory?id=" + id;
        }
    }
</script>

</body>
</html>




