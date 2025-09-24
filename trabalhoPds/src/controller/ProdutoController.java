package controller;

import model.Produtos;
import model.ProdutosDAO;
import model.Supermercado;
import view.TelaAdmin;

import javax.swing.*;
import java.util.List;

public class ProdutoController {
    private TelaAdmin view;
    private Supermercado supermercado;
    private ProdutosDAO produtosDAO;

    public ProdutoController(TelaAdmin view, Supermercado supermercado) {
        this.view = view;
        this.supermercado = supermercado;
        this.produtosDAO = new ProdutosDAO();
    }

    public void carregarProdutos() {
        List<Produtos> lista = produtosDAO.listarProdutos();
        view.getModeloTabela().setRowCount(0); // limpa tabela
        for (Produtos p : lista) {
            view.getModeloTabela().addRow(new Object[]{
                    p.getNome(), p.getPreco(), p.getQuantidade()
            });
        }
    }

    public void adicionarProduto() {
        String nome = view.getTxtNome().getText().trim();
        String precoStr = view.getTxtPreco().getText().trim();
        String qtdStr = view.getTxtQuantidade().getText().trim();

        if (nome.isEmpty() || precoStr.isEmpty() || qtdStr.isEmpty()) {
            JOptionPane.showMessageDialog(view, "Preencha todos os campos!");
            return;
        }

        try {
            double preco = Double.parseDouble(precoStr);
            int quantidade = Integer.parseInt(qtdStr);

            Produtos novo = new Produtos();
            novo.setNome(nome);
            novo.setPreco(preco);
            novo.setQuantidade(quantidade);

            produtosDAO.inserirProduto(novo);
            JOptionPane.showMessageDialog(view, "Produto cadastrado!");
            carregarProdutos();

        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(view, "Preço e quantidade devem ser numéricos!");
        }
    }

    public void editarProduto() {
        int linha = view.getTabelaProdutos().getSelectedRow();
        if (linha == -1) {
            JOptionPane.showMessageDialog(view, "Selecione um produto!");
            return;
        }

        String nomeOriginal = (String) view.getModeloTabela().getValueAt(linha, 0);

        String novoNome = view.getTxtNome().getText().trim();
        String precoStr = view.getTxtPreco().getText().trim();
        String qtdStr = view.getTxtQuantidade().getText().trim();

        if (novoNome.isEmpty() || precoStr.isEmpty() || qtdStr.isEmpty()) {
            JOptionPane.showMessageDialog(view, "Preencha todos os campos!");
            return;
        }

        try {
            double preco = Double.parseDouble(precoStr);
            int quantidade = Integer.parseInt(qtdStr);

            Produtos editado = new Produtos();
            editado.setNome(novoNome);
            editado.setPreco(preco);
            editado.setQuantidade(quantidade);

            produtosDAO.atualizarProduto(nomeOriginal, editado);
            JOptionPane.showMessageDialog(view, "Produto atualizado!");
            carregarProdutos();

        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(view, "Preço e quantidade devem ser numéricos!");
        }
    }

    public void removerProduto() {
        int linha = view.getTabelaProdutos().getSelectedRow();
        if (linha == -1) {
            JOptionPane.showMessageDialog(view, "Selecione um produto!");
            return;
        }

        String nome = (String) view.getModeloTabela().getValueAt(linha, 0);

        int confirm = JOptionPane.showConfirmDialog(view,
                "Remover o produto " + nome + "?", "Confirmação",
                JOptionPane.YES_NO_OPTION);

        if (confirm == JOptionPane.YES_OPTION) {
            produtosDAO.removerProduto(nome);
            JOptionPane.showMessageDialog(view, "Produto removido!");
            carregarProdutos();
        }
    }
}