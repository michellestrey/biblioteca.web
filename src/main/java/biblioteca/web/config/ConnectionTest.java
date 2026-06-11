package biblioteca.web.config;

import java.sql.Connection;

public class ConnectionTest {
	
	public static void main (String [] args) {
	
	
	try {
		Connection conn = ConnectionFactory.getConnection();
		System.out.println("Conexão ok");
		conn.close();
		
	} catch (Exception e ) {
		System.out.println("Erro de conexão");
		e.printStackTrace();
	}
  }
}
