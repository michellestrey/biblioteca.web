package biblioteca.web.service;

import java.util.List;

import biblioteca.web.dao.LivroDAO;
import biblioteca.web.model.Livro;

public class LivroService {
	
	private LivroDAO livroDAO;
	

    public LivroService() {
        this.livroDAO = new LivroDAO();
    }

    public void cadastrarLivro(Livro livro) {

        if (livro.getTitulo() == null || livro.getTitulo().trim().isEmpty()) {
            throw new IllegalArgumentException("Título é obrigatório.");
        }

        if (livro.getAutor() == null || livro.getAutor().trim().isEmpty()) {
            throw new IllegalArgumentException("Autor é obrigatório.");
        }

        if (livro.getIsbn() == null || livro.getIsbn().trim().isEmpty()) {
            throw new IllegalArgumentException("ISBN é obrigatório.");
        }

        livroDAO.salvar(livro);
    }

    public List<Livro> listarLivros() {
        return livroDAO.listarTodos();
    }

    public boolean excluirLivro(String isbn) {

        if (isbn == null || isbn.trim().isEmpty()) {
            throw new IllegalArgumentException("ISBN inválido.");
        }

        return livroDAO.excluirPorIsbn(isbn);
    }
    
    public boolean excluirLivroPorId(Long id) {

        if (id == null) {
            throw new IllegalArgumentException("ID inválido.");
        }

        return livroDAO.excluirPorId(id);
    }

}

