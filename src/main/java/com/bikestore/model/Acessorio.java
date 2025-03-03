package com.bikestore.model;

public class Acessorio extends Produto {
    private String tipo;

    /**
     * Construtor para a classe Produto
     *
     * @param nome  Nome do produto
     * @param preco Preço do produto
     * @param quantidadeEstoque Quantidade inicial em estoque
     */
    public Acessorio(String nome, double preco, int quantidadeEstoque, String tipo) {
        super(nome, preco, quantidadeEstoque);
        this.tipo = tipo;
    }

    @Override
    public void atualizarEstoque(int quantidade){
        setQuantidadenoEstoque(getQuantidadenoEstoque() - quantidade);
    }
}
