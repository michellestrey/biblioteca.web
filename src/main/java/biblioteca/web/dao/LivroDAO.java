package biblioteca.web.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import biblioteca.web.config.ConnectionFactory;
import biblioteca.web.model.Livro;

public class LivroDAO {

    public void salvar(Livro livro) {
  
    	String sql = "INSERT INTO livro " +
                "(titulo, autor, anoPublicacao, isbn) " +
                "VALUES (?, ?, ?, ?)";

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, livro.getTitulo());
            stmt.setString(2, livro.getAutor());
            stmt.setInt(3, livro.getAnoPublicacao());
            stmt.setString(4, livro.getIsbn());

            stmt.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao salvar livro", e);
        }
    }

    public List<Livro> listarTodos() {
    	System.out.println("ENTROU NO DAO");
        List<Livro> livros = new ArrayList<>();

        String sql = "SELECT id, titulo, autor, anoPublicacao, isbn " +
                "FROM livro " +
                "ORDER BY titulo";

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {

                Livro livro = new Livro();

                livro.setId(rs.getLong("id"));
                livro.setTitulo(rs.getString("titulo"));
                livro.setAutor(rs.getString("autor"));
                livro.setAnoPublicacao(rs.getInt("anoPublicacao"));
                livro.setIsbn(rs.getString("isbn"));

                livros.add(livro);
            }

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao listar livros, livros não incluídos", e);
        }

        return livros;
    }

    public boolean excluirPorIsbn(String isbn) {

        String sql = "DELETE FROM livro WHERE isbn = ?";

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, isbn);

            return stmt.executeUpdate() > 0;

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao excluir livro", e);
        }
    }
    
    public boolean excluirPorId(Long id) {

        String sql = "DELETE FROM livro WHERE id = ?";

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setLong(1, id);

            return stmt.executeUpdate() > 0;

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao excluir livro", e);
        }
    }
}

