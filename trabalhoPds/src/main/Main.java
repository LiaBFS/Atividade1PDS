package main;

import model.Supermercado;
import view.TelaLogin;

public class Main {
    public static void main(String[] args) {
        // Inicializa o "estado" do sistema
        Supermercado supermercado = new Supermercado();

        // Abre a tela de login
        javax.swing.SwingUtilities.invokeLater(() -> {
            new TelaLogin(supermercado).setVisible(true);
        });
    }
}