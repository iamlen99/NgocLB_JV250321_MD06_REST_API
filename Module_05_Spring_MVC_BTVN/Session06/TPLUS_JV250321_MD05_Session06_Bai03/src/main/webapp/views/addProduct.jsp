<%--
  Created by IntelliJ IDEA.
  User: ngoclb
  Date: 8/6/25
  Time: 12:02 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <title>Them san pham</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background: #f2f2f2;
            padding: 20px;
        }

        .form-container {
            max-width: 500px;
            margin: 0 auto;
            background: white;
            padding: 30px;
            border-radius: 10px;
            box-shadow: 0 4px 10px rgba(0,0,0,0.1);
        }

        .form-container h2 {
            text-align: center;
            margin-bottom: 20px;
        }

        .form-group {
            margin-bottom: 15px;
        }

        label {
            display: block;
            font-weight: bold;
            margin-bottom: 6px;
        }

        input[type="text"],
        input[type="number"] {
            width: 100%;
            padding: 10px;
            border: 1px solid #ccc;
            border-radius: 6px;
            font-size: 14px;
        }

        button {
            width: 100%;
            padding: 12px;
            background-color: #28a745;
            border: none;
            border-radius: 6px;
            color: white;
            font-size: 16px;
            cursor: pointer;
        }

        button:hover {
            background-color: #218838;
        }
    </style>
</head>
<body>
<div class="form-container">
    <h2>Thêm sản phẩm mới</h2>
    <c:if test="${message != null}">
        <p style="color: red">${message}</p>
    </c:if>
    <form action="${pageContext.request.contextPath}/product/addProduct" method="post">
        <div class="form-group">
            <label for="productName">Tên sản phẩm:</label>
            <input type="text" id="productName" name="productName" value="${product.productName}" placeholder="Nhập tên sản phẩm">
        </div>
        <div class="form-group">
            <label for="price">Giá sản phẩm (VND):</label>
            <input type="number" id="price" name="price" value="${product.price}" placeholder="Nhập giá sản phẩm" step="1000">
        </div>
        <div class="form-group">
            <label for="description">Mô tả sản phẩm:</label>
            <input type="text" id="description" name="description" value="${product.description}" placeholder="Nhập mô tả sản phẩm">
        </div>
        <div class="form-group">
            <label for="image">URL hình ảnh:</label>
            <input type="text" id="image" name="image" value="${product.imageUrl}" placeholder="Nhập đường dẫn ảnh sản phẩm">
        </div>
        <button type="submit">Thêm sản phẩm</button>
    </form>
</div>
</body>
</html>
