package com.bikestore.model;

public class Bicicleta extends Produto{
    private String marca;
    private String modelo;
    private String tamanho;

    /**
     * Construtor para a classe Bicicleta
     *
     * @param nome  Nome da bicicleta
     * @param preco Preço da bicicleta
     * @param quantidadeEstoque Quantidade inicial em estoque da bicicleta
     * @param marca Marca da bicicleta
     * @param modelo    Modelo da bicicleta
     * @param tamanho   Tamanho da bicicleta
     */
    public Bicicleta(String nome, double preco, int quantidadeEstoque, String marca, String modelo, String tamanho) {
        super(nome, preco, quantidadeEstoque);
        this.marca = marca;
        this.modelo = modelo;
        this.tamanho = tamanho;
    }

    @Override
    public void atualizarEstoque(int quantidade){
        this.setQuantidadenoEstoque(this.getQuantidadenoEstoque() - quantidade);
    }

}

