package com.bikestore.model;

public class Bicicleta extends Produto{
    private String marca;
    private String modelo;
    private String tamanho;

    /**
     * @param nome              Nome da bicicleta
     * @param preco             Preço da bicicleta
     * @param quantidadeEstoque Quantidade inicial em estoque da bicicleta
     * @param marca             Marca da bicicleta
     * @param modelo            Modelo da bicicleta
     * @param tamanho           Tamanho da bicicleta
     */
    public Bicicleta(String nome, double preco, int quantidadeEstoque, String marca, String modelo, String tamanho) {
        super(nome, preco, quantidadeEstoque);
        this.marca = marca;
        this.modelo = modelo;
        this.tamanho = tamanho;
    }

    /**
     * Reduz o estoque da bicicleta de acordo com a quantidade fornecida
     *
     * @param quantidade Quantidade a ser subtraida do estoque
     */
    @Override
    public void atualizarEstoque(int quantidade) {
        this.setQuantidadenoEstoque(this.getQuantidadenoEstoque() - quantidade);
    }

    public String getMarca() {
        return marca;
    }

    public String getModelo() {
        return modelo;
    }

    public String getTamanho() {
        return tamanho;
    }

    public void setMarca(String Marca) {
        this.marca = Marca;
    }

    public void setModelo(String Modelo) {
        this.modelo = Modelo;
    }

    public void setTamanho(String Tamanho) {
        this.tamanho = Tamanho;
    }

    /**
     * Retorna um texto das caracteristicas da bicicleta
     *
     * @return Representação em forma de string da bicicleta
     */
    @Override
    public String toString() {
        return "Bicicleta{" +
                "nome='" + getNome() + '\'' +
                ", preco=" + getPreco() +
                ", quantidadeEstoque=" + getQuantidadenoEstoque() +
                ", marca='" + marca + '\'' +
                ", modelo='" + modelo + '\'' +
                ", tamanho='" + tamanho + '\'' +
                '}';
    }
}

