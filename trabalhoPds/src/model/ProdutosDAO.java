package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ProdutosDAO {
	
	public void adicionarProduto(Produtos produto) {
        String sql = "INSERT INTO produtos (nome, preco) VALUES (?, ?)";
        Connection conexao = null;
        PreparedStatement pstm = null;

        try {
            conexao = BancoDeDados.conectar();
            pstm = conexao.prepareStatement(sql);
            pstm.setString(1, produto.getNome());
            pstm.setString(2, produto.getPreco());
            
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
	
	
	
	public void atualizarProduto(Produtos produto, String nomeAntigo) {
	    String sql = "UPDATE produtos SET nome = ?, preco = ? WHERE nome = ?";
	    Connection conexao = null;
	    PreparedStatement pstm = null;

	    try {
	        conexao = BancoDeDados.conectar();
	        pstm = conexao.prepareStatement(sql);
	        pstm.setString(1, produto.getNome());
	        pstm.setString(2, produto.getPreco());
	        pstm.setString(3, nomeAntigo);
	        pstm.executeUpdate();
	    } catch (SQLException e) {
	        e.printStackTrace();
	    } finally {
	        BancoDeDados.desconectar(conexao);
	    }
	}
	

    // DELETE
	
    public void excluirProduto(String nome) {
        String sql = "DELETE FROM produtos WHERE nome = ?";
        Connection conexao = null;
        PreparedStatement pstm = null;

        try {
            conexao = BancoDeDados.conectar();
            pstm = conexao.prepareStatement(sql);
            pstm.setString(1, nome);
            pstm.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
        	BancoDeDados.desconectar(conexao);
        }
    }
    
    
    

}


