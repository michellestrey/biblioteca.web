<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<% request.setAttribute("currentPage", "home"); %>
<jsp:include page="header.jsp" />

<div class="container">
  <section class="hero">
    <h1>Sua biblioteca digital</h1>
    <p>Gerencie seu acervo de livros com simplicidade. Cadastre, consulte e organize sua coleção em um só lugar.</p>
    <div class="hero-actions">
   <a href="${pageContext.request.contextPath}/livros?action=cadastrar" class="btn btn-secondary">
    ✨ Cadastrar Livro
   </a>
      </a>
      <a href="${pageContext.request.contextPath}/livros?action=listar" class="btn btn-secondary">
    📖 Ver Acervo
</a>
    </div>
  </section>

  <div class="feature-grid">
    <div class="feature-card">
      <div class="feature-icon">📝</div>
      <h3>Cadastro Rápido</h3>
      <p>Adicione novos livros ao acervo em segundos com um formulário simples e intuitivo.</p>
    </div>
    <div class="feature-card">
      <div class="feature-icon">🔍</div>
      <h3>Busca Inteligente</h3>
      <p>Encontre qualquer livro pelo título, autor ou ISBN de forma instantânea.</p>
    </div>
    <div class="feature-card">
      <div class="feature-icon">⚡</div>
      <h3>Gestão Eficiente</h3>
      <p>Liste, edite e exclua registros com poucos cliques numa interface moderna.</p>
    </div>
  </div>
</div>

<jsp:include page="footer.jsp" />
