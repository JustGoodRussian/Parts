<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib prefix="from" uri="http://www.springframework.org/tags/form" %>
<%@ page session="false" %>
<html>
<head>
    <link rel="stylesheet" type="text/css" href="/resources/style.css" >
    <title>Комплектующие</title>
</head>
<body>

<h1>Добавить/редактировать деталь</h1>

<c:url var="addAction" value="/add"/>

<form:form action="${addAction}" modelAttribute="part">
    <table>
        <tr>
            <td>
                <form:hidden path="id"/>
            </td>
        </tr>
        <tr>
            <td>
                <form:label path="title">
                    <spring:message text="Наименование"/>
                </form:label>
            </td>
            <td>
                <form:input path="title"/>
            </td>
        </tr>
        <tr>
            <td>
                <form:label path="necessity">
                    <spring:message text="Необходимость"/>
                </form:label>
            </td>
            <td>
                <form:select path="necessity">
                    <form:option label="Нет" value='0'></form:option>
                    <form:option label="Да"  value='1'></form:option>
                </form:select>
            </td>
        </tr>
        <tr>
            <td>
                <form:label path="quantity">
                    <spring:message text="Количество"/>
                </form:label>
            </td>
            <td>
                <form:input path="quantity"/>
            </td>
        </tr>
        <tr>
            <td colspan="2">
                <c:if test="${!empty part.title}">
                    <input type="submit"
                           value="<spring:message text="Редактировать"/>"/>
                </c:if>
                <c:if test="${empty part.title}">
                    <input type="submit"
                           value="<spring:message text="Добавить"/>"/>
                </c:if>
            </td>
        </tr>
    </table>
</form:form>

</body>
</html>
