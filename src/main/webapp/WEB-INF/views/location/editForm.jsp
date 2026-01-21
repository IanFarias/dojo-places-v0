
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page import="java.util.Date" %>

<html>
<head>
    <title>Local | Cadastro</title>
    <style>
        .button__container {
            display: flex;
            gap: 8px;
        }
    </style>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.8/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-sRIl4kxILFvY47J16cr9ZwB07vP4J8+LH7qKQnuqkuIAvNWLzeN8tE5YBujZqJLB" crossorigin="anonymous">
</head>
<body>
    <main class="container py-5 d-flex flex-column align-items-center">
        <h1 class="mb-4">Editar local</h1>

        <form:form modelAttribute="locationEditFormDTO" method="post" action="/location/edit/${locationEditFormDTO.id}" cssClass="w-50 m-4">

            <div class="row">
                <div class="col input__container">
                    <label class="form-label">Código:</label>

                    <form:input path="code" cssClass="form-control"/>
                    <form:errors path="code" cssStyle="color: red"/>

                </div>
                <div class="col input__container">
                    <label class="form-label">Nome:</label>
                    <form:input path="name" cssClass="form-control"/>
                    <form:errors path="name" cssStyle="color: red"/>
                </div>
            </div>

            <br/>
            <div class="input__container">
                <label for="cep" class="form-label">CEP:</label>
                <form:input path="cep" cssClass="form-control"/>
                <form:errors path="cep" cssStyle="color: red"/>
            </div>
            <br/>
            <div class="row">
                <div class="col input__container">
                    <label class="form-label">Bairro:</label>
                    <form:input path="neighborhood" cssClass="form-control"/>
                    <form:errors path="neighborhood" cssStyle="color: red"/>
                </div>
                <br/>
                <div class="col input__container">
                    <label class="form-label">Cidade:</label>
                    <form:input path="city" cssClass="form-control"/>
                    <form:errors path="city" cssStyle="color: red"/>
                </div>
            </div>
            <span class="d-block mt-4"><form:errors path="" cssStyle="color: red" /></span>
            <div class="button__container mt-5">
                <button class="btn btn-primary btn-lg" type="submit">Salvar</button>
                <a href="/" class="btn btn-light btn-lg" type="submit">Cancelar</a>
            </div>
        </form:form>
    </main>
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
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.8/dist/js/bootstrap.bundle.min.js" integrity="sha384-FKyoEForCGlyvwx9Hj09JcYn3nv7wiPVlz7YYwJrWVcXK/BmnVDxM+D2scQbITxI" crossorigin="anonymous"></script>

</body>
</html>

