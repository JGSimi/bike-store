package com.bikestore.model;

public class Bicicleta extends Produto{
    private String marca;
    private String modelo;
    private String tamanho;

    public Bicicleta(String nome, double preco, int quantidadeEstoque, String marca, String modelo, String tamanho){
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

