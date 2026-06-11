<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Cadastro de Livro</title>

    <style>
        body {
            font-family: Arial, sans-serif;
            margin: 30px;
        }

        input {
            display: block;
            margin-bottom: 12px;
            padding: 6px;
            width: 300px;
        }

        button {
            padding: 8px 15px;
            cursor: pointer;
        }

        .container {
            max-width: 400px;
        }
    </style>
</head>

<body>

<div class="container">

    <h2>Cadastro de Livro</h2>

    <!-- ainda sem backend, action vazia -->
    <form method="post" action="#">

        <label>Título</label>
        <input type="text" name="titulo"  maxlength= "50" placeholder="Digite o título">

        <label>Autor</label>
        <input type="text" name="autor" maxlength = "80" placeholder="Digite o autor">

        <label>Ano de publicação</label>
        <input type="number" name="ano" min = "1700" max= "2030" step= "1" placeholder="Ex: 2020">

        <label>ISBN</label>
        <input type="text" name="isbn" placeholder="Ex: 978123456">

        <button type="submit">Salvar</button>

    </form>

</div>

</body>
</html>