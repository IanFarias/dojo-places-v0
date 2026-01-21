<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>Local</title>
    <style>
        th, td {
            padding: 8px;
        }

        .delete-button {
            cursor: pointer;
            background-color: transparent;
            border: none;
            color: red;
            padding: 0;
        }
    </style>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.8/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-sRIl4kxILFvY47J16cr9ZwB07vP4J8+LH7qKQnuqkuIAvNWLzeN8tE5YBujZqJLB" crossorigin="anonymous">
</head>
<body>
 <main class="container py-5">
    <h1 class="fw-bold">Locais</h1>
    <a href="/location/create" class="btn btn-primary mt-4">Cadastrar Novo Local</a>
    <br/><br/>
    <table class="table align-middle table-bordered table-striped">
        <thead>
            <tr>
                <th>ID</th>
                <th>Nome</th>
                <th>Código</th>
                <th>Cadastrada em</th>
                <th>Atualizada em</th>
                <th>Editar</th>
                <th class="center">Excluir</th>
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
                    <td align="center">
                        <button type="button" class="delete-button" data-id="${location.id()}">
                            <svg xmlns="http://www.w3.org/2000/svg" width="24" height="24" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2" stroke-linecap="round" stroke-linejoin="round" class="lucide lucide-trash2-icon lucide-trash-2"><path d="M10 11v6"/><path d="M14 11v6"/><path d="M19 6v14a2 2 0 0 1-2 2H7a2 2 0 0 1-2-2V6"/><path d="M3 6h18"/><path d="M8 6V4a2 2 0 0 1 2-2h4a2 2 0 0 1 2 2v2"/></svg>
                        </button>
                    </td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
 </main>
<script>
    const buttons = document.querySelectorAll('.delete-button');
    buttons.forEach(button => {
        button.addEventListener('click', async (event) => {
            const locationId = button.getAttribute('data-id');

            const confirmDelete = confirm("Deseja deletar este local?");

            if(!confirmDelete) return;

            const response = await fetch("/location/delete/" + locationId, {
                method: 'DELETE'
            });

            if(response.status === 200) {
                window.location.reload();
            }
        });
    })

</script>
 <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.8/dist/js/bootstrap.bundle.min.js" integrity="sha384-FKyoEForCGlyvwx9Hj09JcYn3nv7wiPVlz7YYwJrWVcXK/BmnVDxM+D2scQbITxI" crossorigin="anonymous"></script>
</body>
</html>
