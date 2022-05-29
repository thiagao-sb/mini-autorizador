package br.com.vrbeneficios.miniautorizador.domain.model;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@IdClass(CartaoId.class)
@Table(name = "CARTAO")
public class Cartao {

    @Id
    @Column(name = "NUMERO_CARTAO")
    private String numeroCartao;

    @Id
    @Column(name = "SENHA")
    private String senha;

    @Column(name = "SALDO")
    private BigDecimal saldo;

    public String getNumeroCartao() {
        return numeroCartao;
    }

    public String getSenha() {
        return senha;
    }

    public BigDecimal getSaldo() {
        return saldo;
    }

    public Cartao comNumeroCartao(final String numeroCartao) {
        this.numeroCartao = numeroCartao;
        return this;
    }

    public Cartao comSenha(final String senha) {
        this.senha = senha;
        return this;
    }

    public Cartao comSaldo(final BigDecimal saldo) {
        this.saldo = saldo;
        return this;
    }
}
