<%@ page
        language="java"
        pageEncoding="UTF-8"
        contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>아파트 주차 관리 시스템</title>
</head>
<style>
    body {
        padding: 0px;
        margin: 0px;
    }

    #divPosition {
        border: 1px solid white;
        position: absolute;
        height: 300px;
        width: 400px;
        margin: -160px 100px 50px -155px;
        top: 50%;
        left: 50%;
        padding: 5px;
        background-color: rgba(255, 255, 255, 0.7);
    }
</style>
<body>
<div id="divPosition">
    <img src="/images/logo.png" style="height: 60px">
</br>
    <h1>차량번호를 입력하세요</h1>
</br>
    <form action="entry" method="post">
        <table border="1">
            <tr>
                <th>차량번호</th>
                <td>
                    <input type="text" name="carnumber">
                    <button type="submit">입차</button>
                </td>
            </tr>
        </table>
    </form>
</div>
</body>
</html>

