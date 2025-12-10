<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>Local</title>
    <style>
        table, th, td {
            border: 1px solid black;
            border-collapse: collapse;
        }
        th, td {
            padding: 8px;
        }
    </style>
</head>
<body>
 <main>
    <h1>Lista de Locais</h1>
    <a href="/location/create">Cadastrar Novo Local</a>
    <br/><br/>
    <table>
        <thead>
            <tr>
                <th>ID</th>
                <th>Nome</th>
                <th>Código</th>
                <th>Cadastrada em</th>
                <th>Atualizada em</th>
                <th>Editar</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach var="location" items="${locations}">
                <tr>
                    <td>${location.id()}</td>
                    <td>${location.name()}</td>
                    <td>${location.code()}</td>
                    <td>${location.createdAt()}</td>
                    <td>${location.updatedAt()}</td>
                    <td>
                        <a href="/location/edit/${location.id()}">editar</a>
                    </td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
 </main>
</body>
</html>
