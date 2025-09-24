package view;

import controller.ProdutoController;
import model.Supermercado;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class TelaAdmin extends JFrame {
    private JTextField txtNome;
    private JTextField txtPreco;
    private JTextField txtQuantidade;
    private JButton btnAdicionar;
    private JButton btnEditar;
    private JButton btnRemover;
    private JButton btnSair;
    private JTable tabelaProdutos;
    private DefaultTableModel modeloTabela;

    private ProdutoController controller;

    public TelaAdmin(Supermercado supermercado) {
        super("Administração de Produtos");

        controller = new ProdutoController(this, supermercado);

        setSize(600, 400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panelTop = new JPanel(new GridLayout(4, 2, 5, 5));

        panelTop.add(new JLabel("Nome:"));
        txtNome = new JTextField();
        panelTop.add(txtNome);

        panelTop.add(new JLabel("Preço:"));
        txtPreco = new JTextField();
        panelTop.add(txtPreco);

        panelTop.add(new JLabel("Quantidade:"));
        txtQuantidade = new JTextField();
        panelTop.add(txtQuantidade);

        btnAdicionar = new JButton("Adicionar");
        btnEditar = new JButton("Editar");
        btnRemover = new JButton("Remover");
        btnSair = new JButton("Sair");

        panelTop.add(btnAdicionar);
        panelTop.add(btnEditar);
        panelTop.add(btnRemover);
        panelTop.add(btnSair);

        add(panelTop, BorderLayout.NORTH);

        // Tabela
        modeloTabela = new DefaultTableModel(new Object[]{"Nome", "Preço", "Quantidade"}, 0);
        tabelaProdutos = new JTable(modeloTabela);
        add(new JScrollPane(tabelaProdutos), BorderLayout.CENTER);

        // Carregar produtos do banco
        controller.carregarProdutos();

        // Eventos
        btnAdicionar.addActionListener(e -> controller.adicionarProduto());
        btnEditar.addActionListener(e -> controller.editarProduto());
        btnRemover.addActionListener(e -> controller.removerProduto());
        btnSair.addActionListener(e -> {
            dispose();
            new TelaLogin(supermercado).setVisible(true);
        });
    }

    // Getters
    public JTextField getTxtNome() { return txtNome; }
    public JTextField getTxtPreco() { return txtPreco; }
    public JTextField getTxtQuantidade() { return txtQuantidade; }
    public DefaultTableModel getModeloTabela() { return modeloTabela; }
    public JTable getTabelaProdutos() { return tabelaProdutos; }
}