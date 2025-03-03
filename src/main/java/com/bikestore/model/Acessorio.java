package com.bikestore.model;

public class Acessorio extends Produto {
    private String tipo;
    
    public Acessorio(String nome, double preco, int quantidadeEstoque, String tipo){
        super(nome, preco, quantidadeEstoque);
        this.tipo = tipo;
    }
    
    @Override
    public void atualizarEstoque(int quantidade){
        setQuantidadenoEstoque(getQuantidadenoEstoque() - quantidade);
    }
}
