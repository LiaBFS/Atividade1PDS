package telas;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class BancoDeDados {

    private static final String URL = "jdbc:mysql://localhost:3306/sistemaMercado"; // <-- substitua '/cadastro_db' pelo seu banco de dados
    private static final String USUARIO = "root"; // <-- Substitua pelo seu usuário
    private static final String SENHA = "admin";   // <-- Substitua pela sua senha
    private static final String DRIVER = "com.mysql.cj.jdbc.Driver";

    // Método para obter uma conexão com o banco de dados
    public static Connection conectar() {
        Connection conexao = null;
        try {
            Class.forName(DRIVER);
            conexao = DriverManager.getConnection(URL, USUARIO, SENHA);
        } catch (ClassNotFoundException e) {
            System.err.println("Driver JDBC não encontrado: " + e.getMessage());
        } catch (SQLException e) {
            System.err.println("Erro ao conectar ao banco de dados: " + e.getMessage());
        }
        return conexao;
    }

    // Método para fechar a conexão
    public static void desconectar(Connection conexao) {
        if (conexao != null) {
            try {
                conexao.close();
            } catch (SQLException e) {
                System.err.println("Erro ao fechar a conexão: " + e.getMessage());
            }
        }
    }
}

