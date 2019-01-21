<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8"%>
<html>
<head>
  <link rel="stylesheet" type="text/css" href="/resources/style.css" >
  <title>Задание</title>
</head>
<body>
<h2>Тестовое задание JavaRush</h2>

<table class="part">
  <tr>
    <th width="200">Ссылка</th>
    <th width="200">Описание</th>
  </tr>
  <tr>
    <td width="200"><a href="https://javarush.ru/downloads/internship/testtask.zip">Задание</a></td>
    <td width="200">Скачать задание</td>
  </tr>
  <tr>
    <td width="200"><a href="<c:url value="/parts/all/1"/>" target="_blank">Решение</a></td>
    <td width="200">Открыть решение</td>
  </tr>
</table>

</body>
</html>