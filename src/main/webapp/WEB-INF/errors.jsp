<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="ru">
<head>
    <meta charset="UTF-8">
    <title>Квест</title>

    <style>
        body {
            margin: 0;
            height: 100vh;

            display: flex;
            justify-content: center;
            align-items: center;

            background: #111;
            color: #ddd;

            font-family: Georgia, serif;
        }

        .card {
            width: 700px;
            max-width: 90%;

            background: #1b1b1b;
            border: 1px solid #444;

            padding: 40px;
            box-shadow: 0 0 30px rgba(0,0,0,0.7);
        }

        h1 {
            margin-top: 0;
            color: #e0d2a0;
            letter-spacing: 2px;
            text-transform: uppercase;
        }

        .subtitle {
            color: #999;
            margin-bottom: 20px;
            font-style: italic;
        }

        p {
            line-height: 1.7;
            color: #cfcfcf;
        }

        form {
            margin-top: 15px;
        }

        button {
            width: 100%;

            padding: 12px 18px;
            margin-top: 10px;

            background: #5d4b2c;
            color: #fff;

            border: none;
            cursor: pointer;

            font-size: 16px;
            text-align: left;
        }

        button:hover {
            background: #75603a;
        }

        .meta {
            margin-bottom: 20px;
            color: #888;
            font-size: 14px;
        }
    </style>
</head>

<body>

<div class="card">
    <h1>Ошибки выполнения приложения</h1>
    <div class="subtitle">
       Возникла следующая ошибка при выполнении приложения:
    </div>
    <p> ${error}</p>


<form method="get" action="/QuestApp/">
<button type=submit >На главную</button>
</form>
</div>
</body>
</html>