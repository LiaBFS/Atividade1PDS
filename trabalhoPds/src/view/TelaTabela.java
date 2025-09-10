package view;

import java.awt.Color;
import java.awt.EventQueue;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;


import javax.swing.JLabel;
import java.awt.Font;

public class TelaTabela extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private DefaultTableModel modelo;
	private JTable table;
	private JLabel lblNewLabel;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaTabela frame = new TelaTabela();
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
	public TelaTabela() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		
		
		
//		String[] colunas = {"Nome", "Preço"};
//		
//        modelo = new DefaultTableModel(colunas, 0);
//      
//      
//        table = new JTable(modelo);
//		table.setBounds(199, 52, 1, 1);
//		contentPane.add(table);
//
//
////       List<Produtos> produtos = ProdutosDAO.getProdutos();
////       		for (Produtos produto : produtos) {
////       		String[] linha = {produto.getNome(), produto.getPreco()};
////       		modelo.addRow(linha);
////      }
//
//       		
//
//       		//VE SE ELE VAI ADICIONAR UMA BARRA DE ROLAGEM
//       	JScrollPane scrollPane = new JScrollPane(table);
//       	scrollPane.setBounds(10, 39, 414, 211);
//       	contentPane.add(scrollPane);
//       	
//       	lblNewLabel = new JLabel("Tabela de Produtos");
//       	lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 13));
//       	lblNewLabel.setBounds(10, 14, 179, 14);
//       	contentPane.add(lblNewLabel);
	}
}


