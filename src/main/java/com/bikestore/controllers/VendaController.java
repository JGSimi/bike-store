package com.bikestore.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.bikestore.controllers.base.MenuBase;
import com.bikestore.model.Estoque;
import com.bikestore.model.Produto;
import com.bikestore.model.VendaSimples;

public class VendaController {
    private static final Scanner scanner = new Scanner(System.in);
    private static final List<VendaSimples> historicoVendas = new ArrayList<>();

    // Menu de Nova Venda
    private static class NovaVendaMenu extends MenuBase {
        private final VendaSimples venda;

        public NovaVendaMenu() {
            super("Nova Venda",
                    "Adicionar Produto",
                    "Visualizar Carrinho",
                    "Finalizar Venda");
            this.venda = new VendaSimples();
        }

        @Override
        protected void processarOpcao(int choice) {
            switch (choice) {
                case 0 -> MenuController.init();
                case 1 -> adicionarProduto();
                case 2 -> visualizarCarrinho();
                case 3 -> finalizarVenda();
            }
        }

        private void adicionarProduto() {
            try {
                Produto produto = selecionarProduto();
                if (produto != null) {
                    System.out.print("Digite a quantidade: ");
                    int quantidade = Integer.parseInt(scanner.nextLine());
                    venda.adicionarProduto(produto, quantidade);
                    System.out.println("Produto adicionado com sucesso!");
                }
            } catch (NumberFormatException e) {
                System.out.println("Quantidade inválida!");
            } catch (IllegalArgumentException e) {
                System.out.println("Erro: " + e.getMessage());
            }
            executar(); // Volta para o menu
        }

        private void visualizarCarrinho() {
            System.out.println(venda.gerarResumo());
            System.out.println("\nPressione ENTER para continuar...");
            scanner.nextLine();
            executar(); // Volta para o menu
        }

        private void finalizarVenda() {
            if (confirmarVenda(venda)) {
                MenuController.init();
            } else {
                executar(); // Volta para o menu se não confirmou
            }
        }
    }

    // Menu do Histórico de Vendas
    private static class HistoricoVendasMenu extends MenuBase {
        public HistoricoVendasMenu() {
            super("Histórico de Vendas",
                    "Listar Todas as Vendas",
                    "Buscar por Data",
                    "Relatório de Vendas");
        }

        @Override
        protected void processarOpcao(int choice) {
            switch (choice) {
                case 0 -> MenuController.init();
                case 1 -> listarTodasVendas();
                case 2 -> buscarPorData();
                case 3 -> gerarRelatorio();
            }
        }

        private void listarTodasVendas() {
            if (historicoVendas.isEmpty()) {
                System.out.println("Nenhuma venda registrada.");
            } else {
                for (int i = 0; i < historicoVendas.size(); i++) {
                    System.out.println("\nVenda #" + (i + 1));
                    System.out.println(historicoVendas.get(i).gerarResumo());
                }
            }
            System.out.println("\nPressione ENTER para continuar...");
            scanner.nextLine();
            executar();
        }

        private void buscarPorData() {
            System.out.println("Funcionalidade em desenvolvimento");
            System.out.println("\nPressione ENTER para continuar...");
            scanner.nextLine();
            executar();
        }

        private void gerarRelatorio() {
            System.out.println("Funcionalidade em desenvolvimento");
            System.out.println("\nPressione ENTER para continuar...");
            scanner.nextLine();
            executar();
        }
    }

    // Métodos públicos para acesso externo
    public static void novaVenda() {
        new NovaVendaMenu().executar();
    }

    public static void historicoVendas() {
        new HistoricoVendasMenu().executar();
    }

    // Métodos auxiliares
    private static Produto selecionarProduto() {
        if (Estoque.listarProdutos().isEmpty()) {
            System.out.println("Não há produtos disponíveis no estoque.");
            System.out.println("\nPressione ENTER para continuar...");
            scanner.nextLine();
            return null;
        }

        System.out.println("\nProdutos Disponíveis:");
        List<Produto> produtos = Estoque.listarProdutos();
        
        for (int i = 0; i < produtos.size(); i++) {
            Produto p = produtos.get(i);
            System.out.printf("%d. %s - R$ %.2f (%d em estoque)%n", 
                            i + 1, 
                            p.getNome(), 
                            p.getPreco(), 
                            p.getQuantidadenoEstoque());
        }

        while (true) {
            try {
                System.out.print("\nSelecione o número do produto (0 para cancelar): ");
                int escolha = Integer.parseInt(scanner.nextLine());

                if (escolha == 0) {
                    return null;
                }

                if (escolha > 0 && escolha <= produtos.size()) {
                    Produto selecionado = produtos.get(escolha - 1);
                    if (selecionado.getQuantidadenoEstoque() > 0) {
                        return selecionado;
                    } else {
                        System.out.println("Produto sem estoque disponível!");
                    }
                } else {
                    System.out.println("Opção inválida! Tente novamente.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Por favor, digite um número válido!");
            }
        }
    }

    private static boolean confirmarVenda(VendaSimples venda) {
        System.out.println(venda.gerarResumo());
        System.out.print("Confirmar venda? (S/N): ");
        
        if (scanner.nextLine().equalsIgnoreCase("S")) {
            historicoVendas.add(venda);
            System.out.println("Venda realizada com sucesso!");
            return true;
        } else {
            System.out.println("Venda cancelada.");
            return false;
        }
    }
}
