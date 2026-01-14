
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
    <h1>Editar local</h1>
    <br/>
    <br/>

    <form:form modelAttribute="locationEditFormDTO" method="post" action="/location/edit/${locationEditFormDTO.id}">
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
            <label for="cep">CEP:</label>
            <form:input path="cep"/>
            <form:errors path="cep" cssStyle="color: red"/>
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
        <form:errors path="*" cssStyle="color: red" />
        <br/>
        <br/>
        <div class="button__container">
            <button class="button" type="submit">Salvar</button>
            <a href="/" class="button-secondary" type="button">Cancelar</a>
        </div>
    </form:form>
    <script>
        const CEP_REGEX = /^[0-9]{8}$/;

        document.getElementById('cep').addEventListener("blur", ({ target }) => {
            searchCep(target.value);
        });

        function resetInputCEP() {
            document.getElementById('neighborhood').value=("");
            document.getElementById('city').value=("");
        }

        function callback(content) {
            if("erro" in content) {
                resetInputCEP();
                alert("CEP não encontrado.");
                return;
            }

            document.getElementById('city').value = content.localidade;
            document.getElementById('neighborhood').value = content.bairro;
        }

        function searchCep(valor) {
            const cep = valor.replace(/\D/g, '');

            if (cep.length < 8) {
                return;
            }

            if (!CEP_REGEX.test(cep)) {
                resetInputCEP();
                alert("Formato de CEP inválido.");
                return;
            }

            fetch("https://viacep.com.br/ws/"+ cep + "/json/")
                .then(response => {
                    if (!response.ok) {
                        throw new Error("Erro na requisição");
                    }
                    return response.json();
                })
                .then(data => {
                    if (data.erro) {
                        resetInputCEP();
                        alert("CEP não encontrado.");
                        return;
                    }

                    callback(data);
                })
                .catch(error => {
                    console.error(error);
                    resetInputCEP();
                    alert("Erro ao buscar o CEP.");
                });
        }
    </script>
</body>
</html>

