package com.bikestore.model;

public enum TipoCliente {
    REGULAR("Cliente Regular", 0.0),
    PREMIUM("Cliente Premium", 0.05),
    VIP("Cliente VIP", 0.10);

    private final String descricao;
    private final double percentualDesconto;

    TipoCliente(String descricao, double percentualDesconto) {
        this.descricao = descricao;
        this.percentualDesconto = percentualDesconto;
    }

    public String getDescricao() {
        return descricao;
    }

    public double getPercentualDesconto() {
        return percentualDesconto;
    }
}