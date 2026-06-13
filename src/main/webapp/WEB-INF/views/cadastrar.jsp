<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<% request.setAttribute("currentPage", "cadastrar"); %>

<jsp:include page="header.jsp" />


<div class="container" style="max-width: 720px;">

  <div class="page-header">
    <h1 class="page-title gradient">Cadastrar Livro</h1>
    <p class="page-subtitle">
      Adicione um novo título ao acervo da biblioteca
    </p>
  </div>


  <% String msg = request.getParameter("msg"); %>

  <% if ("sucesso".equals(msg)) { %>

    <div class="alert alert-success">
      ✓ Livro cadastrado com sucesso!
    </div>

  <% } else if ("erro".equals(msg)) { %>

    <div class="alert alert-error">
      ✗ Erro ao cadastrar o livro. Tente novamente.
    </div>

  <% } %>



  <div class="card">

    <form action="${pageContext.request.contextPath}/livros?action=cadastrar"
          method="post">


      <div class="form-group">

        <label class="form-label" for="titulo">
          Título do Livro
        </label>

        <input type="text"
               id="titulo"
               name="titulo"
               class="form-input"
               placeholder="Ex: Dom Casmurro"
               minlength="3"
               maxlength="60"
               required>

      </div>

      <div class="form-group">

        <label class="form-label" for="autor">
          Autor
        </label>

        <input type="text"
               id="autor"
               name="autor"
               class="form-input"
               placeholder="Ex: Machado de Assis"
               minlength="3"
               maxlength ="50"
               required>

      </div>


      <div class="form-row">


        <div class="form-group">

          <label class="form-label" for="anoPublicacao">
            Ano de Publicação
          </label>

          <input type="number"
                 id="anoPublicacao"
                 name="anoPublicacao"
                 class="form-input"
                 placeholder="1899"
                 min="1000"
                 max="2100"
                 required>

        </div>


        <div class="form-group">

          <label class="form-label" for="isbn">
            ISBN
          </label>
             <input type="text"
                    id="isbn"
                    name="isbn"
                    class="form-input"
                    placeholder="Somente números"
                    minlength="10"
                    maxlength="13"
                    pattern="[0-9]+"
                    title="Digite apenas números no ISBN"
                    required>

        </div>


      </div>


      <div class="form-actions">


        <button type="submit" class="btn btn-primary">
          💾 Salvar Livro
        </button>



        <a href="${pageContext.request.contextPath}/livros?action=listar"
           class="btn btn-secondary">
          Cancelar
        </a>


      </div>


    </form>

  </div>


</div>


<jsp:include page="footer.jsp" />
