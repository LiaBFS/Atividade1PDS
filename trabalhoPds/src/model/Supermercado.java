package model;

import java.util.List;

public class Supermercado {
    private Usuarios usuarioLogado;
    private Carrinho carrinho;

    public Supermercado() {
        this.carrinho = new Carrinho();
    }

    public Usuarios getUsuarioLogado() {
        return usuarioLogado;
    }

    public void setUsuarioLogado(Usuarios usuarioLogado) {
        this.usuarioLogado = usuarioLogado;
    }

    public Carrinho getCarrinho() {
        return carrinho;
    }

    // Gera uma "nota fiscal" em forma de string
    public String gerarNotaFiscal() {
        StringBuilder nota = new StringBuilder();
        nota.append("===== NOTA FISCAL =====\n");
        nota.append("Cliente: ").append(usuarioLogado.getUser()).append("\n");
        nota.append("CPF: ").append(usuarioLogado.getCpf()).append("\n\n");
        nota.append("Produtos:\n");

        for (Produtos p : carrinho.getItens()) {
            nota.append(p.getNome())
                .append(" x").append(p.getQuantidade())
                .append(" - R$ ").append(p.getPreco() * p.getQuantidade())
                .append("\n");
        }

        nota.append("\nTotal: R$ ").append(carrinho.calcularTotal()).append("\n");
        nota.append("========================\n");
        return nota.toString();
    }
}