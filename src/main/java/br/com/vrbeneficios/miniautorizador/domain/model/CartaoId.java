package br.com.vrbeneficios.miniautorizador.domain.model;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;

public class CartaoId implements Serializable {

    @Column(name = "NUMERO_CARTAO")
    private String numeroCartao;

    @Column(name = "SENHA")
    private String senha;

    public CartaoId() {
    }

    public CartaoId(String numeroCartao, String senha) {
        this.numeroCartao = numeroCartao;
        this.senha = senha;
    }

    public String getNumeroCartao() {
        return numeroCartao;
    }

    public String getSenha() {
        return senha;
    }
}
