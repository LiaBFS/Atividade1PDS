package controller;

import model.Produtos;
import model.ProdutosDAO;
import model.Supermercado;
import model.Usuarios;
import view.TelaCompras;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class CompraController {
    private TelaCompras view;
    private Supermercado supermercado;
    private Usuarios usuario;
    private ProdutosDAO produtosDAO;
    private List<Produtos> carrinho;

    public CompraController(TelaCompras view, Supermercado supermercado, Usuarios usuario) {
        this.view = view;
        this.supermercado = supermercado;
        this.usuario = usuario;
        this.produtosDAO = new ProdutosDAO();
        this.carrinho = new ArrayList<>();
    }

    public void carregarProdutos() {
        List<Produtos> lista = produtosDAO.listarProdutos();
        view.getModeloProdutos().setRowCount(0);
        for (Produtos p : lista) {
            view.getModeloProdutos().addRow(new Object[]{
                    p.getNome(), p.getPreco(), p.getQuantidade()
            });
        }
    }

    public void adicionarAoCarrinho() {
        int linha = view.getTabelaProdutos().getSelectedRow();
        if (linha == -1) {
            JOptionPane.showMessageDialog(view, "Selecione um produto!");
            return;
        }

        String nome = (String) view.getModeloProdutos().getValueAt(linha, 0);
        double preco = (double) view.getModeloProdutos().getValueAt(linha, 1);
        int estoque = (int) view.getModeloProdutos().getValueAt(linha, 2);

        if (estoque <= 0) {
            JOptionPane.showMessageDialog(view, "Produto sem estoque!");
            return;
        }

        String qtdStr = JOptionPane.showInputDialog(view, "Quantidade:", "1");
        if (qtdStr == null) return; // cancelado
        int qtd;
        try {
            qtd = Integer.parseInt(qtdStr);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(view, "Quantidade inválida!");
            return;
        }

        if (qtd > estoque) {
            JOptionPane.showMessageDialog(view, "Estoque insuficiente!");
            return;
        }

        Produtos item = new Produtos();
        item.setNome(nome);
        item.setPreco(preco);
        item.setQuantidade(qtd);
        carrinho.add(item);

        view.getModeloCarrinho().addRow(new Object[]{
                nome, preco, qtd, preco * qtd
        });

        atualizarTotal();
    }

    public void removerDoCarrinho() {
        int linha = view.getTabelaCarrinho().getSelectedRow();
        if (linha == -1) {
            JOptionPane.showMessageDialog(view, "Selecione um item do carrinho!");
            return;
        }
        carrinho.remove(linha);
        view.getModeloCarrinho().removeRow(linha);
        atualizarTotal();
    }

    private void atualizarTotal() {
        double total = 0;
        for (Produtos p : carrinho) {
            total += p.getPreco() * p.getQuantidade();
        }
        view.getLblTotal().setText("Total: R$ " + String.format("%.2f", total));
    }

    public void finalizarCompra() {
        if (carrinho.isEmpty()) {
            JOptionPane.showMessageDialog(view, "Carrinho vazio!");
            return;
        }

        // Atualizar estoque
        for (Produtos item : carrinho) {
            produtosDAO.diminuirEstoque(item.getNome(), item.getQuantidade());
        }

        // Nota fiscal
        StringBuilder nota = new StringBuilder();
        nota.append("=== NOTA FISCAL ===\n");
        nota.append("Cliente: ").append(usuario.getUser()).append("\n");
        nota.append("CPF: ").append(usuario.getCpf()).append("\n\n");
        double total = 0;
        for (Produtos p : carrinho) {
            double subtotal = p.getPreco() * p.getQuantidade();
            nota.append(p.getNome()).append(" - Qtd: ").append(p.getQuantidade())
                .append(" - R$ ").append(String.format("%.2f", subtotal)).append("\n");
            total += subtotal;
        }
        nota.append("\nTOTAL: R$ ").append(String.format("%.2f", total));

        JOptionPane.showMessageDialog(view, nota.toString(), "Nota Fiscal", JOptionPane.INFORMATION_MESSAGE);

        // Limpar carrinho e recarregar
        carrinho.clear();
        view.getModeloCarrinho().setRowCount(0);
        atualizarTotal();
        carregarProdutos();
    }
}