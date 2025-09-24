package controller;

import model.UsuariosDAO;
import model.Usuarios;
import model.Supermercado;
import view.TelaLogin;
import view.TelaCadastro;
import view.TelaAdmin;
import view.TelaCompras;

import javax.swing.*;

public class LoginController {
    private TelaLogin tela;
    private UsuariosDAO usuariosDAO;
    private Supermercado supermercado;

    public LoginController(TelaLogin tela, Supermercado supermercado) {
        this.tela = tela;
        this.supermercado = supermercado;
        this.usuariosDAO = new UsuariosDAO();
    }

    public void login(String user, String cpf) {
        Usuarios usuario = usuariosDAO.buscarUsuario(user, cpf);

        if (usuario != null) {
            supermercado.setUsuarioLogado(usuario);
            JOptionPane.showMessageDialog(tela, "Login realizado com sucesso!");

            if (usuario.isAdmin()) {
                new TelaAdmin(supermercado).setVisible(true);
            } else {
                new TelaCompras(supermercado, usuario).setVisible(true);
            }
            tela.dispose();
        } else {
            JOptionPane.showMessageDialog(tela, "Usuário não encontrado!");
        }
    }

    public void abrirCadastro() {
        new TelaCadastro(supermercado).setVisible(true);
        tela.dispose();
    }
}