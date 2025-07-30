<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>Currency conversion</title>
    <style>
        body {
            background: #f0f0f0;
            font-family: Arial, sans-serif;
            display: flex;
            justify-content: center;
            align-items: center;
            height: 100vh;
            margin: 0;
        }

        #conversion {
            background-color: white;
            padding: 40px 30px;
            border-radius: 15px;
            box-shadow: 0 0 10px rgba(0,0,0,0.15);
            text-align: center;
        }

        h1 {
            font-size: 36px;
            font-weight: bold;
            margin-bottom: 30px;
        }

        label {
            display: block;
            text-align: left;
            margin-bottom: 5px;
            font-size: 18px;
        }

        input[type="text"] {
            width: 100%;
            padding: 12px;
            font-size: 16px;
            margin-bottom: 20px;
            border: 2px solid #999;
            border-radius: 8px;
            box-sizing: border-box;
        }

        button {
            font-size: 18px;
            padding: 10px 20px;
            border: 2px solid #999;
            border-radius: 8px;
            background-color: #f5f5f5;
            cursor: pointer;
        }

        button:hover {
            background-color: #e0e0e0;
        }
    </style>
</head>
<body>
<div id="conversion">
<h1>Chuyen doi tien te</h1>
    <% String error = (String) request.getAttribute("error"); %>
    <% if (error != null) { %>
    <p style="color: red; font-weight: bold;"><%= error %></p>
    <% } %>

    <form action="HandleConvert" method="post">
        <label for="rate">Rate</label><br>
        <input type="text" name="rate" id="rate" placeholder="Enter your rate"><br>
        <label for="currency">USD</label><br>
        <input type="text" name="usd" id="currency" placeholder="Enter your usd"><br>
        <button type="submit" id="convert">Convert</button>
    </form>
</div>
</body>
</html>