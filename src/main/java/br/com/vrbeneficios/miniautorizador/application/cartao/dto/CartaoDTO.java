package br.com.vrbeneficios.miniautorizador.application.cartao.dto;

public class CartaoDTO {

    private String numeroCartao;

    private String senha;

    public String getNumeroCartao() {
        return numeroCartao;
    }

    public String getSenha() {
        return senha;
    }

    public CartaoDTO comNumeroCartao(final String numeroCartao) {
        this.numeroCartao = numeroCartao;
        return this;
    }

    public CartaoDTO comSenha(final String senha) {
        this.senha = senha;
        return this;
    }
}
