package com.bikestore;

public abstract class Produto {
    private String nome;
    private double preco;
    private int quantidadenoEstoque;


public Produto(String nome, double preco, int quantidadenoEstoque){
    this.nome = nome;
    this.preco = preco;
    this.quantidadenoEstoque = quantidadenoEstoque;
}

public abstract void atualizarEstoque(int quantidade);

public String getNome(){
    return nome;
}

public double getPreco(){
    return preco;
}

public int getQuantidadenoEstoque(){
    return quantidadenoEstoque;
}

public void setQuantidadenoEstoque(int quantidadenoEstoque){
    this.quantidadenoEstoque = quantidadenoEstoque;
}

}