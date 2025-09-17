package view;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.text.MaskFormatter;

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
import javax.swing.JFormattedTextField;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class TelaLogin extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTextField tfUsuario;
    private JTextField tfCPF;

    private UsuariosDAO usuarioDAO;  // DAO
    private Usuarios usuario = new Usuarios();
    
    private void abrirTelaCadastro() {
    	dispose();
    	new TelaCadastro().setVisible(true);
    }

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
                    TelaLogin frame = new TelaLogin();
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

        JLabel lblBemVindo = new JLabel("Bem Vindo!");
        lblBemVindo.setHorizontalAlignment(SwingConstants.CENTER);
        lblBemVindo.setFont(new Font("Tahoma", Font.BOLD, 15));
        lblBemVindo.setBounds(134, 11, 165, 38);
        contentPane.add(lblBemVindo);

        JLabel lblUsuario = new JLabel("Usuário:");
        lblUsuario.setFont(new Font("Tahoma", Font.PLAIN, 13));
        lblUsuario.setBounds(10, 76, 69, 23);
        contentPane.add(lblUsuario);

        tfUsuario = new JTextField();
        tfUsuario.setBounds(73, 78, 351, 20);
        contentPane.add(tfUsuario);
        tfUsuario.setColumns(10);

        JLabel lblCPF = new JLabel("CPF:");
        lblCPF.setFont(new Font("Tahoma", Font.PLAIN, 13));
        lblCPF.setBounds(10, 110, 69, 23);
        contentPane.add(lblCPF);
        
        MaskFormatter cpfMask = null;

		try {

		    cpfMask = new MaskFormatter("###########"); // 11 números

		    cpfMask.setPlaceholderCharacter('_'); // mostra "_" nos espaços vazios

		} catch (Exception e) {

		    e.printStackTrace();

		}

         JFormattedTextField tfCPF = new JFormattedTextField(cpfMask);
        tfCPF.setColumns(10);
        tfCPF.setBounds(73, 110, 351, 20);
        contentPane.add(tfCPF);

        ButtonGroup g = new ButtonGroup();

        JButton btnIrCadastro = new JButton("Criar Conta >");
        btnIrCadastro.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                abrirTelaCadastro();
                }
            
        });

        btnIrCadastro.setBounds(227, 228, 112, 23);
        contentPane.add(btnIrCadastro);
        
        JButton btnEntrar = new JButton("Entrar >");
        btnEntrar.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		
        		String user = tfUsuario.getText().trim();
		        String cpf = tfCPF.getText().trim();
		        
		        System.out.println(user);
		        System.out.println(cpf);
		      
		        if (user.isEmpty() || cpf.isEmpty()) {
		            
		        	JOptionPane.showMessageDialog(null, "Todos os campos do Login são obrigatórios.", "Erro de Login", JOptionPane.ERROR_MESSAGE);
		            return;
		        }

		        // cria objeto usuario com email e senha
		        Usuarios usuarioLogin=null;
				try {
					usuarioLogin = new Usuarios(user, cpf);
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

		        
		        UsuariosDAO usuarioDAO = new UsuariosDAO();
		        Usuarios usuarioAutenticado = usuarioDAO.pesquisarUsuariosPorUserCPF(usuarioLogin);

		        // avalia retorno
		        if (usuarioAutenticado != null) {
		        	if(usuarioAutenticado.isAdmin()) {
		        		abrirTelaAdmin();
		        	}
		        	else {
		        		abrirTelaCompras();
		        	}
		           

		        } else {
		            JOptionPane.showMessageDialog(null, "Usuário e/ou CPF incorretos. Tente Novamente.", "Erro de Login", JOptionPane.ERROR_MESSAGE);
		            
		            tfCPF.setText("");
		        }
			
		}
        		
        		
        		
        	
        });
        btnEntrar.setBounds(97, 228, 105, 23);
        contentPane.add(btnEntrar);
    }
}
