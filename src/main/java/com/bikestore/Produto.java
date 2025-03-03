package com.bikestore;

public abstract class Produto {
    private String nome;
    private double preco;
    private int quantidadeEstoque;


public Produto(String nome, double preco, int quantidadeEstoque){
    this.nome = nome;
    this.preco = preco;
    this.quantidadeEstoque = quantidadeEstoque;
}

public abstract void atualizarEstoque(int quantidade);

public String getNome(){
    return nome;
}

public double getPreco(){
    return preco;
}

}