package biblioteca.web.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import biblioteca.web.model.Livro;
import biblioteca.web.service.LivroService;

@WebServlet("/livros")
public class LivroController extends HttpServlet {

    private LivroService service = new LivroService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String action = request.getParameter("action");

        if (action == null) {
            listar(request, response);
            return;
        }

        switch (action) {
            case "listar":
                listar(request, response);
                break;
            case "excluir":
                excluir(request, response);
                break;
            default:
                listar(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String action = request.getParameter("action");

        if ("cadastrar".equals(action)) {
            cadastrar(request, response);
        } else {
            response.sendRedirect("livros?action=listar");
        }
    }

    private void cadastrar(HttpServletRequest request, HttpServletResponse response)
            throws IOException {

        Livro livro = new Livro();
        livro.setTitulo(request.getParameter("titulo"));
        livro.setAutor(request.getParameter("autor"));
        livro.setAnoPublicacao(Integer.parseInt(request.getParameter("anoPublicacao")));
        livro.setIsbn(request.getParameter("isbn"));

        service.cadastrarLivro(livro);

        response.sendRedirect("livros?action=listar");//ENDPOINT
    }

    private void excluir(HttpServletRequest request, HttpServletResponse response)
            throws IOException {

        Long id = Long.parseLong(request.getParameter("id"));
        service.excluirLivroPorId(id);

        response.sendRedirect("livros?action=listar");
    }

    // MÉTODO DE TESTE DO BACKEND SEM DEPENDER DE JSP
    private void listar(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //HTML DIRETO NO NAVEGADOR!
        System.out.println("ENTROU NO CONTROLLER - TESTE PURO");

        try {
            List<Livro> livros = service.listarLivros();

            response.setContentType("text/html;charset=UTF-8");
            PrintWriter out = response.getWriter();
            
            out.println("<html><body>");
            out.println("<h1>Sucesso! O Backend respondeu corretamente.</h1>");
            out.println("<p>Quantidade de livros encontrados no banco: <strong>" + livros.size() + "</strong></p>");
            
            if (!livros.isEmpty()) {
                out.println("<h3>Lista de Livros:</h3><ul>");
                for (Livro l : livros) {
                    out.println("<li>ID: " + l.getId() + " - " + l.getTitulo() + " (" + l.getAutor() + ")</li>");
                }
                out.println("</ul>");
            }
            out.println("</body></html>");
            
        } catch (Exception e) {
            System.out.println("ERRO DENTRO DO MÉTODO LISTAR:");
            e.printStackTrace();
            
            response.setContentType("text/html;charset=UTF-8");
            response.getWriter().println("<h1>Erro no Backend ao listar! Olhe o console do Eclipse.</h1>");
        }
    }
}
