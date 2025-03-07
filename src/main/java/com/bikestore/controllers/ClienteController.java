package com.bikestore.controllers;

import com.bikestore.controllers.base.MenuBase;
import com.bikestore.model.Cliente;
import com.bikestore.utils.ValidadorCliente;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Optional;

public class ClienteController {
    // Lista estática para armazenar os clientes cadastrados
    private static final List<Cliente> clientes = new ArrayList<>();
    private static final Scanner scanner = new Scanner(System.in); // Scanner para entrada de dados pelo usuário

    // Classe aninhada que gerencia o menu de operações do cliente
    private static class ClienteMenu extends MenuBase {
        // Construtor do menu com as opções disponíveis
        public ClienteMenu() {
            super("Gerenciamento de Clientes",
                    "Cadastrar Cliente",
                    "Buscar Cliente",
                    "Listar Clientes",
                    "Atualizar Cliente",
                    "Remover Cliente");
        }

        // Processa a opção escolhida no menu
        @Override
        protected void processarOpcao(int choice) {
            switch (choice) {
                case 0 -> MenuController.init(); // Voltar para o menu principal
                case 1 -> cadastrarCliente();
                case 2 -> buscarCliente();
                case 3 -> listarClientes();
                case 4 -> atualizarCliente();
                case 5 -> removerCliente();
            }
        }

        // Método para cadastrar um novo cliente
        private void cadastrarCliente() {
            System.out.println("\n=== Cadastro de Cliente ===");

            // Entrada do nome
            System.out.print("Nome: ");
            String nome = scanner.nextLine();

            // Entrada e validação do CPF
            String cpf;
            do {
                System.out.print("CPF (apenas números): ");
                cpf = scanner.nextLine();
                if (buscarClientePorCPF(cpf).isPresent()) {
                    System.out.println("CPF já cadastrado!");
                    System.out.println("\nPressione ENTER para continuar...");
                    scanner.nextLine();
                    executar();
                    return;
                }
            } while (!ValidadorCliente.validarCPF(cpf));

            // Entrada do telefone
            System.out.print("Telefone: ");
            String telefone = scanner.nextLine();

            // Entrada e validação do email
            String email;
            do {
                System.out.print("Email: ");
                email = scanner.nextLine();
            } while (!ValidadorCliente.validarEmail(email));

            // Criar e adicionar cliente à lista
            Cliente cliente = new Cliente(nome, cpf, telefone, email);
            clientes.add(cliente);
            System.out.println("\nCliente cadastrado com sucesso!");
            System.out.println("\nPressione ENTER para continuar...");
            scanner.nextLine();
            executar();
        }

        // Método para buscar um cliente por CPF
        private void buscarCliente() {
            System.out.println("\n=== Buscar Cliente ===");
            System.out.print("Digite o CPF do cliente: ");
            String cpf = scanner.nextLine();

            Optional<Cliente> clienteOpt = buscarClientePorCPF(cpf);
            if (clienteOpt.isPresent()) {
                Cliente cliente = clienteOpt.get();
                exibirDetalhesCliente(cliente);
            } else {
                System.out.println("Cliente não encontrado!");
            }
            System.out.println("\nPressione ENTER para continuar...");
            scanner.nextLine();
            executar();
        }

        // Método para listar todos os clientes cadastrados
        private void listarClientes() {
            if (clientes.isEmpty()) {
                System.out.println("\nNenhum cliente cadastrado!");
            } else {
                System.out.println("\n=== Lista de Clientes ===");
                clientes.forEach(cliente -> {
                    System.out.println("\n--------------------");
                    exibirDetalhesCliente(cliente);
                });
            }
            System.out.println("\nPressione ENTER para continuar...");
            scanner.nextLine();
            executar();
        }

        // Método para atualizar os dados de um cliente
        private void atualizarCliente() {
            System.out.println("\n=== Atualizar Cliente ===");
            System.out.print("Digite o CPF do cliente: ");
            String cpf = scanner.nextLine();

            Optional<Cliente> clienteOpt = buscarClientePorCPF(cpf);
            if (clienteOpt.isEmpty()) {
                System.out.println("Cliente não encontrado!");
                System.out.println("\nPressione ENTER para continuar...");
                scanner.nextLine();
                executar();
                return;
            }

            Cliente cliente = clienteOpt.get();
            System.out.println("\nDados atuais do cliente:");
            exibirDetalhesCliente(cliente);

            System.out.println("\nDigite os novos dados (ou pressione ENTER para manter o valor atual):");

            // Atualização condicional dos campos
            System.out.print("Novo nome [" + cliente.getNome() + "]: ");
            String novoNome = scanner.nextLine();
            if (!novoNome.isEmpty()) cliente.setNome(novoNome);

            System.out.print("Novo telefone [" + cliente.getTelefone() + "]: ");
            String novoTelefone = scanner.nextLine();
            if (!novoTelefone.isEmpty()) cliente.setTelefone(novoTelefone);

            System.out.print("Novo email [" + cliente.getEmail() + "]: ");
            String novoEmail = scanner.nextLine();
            if (!novoEmail.isEmpty() && ValidadorCliente.validarEmail(novoEmail)) {
                cliente.setEmail(novoEmail);
            }

            System.out.println("\nDados atualizados com sucesso!");
            System.out.println("\nPressione ENTER para continuar...");
            scanner.nextLine();
            executar();
        }

        // Método para remover um cliente
        private void removerCliente() {
            System.out.println("\n=== Remover Cliente ===");
            System.out.print("Digite o CPF do cliente: ");
            String cpf = scanner.nextLine();

            Optional<Cliente> clienteOpt = buscarClientePorCPF(cpf);
            if (clienteOpt.isEmpty()) {
                System.out.println("Cliente não encontrado!");
                System.out.println("\nPressione ENTER para continuar...");
                scanner.nextLine();
                executar();
                return;
            }

            Cliente cliente = clienteOpt.get();
            System.out.println("\nDados do cliente a ser removido:");
            exibirDetalhesCliente(cliente);

            // Confirmação da remoção
            System.out.print("\nConfirma a remoção? (S/N): ");
            String confirmacao = scanner.nextLine();

            if (confirmacao.equalsIgnoreCase("S")) {
                clientes.remove(cliente);
                System.out.println("Cliente removido com sucesso!");
            } else {
                System.out.println("Operação cancelada!");
            }
            System.out.println("\nPressione ENTER para continuar...");
            scanner.nextLine();
            executar();
        }
    }

    // Inicia o gerenciamento de clientes pelo menu
    public static void gerenciarClientes() {
        new ClienteMenu().executar();
    }

    // Busca um cliente na lista pelo CPF
    private static Optional<Cliente> buscarClientePorCPF(String cpf) {
        return clientes.stream()
                .filter(c -> c.getCpf().equals(cpf))
                .findFirst();
    }

    // Exibe os detalhes de um cliente
    private static void exibirDetalhesCliente(Cliente cliente) {
        System.out.println("Nome: " + cliente.getNome());
        System.out.println("CPF: " + cliente.getCpf());
        System.out.println("Telefone: " + cliente.getTelefone());
        System.out.println("Email: " + cliente.getEmail());
        System.out.println("Data de Cadastro: " + cliente.getDataCadastro());
    }
}