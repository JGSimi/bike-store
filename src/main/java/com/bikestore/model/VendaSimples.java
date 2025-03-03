package com.bikestore.model;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

import com.bikestore.controllers.interfaces.Venda;

public class VendaSimples implements Venda {
    private final Map<Produto, Integer> itens;
    private final LocalDateTime dataVenda;
    private double valorTotal;

    // Construtor padrão
    public VendaSimples() {
        this.itens = new HashMap<>();
        this.dataVenda = LocalDateTime.now();
        this.valorTotal = 0.0;
    }

    @Override
    public void adicionarProduto(Produto produto, int quantidade) {
        if (produto.getQuantidadenoEstoque() >= quantidade) {
            itens.put(produto, quantidade);
            produto.atualizarEstoque(quantidade);
            valorTotal += produto.getPreco() * quantidade;
        } else {
            throw new IllegalArgumentException("Quantidade insuficiente em estoque");
        }
    }

    @Override
    public double calcularTotal() {
        return valorTotal;
    }

    @Override
    public String gerarResumo() {
        StringBuilder resumo = new StringBuilder();
        resumo.append("\n=== Resumo da Venda ===\n");
        resumo.append("Data: ").append(dataVenda).append("\n\n");
        
        for (Map.Entry<Produto, Integer> item : itens.entrySet()) {
            Produto produto = item.getKey();
            int quantidade = item.getValue();
            double subtotal = produto.getPreco() * quantidade;
            
            resumo.append(String.format("Produto: %s\n", produto.getNome()));
            resumo.append(String.format("Quantidade: %d\n", quantidade));
            resumo.append(String.format("Preço unitário: R$ %.2f\n", produto.getPreco()));
            resumo.append(String.format("Subtotal: R$ %.2f\n\n", subtotal));
        }
        
        resumo.append(String.format("Valor Total: R$ %.2f\n", valorTotal));
        return resumo.toString();
    }
} 