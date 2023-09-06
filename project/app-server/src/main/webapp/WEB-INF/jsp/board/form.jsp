<%@ page
        language="java"
        pageEncoding="UTF-8"
        contentType="text/html;charset=UTF-8"
        trimDirectiveWhitespaces="true"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset='UTF-8'>
    <title>비트캠프</title>
    <style>
        table {
            width: 20%;
            margin: 20px auto;
            background-color: #fff;
            border: 1px solid #ccc;
        }
        th, td {
            padding: 10px;
            text-align: center;
            border: 1px solid #ccc;
        }
        th {
            color: #020000;
            white-space: nowrap; /* 텍스트 줄 바꿈 방지 */
        }
        tr:nth-child(even) {
            background-color: #f2f2f2;
        }
        h1 {
            text-align: center;
            margin: 50px;
        }
        div {
            text-align: center;
        }
        form {
            text-align: center;
        }
    </style>
</head>
<body>

<jsp:include page="../header.jsp"/>

<h1>게시글</h1>
<form action='/board/add' method='post' enctype='multipart/form-data'>
    제목 <input type='text' name='title'><br>
    내용 <textarea name='content'></textarea><br>
    파일 <input type='file' name='files' multiple><br>
    <input type='hidden' name='category' value='1'>
    <button>등록</button>
</form>

<jsp:include page="../footer.jsp"/>

</body>
</html>