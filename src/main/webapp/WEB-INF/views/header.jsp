<%@ page contentType="text/html;charset=UTF-8" language="java"%>

<%
  String currentPage = (String) request.getAttribute("currentPage");
  if (currentPage == null) currentPage = "";
%>

<!DOCTYPE html>
<html lang="pt-BR">

<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Biblioteca Web</title>

<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/styles.css">
</head>

<body>

	<nav class="navbar">

		<div class="navbar-inner">

			<a href="${pageContext.request.contextPath}/home" class="logo"> <span
				class="logo-icon">📚</span> <span>Biblioteca</span>
			</a>


			<div class="nav-links">


				<a href="${pageContext.request.contextPath}/home"
					class="<%= currentPage.equals("home") ? "active" : "" %>">
					Início </a> <a
					href="${pageContext.request.contextPath}/livros?action=listar"
					class="<%= currentPage.equals("listar") ? "active" : "" %>">
					Livros </a> <a
					href="${pageContext.request.contextPath}/livros?action=cadastrar"
					class="<%= currentPage.equals("cadastrar") ? "active" : "" %>">
					Cadastrar </a>


			</div>

		</div>

	</nav>