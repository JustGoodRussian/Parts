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

<p>Поиск по наименованию</p>

<c:url var="addAction" value="/search"/>
<p>
    <form:form action="${addAction}" modelAttribute="part">
        <table>
            <tr>
                <td>
                    <form:input path="title"/>
                </td>
            </tr>
            <tr>
                <td colspan="2">
                    <input type="submit"
                           value="<spring:message text="Найти"/>"/>
                </td>
            </tr>
        </table>
    </form:form>
</p>

<h2>Список комплектующих</h2>

<p>
    <a style="padding-right: 10px" href="<c:url value='/parts/${"all"}/1'/>">Все детали</a>
    <a style="padding-right: 10px" href="<c:url value='/parts/${"necessity"}/1'/>">Необходимые детали</a>
    <a style="padding-right: 10px" href="<c:url value='/parts/${"auxiliary"}/1'/>">Опциональные детали</a>
    <a style="padding-right: 10px" href="<c:url value="/parts/add"/>">Добавить деталь</a>
</p>

<p>
    <c:if test="${!empty allParts}">
        <table class="part">
            <tr>
                <th width="250">Наименование</th>
                <th width="150">Необходимость</th>
                <th width="150">Количество</th>
                <th width="100">Редактировать</th>
                <th width="100">Удалить</th>
            </tr>
            <c:forEach items="${allParts}" var="part">
                <tr>
                    <td>${part.title}</td>
                    <td>
                        <c:choose>
                            <c:when test="${part.necessity=='1'}">
                                Да
                            </c:when>
                            <c:otherwise>
                                Нет
                            </c:otherwise>
                        </c:choose>
                    </td>
                    <td>${part.quantity}</td>
                    <td><a href="<c:url value='/parts/edit/${part.id}'/>">Редактировать</a></td>
                    <td><a href="<c:url value='/parts/remove/${part.id}'/>">Удалить</a></td>
                </tr>
            </c:forEach>
        </table>
    </c:if>
</p>

<p>
    <c:if test="${numOfPages > 1}">
        <c:if test="${currentPage > 1}">
            <a href="<c:url value='/parts/${typeSort}/${currentPage - 1}'/>">Previous</a>
        </c:if>
        <c:forEach begin="1" end="${numOfPages}" var="i">
            <a href="<c:url value='/parts/${typeSort}/${i}'/>">${i}</a>
        </c:forEach>
        <c:if test="${currentPage < numOfPages}">
            <a href="<c:url value='/parts/${typeSort}/${currentPage + 1}'/>">Next</a>
        </c:if>
    </c:if>
</p>

<p>
    <c:if test="${!empty maxAssembling}">
        <table class="part">
            <tr>
                <td width="250">Можно собрать</td>
                <td width="150">${maxAssembling}</td>
                <td width="150">компьютеров</td>
            </tr>
        </table>
    </c:if>
</p>

</body>
</html>
