package com.bikestore.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Cliente {
    private String nome;
    private String cpf;
    private String telefone;
    private String email;
    private TipoCliente tipo;
    private List<VendaSimples> historicoCompras;
    private LocalDate dataCadastro;

    public Cliente() {
        this.tipo = TipoCliente.REGULAR;
        this.historicoCompras = new ArrayList<>();
        this.dataCadastro = LocalDate.now();
    }

    public Cliente(String nome, String cpf, String telefone, String email) {
        this();
        this.nome = nome;
        this.cpf = cpf;
        this.telefone = telefone;
        this.email = email;
    }

    // Getters e Setters
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public TipoCliente getTipo() {
        return tipo;
    }

    public void setTipo(TipoCliente tipo) {
        this.tipo = tipo;
    }

    public LocalDate getDataCadastro() {
        return dataCadastro;
    }

    public void adicionarCompra(VendaSimples venda) {
        this.historicoCompras.add(venda);
        atualizarTipoCliente();
    }

    private void atualizarTipoCliente() {
        double totalCompras = historicoCompras.stream()
                .mapToDouble(VendaSimples::getValorTotal)
                .sum();

        if (totalCompras >= 10000) {
            this.tipo = TipoCliente.VIP;
        } else if (totalCompras >= 5000) {
            this.tipo = TipoCliente.PREMIUM;
        }
    }

    @Override
    public String toString() {
        return String.format("Cliente: %s | CPF: %s | Tipo: %s",
                nome, cpf, tipo.getDescricao());
    }
}