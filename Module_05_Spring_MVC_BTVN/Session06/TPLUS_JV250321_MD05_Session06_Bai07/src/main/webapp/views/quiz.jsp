<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Quiz Game</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            padding: 20px;
            text-align: center;
        }
        .image-container img {
            max-width: 300px;
            height: auto;
        }
        .feedback {
            margin-top: 15px;
            font-weight: bold;
            color: red;
        }
        .correct {
            color: green;
        }
    </style>
</head>
<body>
<h2>Đoán hình</h2>

<div class="image-container">
    <c:if test="${not empty question}">
        <img src="${question.imageUrl}" alt="Câu hỏi"/>
    </c:if>
</div>

<form method="post" action="${pageContext.request.contextPath}/guess">
    <input type="text" name="answer" placeholder="Nhập câu trả lời của bạn" required/>
    <br/><br/>
    <c:if test="${noGuess == null}">
        <button type="submit">Đoán</button>
    </c:if>
    <c:if test="${not empty playagain}">
        <button><a href="${pageContext.request.contextPath}/quizController/" style="text-decoration: none">${playagain}</a></button>
    </c:if>
</form>

<div class="feedback">
    <c:if test="${not empty correct}">
        <p class="correct">${correct}</p>
    </c:if>
    <c:if test="${not empty wrong}">
        <p>${wrong}</p>
    </c:if>
</div>
</body>
</html>
