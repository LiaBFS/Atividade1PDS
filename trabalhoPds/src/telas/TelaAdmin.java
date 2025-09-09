package telas;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class TelaAdmin extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField tfNomeProduto;
	private JTextField tfPreco;
	private JTextField tfNovoNome;
	private JTextField tfNovoPreco;
	
	// 🔹 NOVO: transformar os comboBox em atributos da classe
	private JComboBox<String> comboBoxNomePraRemo;
	private JComboBox<String> comboBoxPraEditar;
	
	ProdutosDAO produtoDAO = new ProdutosDAO();
	
	
	private void abrirTelaTabela() {
	    dispose(); // Fecha a tela atual
	    new TelaTabela().setVisible(true); // Abre a tela de cadastro
	}
	
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaAdmin frame = new TelaAdmin();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public TelaAdmin() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 449, 348);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Cadastro de Novo Produto:");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblNewLabel.setBounds(10, 8, 182, 16);
		contentPane.add(lblNewLabel);
		
		JLabel lblNomeProduto = new JLabel("Nome do Produto:");
		lblNomeProduto.setFont(new Font("Tahoma", Font.PLAIN, 10));
		lblNomeProduto.setBounds(10, 35, 92, 14);
		contentPane.add(lblNomeProduto);
		
		tfNomeProduto = new JTextField();
		tfNomeProduto.setBounds(106, 32, 318, 20);
		contentPane.add(tfNomeProduto);
		tfNomeProduto.setColumns(10);
		
		JLabel lblPreco = new JLabel("Preço:");
		lblPreco.setFont(new Font("Tahoma", Font.PLAIN, 10));
		lblPreco.setBounds(10, 60, 46, 14);
		contentPane.add(lblPreco);
		
		tfPreco = new JTextField();
		tfPreco.setBounds(106, 57, 86, 20);
		contentPane.add(tfPreco);
		tfPreco.setColumns(10);
		
		JLabel lblRemooProduto = new JLabel("Remoção de Produtos:");
		lblRemooProduto.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblRemooProduto.setBounds(10, 85, 154, 16);
		contentPane.add(lblRemooProduto);
		
		JLabel lblNomePraRemo = new JLabel("Nome do Produto:");
		lblNomePraRemo.setFont(new Font("Tahoma", Font.PLAIN, 10));
		lblNomePraRemo.setBounds(10, 112, 92, 14);
		contentPane.add(lblNomePraRemo);
		
		// 🔹 NOVO: inicializar como atributo
		comboBoxNomePraRemo = new JComboBox<>();
		comboBoxNomePraRemo.setBounds(106, 108, 318, 22);
		contentPane.add(comboBoxNomePraRemo);
		
		
		JButton btnRemove = new JButton("Remover");
		btnRemove.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String nome = (String) comboBoxNomePraRemo.getSelectedItem();
				
				produtoDAO.excluirProduto(nome);
	 
				JOptionPane.showMessageDialog(null, "Produto removido com sucesso!");
	            comboBoxNomePraRemo.removeItem(nome);
	            comboBoxPraEditar.removeItem(nome);
	           
			}
		});
		btnRemove.setBounds(335, 135, 89, 23);
		contentPane.add(btnRemove);
		
		
		JLabel lblEdioDeProdutos = new JLabel("Edição de Produtos:");
		lblEdioDeProdutos.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblEdioDeProdutos.setBounds(10, 163, 154, 16);
		contentPane.add(lblEdioDeProdutos);
		
		JLabel lblNomeEdita = new JLabel("Nome do Produto:");
		lblNomeEdita.setFont(new Font("Tahoma", Font.PLAIN, 10));
		lblNomeEdita.setBounds(10, 190, 92, 14);
		contentPane.add(lblNomeEdita);
		
		// 🔹 NOVO: inicializar como atributo
		comboBoxPraEditar = new JComboBox<>();
		comboBoxPraEditar.setBounds(106, 186, 318, 22);
		contentPane.add(comboBoxPraEditar);
		
		JLabel lblNovoNome = new JLabel("Novo Nome:");
		lblNovoNome.setFont(new Font("Tahoma", Font.PLAIN, 10));
		lblNovoNome.setBounds(10, 215, 92, 14);
		contentPane.add(lblNovoNome);
		
		tfNovoNome = new JTextField();
		tfNovoNome.setColumns(10);
		tfNovoNome.setBounds(106, 212, 318, 20);
		contentPane.add(tfNovoNome);
		
		JLabel lblNovoPreco = new JLabel("Novo Preço:");
		lblNovoPreco.setFont(new Font("Tahoma", Font.PLAIN, 10));
		lblNovoPreco.setBounds(10, 240, 92, 14);
		contentPane.add(lblNovoPreco);
		
		tfNovoPreco = new JTextField();
		tfNovoPreco.setColumns(10);
		tfNovoPreco.setBounds(106, 237, 86, 20);
		contentPane.add(tfNovoPreco);
		
		JButton btnEditar = new JButton("Salvar");
		btnEditar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String nomeAntigo = (String) comboBoxPraEditar.getSelectedItem();
		        String novoNome = tfNovoNome.getText().trim();
		        String novoPreco = tfNovoPreco.getText().trim();
				
		        if (novoNome.isEmpty() || novoPreco.isEmpty()) {
		            JOptionPane.showMessageDialog(null, "Todos os campos são obrigatórios.");
		            return;
		        }

		        Produtos produtoAtualizado = new Produtos();
		        produtoAtualizado.setNome(novoNome);
		        produtoAtualizado.setPreco(novoPreco);

		        produtoDAO.atualizarProduto(produtoAtualizado, nomeAntigo);

		        
		        comboBoxPraEditar.removeItem(nomeAntigo);
		        comboBoxPraEditar.addItem(novoNome);

		        comboBoxNomePraRemo.removeItem(nomeAntigo);
		        comboBoxNomePraRemo.addItem(novoNome);

		        
		        tfNovoNome.setText("");
		        tfNovoPreco.setText("");
				
				
				
				
			}
		});
		btnEditar.setBounds(335, 236, 89, 23);
		contentPane.add(btnEditar);
		
		
		JButton btnSalvarNovo = new JButton("Salvar");
		btnSalvarNovo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String nome = tfNomeProduto.getText().trim() ;
				String preco = tfPreco.getText().trim();
				
				if(nome.isEmpty() && preco.isEmpty()) { 
					JOptionPane.showMessageDialog(null, "Todos os campos são obrigatórios.", "Erro", JOptionPane.ERROR_MESSAGE);
			        return;
				}
				
					
					Produtos novoProduto = new Produtos();
					novoProduto.setNome(tfNomeProduto.getText());
					novoProduto.setPreco(tfPreco.getText());
					
					
					
					produtoDAO.adicionarProduto(novoProduto);
					
					//adicionar o produto nos comboBox
					comboBoxNomePraRemo.addItem(nome);
					comboBoxPraEditar.addItem(nome);
					
					tfNomeProduto.setText("");
					tfPreco.setText("");
				
			}
		});
		btnSalvarNovo.setBounds(335, 56, 89, 23);
		contentPane.add(btnSalvarNovo);
		
		
		JLabel lblVisualizaoDeProdutos = new JLabel("Visualização de Produtos:");
		lblVisualizaoDeProdutos.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblVisualizaoDeProdutos.setBounds(11, 274, 167, 16);
		contentPane.add(lblVisualizaoDeProdutos);
		
		JButton btnAcessarTabela = new JButton("Acessar Tabela de Produtos");
		btnAcessarTabela.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				abrirTelaTabela();
			}
		});
		btnAcessarTabela.setBounds(201, 272, 223, 23);
		contentPane.add(btnAcessarTabela);
	}
}