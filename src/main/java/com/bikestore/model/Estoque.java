package com.bikestore.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Estoque {
    private static final List<Produto> produtos = new ArrayList<>();

    // Construtor privado para evitar instanciação
    private Estoque() {}

    // Métodos de gerenciamento de produtos
    public static void adicionarProduto(Produto produto) {
        produtos.add(produto);
    }

    public static void removerProduto(Produto produto) {
        produtos.remove(produto);
    }

    public static List<Produto> listarProdutos() {
        return new ArrayList<>(produtos);
    }

    public static Optional<Produto> buscarProdutoPorNome(String nome) {
        return produtos.stream()
                      .filter(p -> p.getNome().equalsIgnoreCase(nome))
                      .findFirst();
    }

    public static boolean verificarDisponibilidade(Produto produto, int quantidade) {
        return produto.getQuantidadenoEstoque() >= quantidade;
    }

    // Método para atualizar o estoque após uma venda
    public static void atualizarEstoque(Produto produto, int quantidade) {
        if (verificarDisponibilidade(produto, quantidade)) {
            produto.atualizarEstoque(quantidade);
        } else {
            throw new IllegalArgumentException("Quantidade insuficiente em estoque");
        }
    }

    // Método para adicionar produtos iniciais (para teste)
    public static void inicializarEstoqueTeste() {
        // Bicicletas
        adicionarProduto(new Bicicleta("Mountain Bike Pro", 2499.99, 5, "Trek", "XC-200", "M"));
        adicionarProduto(new Bicicleta("Bike Urbana", 1899.99, 3, "Caloi", "City", "L"));
        adicionarProduto(new Bicicleta("BMX Freestyle", 1299.99, 2, "Mongoose", "Legion", "Único"));

        // Acessórios
        adicionarProduto(new Acessorio("Capacete Pro", 299.99, 10, "Capacete"));
        adicionarProduto(new Acessorio("Luzes LED", 89.99, 15, "Iluminação"));
        adicionarProduto(new Acessorio("Cadeado Reforçado", 159.99, 8, "Segurança"));
    }
} 