package br.com.vrbeneficios.miniautorizador.application.cartao.dto;

import java.math.BigDecimal;

public class TransacoesDTO {

    private final String numeroCartao;
    private final String senhaCartao;
    private final BigDecimal valor;

    public TransacoesDTO(String numeroCartao, String senhaCartao, BigDecimal valor) {
        this.numeroCartao = numeroCartao;
        this.senhaCartao = senhaCartao;
        this.valor = valor;
    }

    public String getNumeroCartao() {
        return numeroCartao;
    }

    public String getSenhaCartao() {
        return senhaCartao;
    }

    public BigDecimal getValor() {
        return valor;
    }
}
