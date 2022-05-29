package br.com.vrbeneficios.miniautorizador.application.cartao.mapper;

import br.com.vrbeneficios.miniautorizador.application.cartao.dto.CartaoDTO;
import br.com.vrbeneficios.miniautorizador.domain.model.cartao.Cartao;

public class CartaoMapper {

    private CartaoMapper() {
    }

    public static Cartao mapearParaCartao(final CartaoDTO cartaoDTO){
        return new Cartao().comSenha(cartaoDTO.getSenha())
                            .comNumeroCartao(cartaoDTO.getNumeroCartao());
    }

    public static CartaoDTO mapearParaCartaoDTO(final Cartao cartao){
        return new CartaoDTO().comSenha(cartao.getSenha())
                                .comNumeroCartao(cartao.getNumeroCartao());
    }

}
