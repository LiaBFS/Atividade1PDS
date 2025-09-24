package model;

import java.util.ArrayList;
import java.util.List;

public class Carrinho {
    private List<Produtos> itens = new ArrayList<>();

    public void adicionarProduto(Produtos produto, int quantidade) {
        // cria uma cópia do produto com quantidade escolhida
        Produtos item = new Produtos(produto.getNome(), produto.getPreco(), quantidade);
        itens.add(item);
    }

    public void removerProduto(String nome) {
        itens.removeIf(p -> p.getNome().equalsIgnoreCase(nome));
    }

    public double calcularTotal() {
        double total = 0;
        for (Produtos p : itens) {
            total += p.getPreco() * p.getQuantidade();
        }
        return total;
    }

    public List<Produtos> getItens() {
        return itens;
    }

    public void limparCarrinho() {
        itens.clear();
    }
}