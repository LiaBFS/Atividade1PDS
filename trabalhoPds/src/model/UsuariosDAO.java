package model;

import model.Usuarios;
import model.BancoDeDados;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UsuariosDAO {

    // Inserir usuário
    public void inserirUsuario(Usuarios usuario) {
        String sql = "INSERT INTO usuarios (user, cpf, admin) VALUES (?, ?, ?)";
        try (Connection conn = BancoDeDados.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, usuario.getUser());
            stmt.setString(2, usuario.getCpf());
            stmt.setBoolean(3, usuario.isAdmin());

            stmt.executeUpdate();
            System.out.println("Usuário cadastrado com sucesso!");
        } catch (SQLException e) {
            System.err.println("Erro ao inserir usuário: " + e.getMessage());
        }
    }

    // Buscar usuário por CPF e nome (login)
    public Usuarios buscarUsuario(String user, String cpf) {
        String sql = "SELECT * FROM usuarios WHERE user = ? AND cpf = ?";
        try (Connection conn = BancoDeDados.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, user);
            stmt.setString(2, cpf);

            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return new Usuarios(
                        rs.getString("user"),
                        rs.getString("cpf"),
                        rs.getBoolean("admin")
                );
            }
        } catch (SQLException e) {
            System.err.println("Erro ao buscar usuário: " + e.getMessage());
        }
        return null;
    }

    // Listar todos os usuários
    public List<Usuarios> listarUsuarios() {
        List<Usuarios> usuarios = new ArrayList<>();
        String sql = "SELECT * FROM usuarios";
        try (Connection conn = BancoDeDados.conectar();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Usuarios u = new Usuarios(
                        rs.getString("user"),
                        rs.getString("cpf"),
                        rs.getBoolean("admin")
                );
                usuarios.add(u);
            }
        } catch (SQLException e) {
            System.err.println("Erro ao listar usuários: " + e.getMessage());
        }
        return usuarios;
    }

    // Remover usuário
    public void removerUsuario(String cpf) {
        String sql = "DELETE FROM usuarios WHERE cpf = ?";
        try (Connection conn = BancoDeDados.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, cpf);
            stmt.executeUpdate();
            System.out.println("Usuário removido!");
        } catch (SQLException e) {
            System.err.println("Erro ao remover usuário: " + e.getMessage());
        }
    }
}