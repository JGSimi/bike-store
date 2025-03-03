package com.bikestore.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.bikestore.until;

//responsavel pela interface do usuario

public class MenuController {
      
      /*
       * funcao para criar menus
       * @param options - lista de opcoes de menu
       * @param message - mensagem de titulo do menu
       */
      public static void createMenu(List<String> options, String message) {
            // Encontra o tamanho da maior string para ajustar o menu
            int maxLength = message.length();
            for (String opcao : options) {
                  if (opcao.length() > maxLength) {
                        maxLength = opcao.length();
                  }
            }
            
            // Adiciona espaço para numeração e formatação
            int larguraTotal = maxLength + 8;
            String linha = "═".repeat(larguraTotal);
            
            // Imprime o cabeçalho do menu
            System.out.println("\n╔" + linha + "╗");
            System.out.println("║  " + until.centralizarTexto(message, larguraTotal - 4) + "  ║");
            System.out.println("╠" + linha + "╣");
            
            // Imprime as opções do menu
            for (int i = 0; i < options.size() - 1; i++) {
                  String opcaoFormatada = (i + 1) + " - " + options.get(i);
                  System.out.println("║  " + until.centralizarTexto(opcaoFormatada, larguraTotal - 4) + "  ║");
            }
            
            // Imprime a opção de sair/voltar como 0
            String opcaoSair = "0 - " + options.get(options.size() - 1);
            System.out.println("║  " + until.centralizarTexto(opcaoSair, larguraTotal - 4) + "  ║");
            
            // Imprime o rodapé do menu
            System.out.println("╚" + linha + "╝");
      }

      public static int getUserInput(List<String> options) {
            Scanner scanner = new Scanner(System.in);
            int opcao = -1;
            boolean entradaValida = false;
            
            while (!entradaValida) {
                  System.out.print("\nDigite sua opção: ");
                  try {
                        opcao = Integer.parseInt(scanner.nextLine());
                        
                        // Verifica se a opção está dentro do intervalo válido (0 até o tamanho da lista)
                        if (opcao >= 0 && opcao <= options.size() - 1) {
                              entradaValida = true;
                        } else {
                              System.out.println("Opção inválida! Por favor, escolha uma opção entre 0 e " + (options.size() - 1));
                        }
                  } catch (NumberFormatException e) {
                        System.out.println("Por favor, digite apenas números!");
                  }
            }
            
            return opcao;
      }

      public static void init() {
            List<String> options = new ArrayList<>();
            options.add("Cadastrar Cliente");
            options.add("Cadastrar Produto");
            options.add("Cadastrar Venda");
            options.add("Sair");

            createMenu(options, "Bike Store");

            int choice = getUserInput(options);

            switch (choice) {
                  case 0 -> System.out.println("Saindo...");
                  case 1 -> System.out.println("ClienteController.init();");
                  case 2 -> System.out.println("ProdutoController.init();");
                  case 3 -> System.out.println("VendaController.init();");
                  default -> System.out.println("Opção inválida!");
            }
      }
      
}
