package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UsuariosDAO {
	
	public void adicionarUsuario(Usuarios usuario) {
        String sql = "INSERT INTO usuarios (user, cpf, admin) VALUES (?, ?, ?)";
        Connection conexao = null;
        PreparedStatement pstm = null;

        try {
            conexao = BancoDeDados.conectar();
            pstm = conexao.prepareStatement(sql);
            pstm.setString(1, usuario.getUser());
            pstm.setString(2, usuario.getCpf());
            pstm.setBoolean(3, usuario.isAdmin());
            
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
	
	
	public Usuarios pesquisarUsuariosPorUserCPF(Usuarios usuario) {
        String sql = "SELECT * FROM usuarios where user = ? and cpf = ? and admin = ?";
        Connection conexao = null;
        PreparedStatement pstm = null;
        ResultSet rset = null; // Objeto que guarda o resultado da consulta 

        try {
            conexao = BancoDeDados.conectar();
            pstm = conexao.prepareStatement(sql);
            
            System.out.println(usuario.getUser());
            System.out.println(usuario.getCpf());
            System.out.println(usuario.isAdmin());
            pstm.setString(1, usuario.getUser());
            pstm.setString(2, usuario.getCpf());
            pstm.setBoolean(3, usuario.isAdmin());

            rset = pstm.executeQuery();
            System.out.println(rset);
            
            if (rset.next()) {System.out.println("sadouty");
            	
                usuario.setCpf(rset.getString("cpf"));
                usuario.setUser(rset.getString("user"));
                usuario.setAdmin(rset.getBoolean("admin"));
                	        return usuario;

            }
            else {
            	
            	return null;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
        	BancoDeDados.desconectar(conexao);
            // Fechar recursos
        }
		return null;
    }
	
	

}

