package view;

import controller.CompraController;
import model.Supermercado;
import model.Usuarios;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class TelaCompras extends JFrame {
    private JTable tabelaProdutos;
    private JTable tabelaCarrinho;
    private DefaultTableModel modeloProdutos;
    private DefaultTableModel modeloCarrinho;
    private JButton btnAdicionar;
    private JButton btnRemover;
    private JButton btnFinalizar;
    private JButton btnSair;
    private JLabel lblTotal;

    private CompraController controller;

    public TelaCompras(Supermercado supermercado, Usuarios usuario) {
        super("Compra de Produtos");

        controller = new CompraController(this, supermercado, usuario);

        setSize(800, 500);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        // ---- Produtos disponíveis ----
        modeloProdutos = new DefaultTableModel(new Object[]{"Nome", "Preço", "Quantidade"}, 0);
        tabelaProdutos = new JTable(modeloProdutos);

        JPanel panelProdutos = new JPanel(new BorderLayout());
        panelProdutos.setBorder(BorderFactory.createTitledBorder("Produtos Disponíveis"));
        panelProdutos.add(new JScrollPane(tabelaProdutos), BorderLayout.CENTER);

        // ---- Carrinho ----
        modeloCarrinho = new DefaultTableModel(new Object[]{"Nome", "Preço", "Qtd", "Subtotal"}, 0);
        tabelaCarrinho = new JTable(modeloCarrinho);

        JPanel panelCarrinho = new JPanel(new BorderLayout());
        panelCarrinho.setBorder(BorderFactory.createTitledBorder("Carrinho de Compras"));
        panelCarrinho.add(new JScrollPane(tabelaCarrinho), BorderLayout.CENTER);

        // ---- Botões ----
        JPanel panelBotoes = new JPanel(new GridLayout(2, 2, 5, 5));
        btnAdicionar = new JButton("Adicionar ao Carrinho");
        btnRemover = new JButton("Remover do Carrinho");
        btnFinalizar = new JButton("Finalizar Compra");
        btnSair = new JButton("Sair");

        panelBotoes.add(btnAdicionar);
        panelBotoes.add(btnRemover);
        panelBotoes.add(btnFinalizar);
        panelBotoes.add(btnSair);

        // ---- Total ----
        lblTotal = new JLabel("Total: R$ 0.00");
        lblTotal.setFont(new Font("Arial", Font.BOLD, 16));
        JPanel panelTotal = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        panelTotal.add(lblTotal);

        // Layout principal
        JSplitPane split = new JSplitPane(JSplitPane.VERTICAL_SPLIT, panelProdutos, panelCarrinho);
        split.setDividerLocation(250);

        add(split, BorderLayout.CENTER);
        add(panelBotoes, BorderLayout.SOUTH);
        add(panelTotal, BorderLayout.NORTH);

        // Carregar produtos
        controller.carregarProdutos();

        // Eventos
        btnAdicionar.addActionListener(e -> controller.adicionarAoCarrinho());
        btnRemover.addActionListener(e -> controller.removerDoCarrinho());
        btnFinalizar.addActionListener(e -> controller.finalizarCompra());
        btnSair.addActionListener(e -> {
            dispose();
            new TelaLogin(supermercado).setVisible(true);
        });
    }

    // Getters
    public DefaultTableModel getModeloProdutos() { return modeloProdutos; }
    public DefaultTableModel getModeloCarrinho() { return modeloCarrinho; }
    public JTable getTabelaProdutos() { return tabelaProdutos; }
    public JTable getTabelaCarrinho() { return tabelaCarrinho; }
    public JLabel getLblTotal() { return lblTotal; }
}