package com.bikestore.controllers;

import java.util.List;
import java.util.Scanner;

import com.bikestore.controllers.base.MenuBase;
import com.bikestore.model.Acessorio;
import com.bikestore.model.Bicicleta;
import com.bikestore.model.Estoque;
import com.bikestore.model.Produto;

public class EstoqueController {
    private static final Scanner scanner = new Scanner(System.in);

    private static class EstoqueMenu extends MenuBase {
        public EstoqueMenu() {
            super("Gerenciamento de Estoque",
                  "Adicionar Novo Produto",
                  "Remover Produto",
                  "Listar Produtos",
                  "Buscar Produto",
                  "Atualizar Estoque");
        }

        @Override
        protected void processarOpcao(int choice) {
            switch (choice) {
                case 0 -> MenuController.init();
                case 1 -> adicionarNovoProduto();
                case 2 -> removerProduto();
                case 3 -> listarProdutos();
                case 4 -> buscarProduto();
                case 5 -> atualizarEstoque();
            }
        }

        private void adicionarNovoProduto() {
            System.out.println("\nTipo de Produto:");
            System.out.println("1. Bicicleta");
            System.out.println("2. Acessório");
            System.out.print("Escolha o tipo: ");

            try {
                int tipo = Integer.parseInt(scanner.nextLine());
                System.out.print("Nome: ");
                String nome = scanner.nextLine();
                System.out.print("Preço: ");
                double preco = Double.parseDouble(scanner.nextLine());
                System.out.print("Quantidade: ");
                int quantidade = Integer.parseInt(scanner.nextLine());

                if (tipo == 1) {
                    System.out.print("Marca: ");
                    String marca = scanner.nextLine();
                    System.out.print("Modelo: ");
                    String modelo = scanner.nextLine();
                    System.out.print("Tamanho (P/M/G): ");
                    String tamanho = scanner.nextLine();

                    Estoque.adicionarProduto(new Bicicleta(nome, preco, quantidade, marca, modelo, tamanho));
                } else if (tipo == 2) {
                    System.out.print("Tipo de Acessório: ");
                    String tipoAcessorio = scanner.nextLine();

                    Estoque.adicionarProduto(new Acessorio(nome, preco, quantidade, tipoAcessorio));
                }

                System.out.println("Produto adicionado com sucesso!");
            } catch (NumberFormatException e) {
                System.out.println("Valor inválido inserido!");
            }

            System.out.println("\nPressione ENTER para continuar...");
            scanner.nextLine();
            executar();
        }

        private void removerProduto() {
            listarProdutos();
            System.out.print("\nDigite o número do produto para remover (0 para cancelar): ");

            try {
                int index = Integer.parseInt(scanner.nextLine()) - 1;
                if (index >= 0 && index < Estoque.listarProdutos().size()) {
                    Produto produto = Estoque.listarProdutos().get(index);
                    Estoque.removerProduto(produto);
                    System.out.println("Produto removido com sucesso!");
                }
            } catch (NumberFormatException e) {
                System.out.println("Número inválido!");
            }

            System.out.println("\nPressione ENTER para continuar...");
            scanner.nextLine();
            executar();
        }

        private void listarProdutos() {
            List<Produto> produtos = Estoque.listarProdutos();
            if (produtos.isEmpty()) {
                System.out.println("Nenhum produto no estoque.");
            } else {
                System.out.println("\nProdutos em Estoque:");
                for (int i = 0; i < produtos.size(); i++) {
                    Produto p = produtos.get(i);
                    System.out.printf("%d. %s - R$ %.2f (%d em estoque)%n",
                            i + 1, p.getNome(), p.getPreco(), p.getQuantidadenoEstoque());
                }
            }

            System.out.println("\nPressione ENTER para continuar...");
            scanner.nextLine();
            executar();
        }

        private void buscarProduto() {
            System.out.print("Digite o nome do produto: ");
            String nome = scanner.nextLine();

            Estoque.buscarProdutoPorNome(nome).ifPresentOrElse(
                produto -> System.out.printf("%s - R$ %.2f (%d em estoque)%n",
                        produto.getNome(), produto.getPreco(), produto.getQuantidadenoEstoque()),
                () -> System.out.println("Produto não encontrado.")
            );

            System.out.println("\nPressione ENTER para continuar...");
            scanner.nextLine();
            executar();
        }

        private void atualizarEstoque() {
            listarProdutos();
            System.out.print("\nDigite o número do produto para atualizar (0 para cancelar): ");

            try {
                int index = Integer.parseInt(scanner.nextLine()) - 1;
                if (index >= 0 && index < Estoque.listarProdutos().size()) {
                    Produto produto = Estoque.listarProdutos().get(index);
                    System.out.print("Digite a nova quantidade em estoque: ");
                    int novaQuantidade = Integer.parseInt(scanner.nextLine());
                    produto.setQuantidadenoEstoque(novaQuantidade);
                    System.out.println("Estoque atualizado com sucesso!");
                }
            } catch (NumberFormatException e) {
                System.out.println("Número inválido!");
            }

            System.out.println("\nPressione ENTER para continuar...");
            scanner.nextLine();
            executar();
        }
    }

    // Método público para acesso externo
    public static void gerenciarEstoque() {
        new EstoqueMenu().executar();
    }
} 