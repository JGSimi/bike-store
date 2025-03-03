package com.bikestore.controllers.interfaces;

import com.bikestore.model.Produto;

public interface Venda {
    void adicionarProduto(Produto produto, int quantidade);
    double calcularTotal();
    String gerarResumo();
} 