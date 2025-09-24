package view;

import controller.LoginController;
import model.Supermercado;

import javax.swing.*;
import java.awt.*;

public class TelaLogin extends JFrame {
    private JTextField txtUser;
    private JTextField txtCpf;
    private JButton btnLogin;
    private JButton btnCadastrar;
    private LoginController controller;

    public TelaLogin(Supermercado supermercado) {
        super("Login - Sistema de Mercado");

        // Controller
        controller = new LoginController(this, supermercado);

        // Configurações da tela
        setSize(400, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Painel principal
        JPanel panel = new JPanel(new GridLayout(3, 2, 5, 5));

        panel.add(new JLabel("Usuário:"));
        txtUser = new JTextField();
        panel.add(txtUser);

        panel.add(new JLabel("CPF:"));
        txtCpf = new JTextField();
        panel.add(txtCpf);

        btnLogin = new JButton("Login");
        btnCadastrar = new JButton("Cadastrar");

        panel.add(btnLogin);
        panel.add(btnCadastrar);

        add(panel, BorderLayout.CENTER);

        // Ações dos botões
        btnLogin.addActionListener(e -> {
            String user = txtUser.getText().trim();
            String cpf = txtCpf.getText().trim();
            controller.login(user, cpf);
        });

        btnCadastrar.addActionListener(e -> controller.abrirCadastro());
    }

    // Método main apenas para rodar o sistema
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            Supermercado supermercado = new Supermercado();
            new TelaLogin(supermercado).setVisible(true);
        });
    }
}