package biblioteca.web.controller;

import java.io.IOException;
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

    	 request.setCharacterEncoding("UTF-8");
    	 response.setCharacterEncoding("UTF-8");
    	 
        String action = request.getParameter("action");


        if (action == null || action.equals("listar")) {

            listar(request, response);


        } else if (action.equals("cadastrar")) {

            abrirCadastro(request, response);


        } else if (action.equals("excluir")) {

            excluir(request, response);


        } else {

            listar(request, response);

        }

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    
    	 request.setCharacterEncoding("UTF-8");
    	 response.setCharacterEncoding("UTF-8");
  
        String action = request.getParameter("action");


        if ("cadastrar".equals(action)) {

            cadastrar(request, response);


        } else {

            response.sendRedirect(
                request.getContextPath() + "/livros?action=listar"
            );

        }

    }


    private void abrirCadastro(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {


        request.getRequestDispatcher(
                "/WEB-INF/views/cadastrar.jsp"
        ).forward(request, response);

    }

    private void cadastrar(HttpServletRequest request, HttpServletResponse response)
            throws IOException {


        Livro livro = new Livro();


        livro.setTitulo(
                request.getParameter("titulo")
        );


        livro.setAutor(
                request.getParameter("autor")
        );


        livro.setAnoPublicacao(
                Integer.parseInt(
                    request.getParameter("anoPublicacao")
                )
        );


        livro.setIsbn(
                request.getParameter("isbn")
        );



        service.cadastrarLivro(livro);



        response.sendRedirect(
            request.getContextPath()
            + "/livros?action=listar&msg=sucesso"
        );

    }

    private void excluir(HttpServletRequest request, HttpServletResponse response)
            throws IOException {

        System.out.println("ENTROU NO EXCLUIR");

        String idRecebido = request.getParameter("id");

        System.out.println("ID: " + idRecebido);


        Long id = Long.parseLong(idRecebido);

        service.excluirLivroPorId(id);


        response.sendRedirect(
            request.getContextPath() + "/livros?action=listar&msg=excluido"
        );
    }

    private void listar(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {


        System.out.println("ENTROU NO CONTROLLER - LISTAR");


        List<Livro> livros = service.listarLivros();



        request.setAttribute(
                "livros",
                livros
        );

        request.getRequestDispatcher(
                "/WEB-INF/views/listar.jsp"
        ).forward(request, response);

    }


}