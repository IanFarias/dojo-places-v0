
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page import="java.util.Date" %>

<html>
<head>
    <title>Local | Cadastro</title>
    <style>

        form {
            max-width: 360px;
        }

        input {
            height: 30px;
        }

        .input__container {
            display: flex;
            flex-direction: column;
        }

        .button__container {
            display: flex;
            flex-direction: column;
            gap: 8px;
        }

        .button-secondary,
        .button {
            display: flex;
            align-items: center;
            justify-content: center;
            height: 40px;
            cursor: pointer;
            border: none;
            background-color: #4a4ad1;
            color: white;
            font-size: 16px;
            border-radius: 6px;
            text-decoration: none;
        }

        .button:hover {
            background-color: #3333cb;
        }

        .button-secondary {
            background-color: white;
            color: #0b0b0b;
            border: 1px solid darkgrey;
        }

        .button-secondary:hover{
            background-color: darkgrey;
            color: white;
        }
    </style>
</head>
<body>
<h1>Cadastro de Local</h1>

<br/>
<br/>

<form:form modelAttribute="locationFormDTO" method="post" action="/location/create">
    <div class="input__container">
        <label>Código:</label>

        <form:input path="code"/>
        <form:errors path="code" cssStyle="color: red"/>

    </div>
    <br/>
    <div class="input__container">
        <label>Nome:</label>
        <form:input path="name"/>
        <form:errors path="name" cssStyle="color: red"/>
    </div>
    <br/>
    <div class="input__container">
        <label>Bairro:</label>
        <form:input path="neighborhood"/>
        <form:errors path="neighborhood" cssStyle="color: red"/>
    </div>
    <br/>
    <div class="input__container">
        <label>Cidade:</label>
        <form:input path="city"/>
        <form:errors path="city" cssStyle="color: red"/>
    </div>
    <br/>
    <br/>
    <div class="button__container">
        <button class="button" type="submit">Cadastrar</button>
    </div>
</form:form>

</body>
</html>

