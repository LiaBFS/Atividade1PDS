package controller;

import model.UsuariosDAO;
import model.Usuarios;
import model.Supermercado;
import view.TelaCadastro;
import view.TelaLogin;

import javax.swing.*;

public class CadastroController {
    private TelaCadastro tela;
    private UsuariosDAO usuariosDAO;
    private Supermercado supermercado;

    public CadastroController(TelaCadastro tela, Supermercado supermercado) {
        this.tela = tela;
        this.supermercado = supermercado;
        this.usuariosDAO = new UsuariosDAO();
    }

    public void cadastrar(String nome, String cpf, boolean admin) {
        Usuarios usuario = new Usuarios(nome, cpf, admin);
        usuariosDAO.inserirUsuario(usuario);

        JOptionPane.showMessageDialog(tela, "Usuário cadastrado com sucesso!");
        new TelaLogin(supermercado).setVisible(true);
        tela.dispose();
    }
}
