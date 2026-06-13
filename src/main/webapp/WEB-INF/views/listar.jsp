<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<% request.setAttribute("currentPage", "listar"); %>

<jsp:include page="header.jsp" />


<div class="container">

  <div class="page-header" 
       style="display:flex; justify-content:space-between; align-items:flex-end; flex-wrap:wrap; gap:1rem;">
    <div>
      <h1 class="page-title gradient">Acervo de Livros</h1>
      <p class="page-subtitle">
        Gerencie todos os livros cadastrados na biblioteca
      </p>
    </div>

  <a href="${pageContext.request.contextPath}/livros?action=cadastrar"
   class="btn btn-primary">
   + Novo Livro
</a>

  </div>


  <% String msg = request.getParameter("msg"); %>

  <% if ("excluido".equals(msg)) { %>
    <div class="alert alert-success">
      ✓ Livro excluído com sucesso!
    </div>
  <% } %>

 <div class="stats">

    <div class="stat-card">
      <div class="stat-icon">📚</div>

      <div class="stat-value">
        <c:out value="${empty livros ? 0 : livros.size()}" />
      </div>

      <div class="stat-label">
        Livros no acervo
      </div>

    </div>


    <div class="stat-card">

      <div class="stat-icon">✍️</div>

      <div class="stat-value">
        <c:out value="${empty livros ? 0 : livros.size()}" />
      </div>

      <div class="stat-label">
        Registros ativos
      </div>

    </div>


    <div class="stat-card">

      <div class="stat-icon">⭐</div>

      <div class="stat-value">
        Ativo
      </div>

      <div class="stat-label">
        Status do sistema
      </div>

    </div>

  </div>



  <div class="table-wrapper">


    <div class="table-toolbar">

      <input type="text"
             id="searchInput"
             class="search-input"
             placeholder="🔍 Buscar por título, autor ou ISBN..."
             onkeyup="filterTable()">


      <span class="badge">
        <c:out value="${empty livros ? 0 : livros.size()}" /> livros
      </span>

    </div>
    <c:choose>
      <c:when test="${empty livros}">
          <div class="empty-state">
            <div class="empty-state-icon">
            📭
          </div>

          <h3>Nenhum livro cadastrado</h3>
            <p>
            Comece adicionando o primeiro livro ao acervo.
          </p>

          <br>

          <a href="${pageContext.request.contextPath}/livros?action=cadastrar"
             class="btn btn-primary">
             + Cadastrar Primeiro Livro
          </a>

        </div>

      </c:when>



      <c:otherwise>


        <table id="booksTable">
            <thead>

            <tr>

              <th>Título / Autor</th>
              <th>Ano</th>
              <th>ISBN</th>
              <th style="text-align:right;">
                Ações
              </th>

            </tr>

          </thead>



          <tbody>


          <c:forEach var="livro" items="${livros}">


            <tr>


              <td>

                <div class="book-title">
                  <c:out value="${livro.titulo}" />
                </div>

                <div class="book-author">
                  <c:out value="${livro.autor}" />
                </div>

              </td>


              <td>
                <c:out value="${livro.anoPublicacao}" />
              </td>


              <td>
                <c:out value="${livro.isbn}" />
              </td>



              <td style="text-align:right;">


        <form action="${pageContext.request.contextPath}/livros"
           method="get"
            style="display:inline;"
              onsubmit="return confirm('Tem certeza que deseja excluir este livro?');">

             <input type="hidden" name="action" value="excluir">

             <input type="hidden" name="id" value="${livro.id}">

               <button type="submit" class="btn btn-danger">
                                                  🗑 Excluir
                </button>

            </form>
              </td>
            </tr>
        </c:forEach>

          </tbody>

        </table>
        
      </c:otherwise>

    </c:choose>

  </div>

</div>

<script>

function filterTable() {

  const input =
      document.getElementById('searchInput').value.toLowerCase();

  const rows =
      document.querySelectorAll('#booksTable tbody tr');

  rows.forEach(row => {

      const text =
          row.textContent.toLowerCase();

      row.style.display =
          text.includes(input) ? '' : 'none';

  });

}

</script>

<jsp:include page="footer.jsp" />
