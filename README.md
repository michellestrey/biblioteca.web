# Sistema de Biblioteca Web

Projeto acadêmico desenvolvido para fins de avaliação na faculdade. Consiste em um sistema legado em Java Web para gerenciamento e listagem de livros.

## Tecnologias
- Java
- Apache Tomcat
- MySQL

## Como Rodar o Projeto

1. Crie um banco de dados MySQL chamado `biblioteca`.
2. Como a configuração de conexão foi ocultada por segurança, crie manualmente o arquivo `ConnectionFactory.java` no caminho `src/main/java/biblioteca/web/config/` com o seguinte código:

```java
package biblioteca.web.config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {
    private static final String URL = "jdbc:mysql://localhost:3306/biblioteca";
    private static final String USER = "seu_usuario";
    private static final String PASS = "sua_senha";

    public static Connection getConnection() throws SQLException {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("Driver MySQL não encontrado", e);
        }
        return DriverManager.getConnection(URL, USER, PASS);
    }

    public static void main(String[] args) {
        try {
            Connection con = ConnectionFactory.getConnection();
            if (con != null) {
                System.out.println("SUCESSO: Conexão com o banco realizada!");
                con.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
```

3. Execute o método main da classe criada para testar a conexão local com o seu banco de dados.
4. Execute o projeto em um servidor Apache Tomcat através da IDE para acessar a interface web.
