package view;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import model.Usuarios;
import model.UsuariosDAO;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import java.awt.Font;
import javax.swing.ButtonGroup;
import javax.swing.SwingConstants;
import javax.swing.JRadioButton;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class TelaLogin extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTextField tfUsuario;
    private JTextField tfCPF;
    private JTextField tfsenhaAdmin;

    private UsuariosDAO usuarioDAO;  // DAO
    private Usuarios usuario = new Usuarios();

    private void abrirTelaAdmin() {
        dispose(); 
        new TelaAdmin().setVisible(true);
    }

    private void abrirTelaCompras() {
        dispose(); 
        new TelaCompras().setVisible(true);
    }

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    TelaCadastro frame = new TelaCadastro();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public TelaLogin() {
        // >>>>>>> Instanciando o DAO aqui <<<<<<
        usuarioDAO = new UsuariosDAO();

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 450, 300);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JLabel lblBemVindo = new JLabel("Crie sua conta");
        lblBemVindo.setHorizontalAlignment(SwingConstants.CENTER);
        lblBemVindo.setFont(new Font("Tahoma", Font.BOLD, 15));
        lblBemVindo.setBounds(134, 11, 165, 38);
        contentPane.add(lblBemVindo);

        JLabel lblUsuario = new JLabel("Usuário:");
        lblUsuario.setFont(new Font("Tahoma", Font.PLAIN, 13));
        lblUsuario.setBounds(10, 59, 69, 23);
        contentPane.add(lblUsuario);

        tfUsuario = new JTextField();
        tfUsuario.setBounds(73, 61, 351, 20);
        contentPane.add(tfUsuario);
        tfUsuario.setColumns(10);

        JLabel lblCPF = new JLabel("CPF:");
        lblCPF.setFont(new Font("Tahoma", Font.PLAIN, 13));
        lblCPF.setBounds(10, 93, 69, 23);
        contentPane.add(lblCPF);

        tfCPF = new JTextField();
        tfCPF.setColumns(10);
        tfCPF.setBounds(73, 93, 351, 20);
        contentPane.add(tfCPF);

        JLabel lblAdmin = new JLabel("Deseja se cadastrar como administrador?");
        lblAdmin.setHorizontalAlignment(SwingConstants.CENTER);
        lblAdmin.setFont(new Font("Tahoma", Font.BOLD, 13));
        lblAdmin.setBounds(73, 124, 291, 38);
        contentPane.add(lblAdmin);

        ButtonGroup g = new ButtonGroup();

        JRadioButton rdbtnCliente = new JRadioButton("Não, sou um cliente.");
        rdbtnCliente.setBounds(37, 165, 159, 23);
        contentPane.add(rdbtnCliente);

        JRadioButton rdbtnAdministrador = new JRadioButton("Sim, sou administrador.");
        rdbtnAdministrador.setBounds(239, 165, 165, 23);
        contentPane.add(rdbtnAdministrador);

        g.add(rdbtnAdministrador);
        g.add(rdbtnCliente);

        tfsenhaAdmin = new JTextField();
        tfsenhaAdmin.setBounds(167, 198, 197, 20);
        contentPane.add(tfsenhaAdmin);
        tfsenhaAdmin.setColumns(10);
        tfsenhaAdmin.setVisible(false);

        JLabel lblSenhaAdmin = new JLabel("Senha de Acesso:");
        lblSenhaAdmin.setBounds(47, 201, 105, 14);
        contentPane.add(lblSenhaAdmin);
        lblSenhaAdmin.setVisible(false);

        rdbtnAdministrador.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (rdbtnAdministrador.isSelected()) {
                    tfsenhaAdmin.setVisible(true);
                    lblSenhaAdmin.setVisible(true);
                }
            }
        });

        rdbtnCliente.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (rdbtnCliente.isSelected()) {
                    tfsenhaAdmin.setVisible(false);
                    lblSenhaAdmin.setVisible(false);
                }
            }
        });

        JButton btnCadastrar = new JButton("Cadastrar >");
        btnCadastrar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                String nome = tfUsuario.getText().trim();
                String cpf = tfCPF.getText().trim();
                String senhaAdm = tfsenhaAdmin.getText().trim();

                boolean tipoSelecionado = rdbtnAdministrador.isSelected() || rdbtnCliente.isSelected();

                if (nome.isEmpty() || cpf.isEmpty() || !tipoSelecionado) {
                    JOptionPane.showMessageDialog(null, "Todos os campos do Cadastro são obrigatórios.", "Erro de Cadastro", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                if (cpf.length() != 11) {
                    JOptionPane.showMessageDialog(null, "Adicione um CPF válido. Formato: XXXXXXXXXXX", "Erro de Cadastro", JOptionPane.ERROR_MESSAGE);
                    tfCPF.setText("");
                    return;
                }

                if (rdbtnAdministrador.isSelected()) {
                    if (!senhaAdm.equals("admin")) {
                        JOptionPane.showMessageDialog(null, "Acesso Negado.", "Erro de Senha", JOptionPane.ERROR_MESSAGE);
                        tfsenhaAdmin.setText("");
                        return;
                    }

                    Usuarios novoUsuario = new Usuarios();
                    novoUsuario.setUser(tfUsuario.getText());
                    novoUsuario.setCpf(tfCPF.getText());

                    usuarioDAO.adicionarUsuario(novoUsuario);

                    JOptionPane.showMessageDialog(null, "Cadastro realizado!");
                    abrirTelaAdmin();
                }

                if (rdbtnCliente.isSelected()) {
                    Usuarios novoUsuario = new Usuarios();
                    novoUsuario.setUser(tfUsuario.getText());
                    novoUsuario.setCpf(tfCPF.getText());

                    usuarioDAO.adicionarUsuario(novoUsuario);

                    JOptionPane.showMessageDialog(null, "Cadastro realizado!");
                    abrirTelaCompras();
                }
            }
        });

        btnCadastrar.setBounds(164, 229, 105, 23);
        contentPane.add(btnCadastrar);
    }
}
