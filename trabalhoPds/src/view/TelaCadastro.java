package view;

import controller.CadastroController;
import model.Supermercado;

import javax.swing.*;
import java.awt.*;

public class TelaCadastro extends JFrame {
    private JTextField txtUser;
    private JTextField txtCpf;
    private JCheckBox chkAdmin;
    private JButton btnCadastrar;
    private JButton btnVoltar;

    private CadastroController controller;

    public TelaCadastro(Supermercado supermercado) {
        super("Cadastro de Usuário");

        controller = new CadastroController(this, supermercado);

        setSize(400, 250);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel(new GridLayout(4, 2, 5, 5));

        panel.add(new JLabel("Usuário:"));
        txtUser = new JTextField();
        panel.add(txtUser);

        panel.add(new JLabel("CPF:"));
        txtCpf = new JTextField();
        panel.add(txtCpf);

        panel.add(new JLabel("Administrador?"));
        chkAdmin = new JCheckBox("Sim");
        panel.add(chkAdmin);

        btnCadastrar = new JButton("Cadastrar");
        btnVoltar = new JButton("Voltar");

        panel.add(btnCadastrar);
        panel.add(btnVoltar);

        add(panel, BorderLayout.CENTER);

        // Eventos
        btnCadastrar.addActionListener(e -> {
            String user = txtUser.getText().trim();
            String cpf = txtCpf.getText().trim();
            boolean admin = chkAdmin.isSelected();

            controller.cadastrar(user, cpf, admin);
        });

        btnVoltar.addActionListener(e -> {
            dispose(); // fecha cadastro
            new TelaLogin(supermercado).setVisible(true); // volta login
        });
    }

    // Getters para o controller usar se precisar
    public JTextField getTxtUser() { return txtUser; }
    public JTextField getTxtCpf() { return txtCpf; }
    public JCheckBox getChkAdmin() { return chkAdmin; }
}