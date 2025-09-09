package telas;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class UsuariosDAO {
	
	public void adicionarUsuario(Usuarios usuario) {
        String sql = "INSERT INTO usuarios (user, cpf) VALUES (?, ?)";
        Connection conexao = null;
        PreparedStatement pstm = null;

        try {
            conexao = BancoDeDados.conectar();
            pstm = conexao.prepareStatement(sql);
            pstm.setString(1, usuario.getUser());
            pstm.setString(2, usuario.getCpf());
            
            pstm.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
        	BancoDeDados.desconectar(conexao);
            if (pstm != null) {
                try {
                    pstm.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }
	
	

}

