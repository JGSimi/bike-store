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

    /**
     * Atualiza o estoque subtraindo a quantidade especificada
     *
     * @param quantidade Quantidade a ser removida do estoque
     */
    @Override
    public void atualizarEstoque(int quantidade) {
        setQuantidadenoEstoque(getQuantidadenoEstoque() - quantidade);
    }

    // Getter
    public String getTipo() {
        return tipo;
    }

    // Setter
    public void setTipo(String Tipo) {
        this.tipo = Tipo;
    }

    @Override
    public String toString() {
        return "Acessorio{" +
                "nome='" + getNome() + '\'' +
                ", preco=" + getPreco() +
                ", quantidadeEstoque=" + getQuantidadenoEstoque() +
                ", tipo='" + tipo + '\'' +
                '}';
    }
}

