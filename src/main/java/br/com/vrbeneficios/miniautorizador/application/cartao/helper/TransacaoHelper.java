package br.com.vrbeneficios.miniautorizador.application.cartao.helper;

import br.com.vrbeneficios.miniautorizador.application.cartao.dto.TransacoesDTO;
import br.com.vrbeneficios.miniautorizador.domain.model.cartao.Cartao;
import br.com.vrbeneficios.miniautorizador.domain.model.enums.RetornoTrasacaoEnum;
import br.com.vrbeneficios.miniautorizador.utils.BigDecimalUtils;

import static br.com.vrbeneficios.miniautorizador.utils.BigDecimalUtils.*;

public class TransacaoHelper {

    private TransacaoHelper() {
    }

    public static RetornoTrasacaoEnum validarTransacao(final Cartao cartao, final TransacoesDTO transacoesDTO){
        if (cartao == null) {
            return RetornoTrasacaoEnum.CARTAO_INEXISTENTE;
        } else if (!cartao.getSenha().equals(transacoesDTO.getSenhaCartao())) {
            return RetornoTrasacaoEnum.SENHA_INVALIDA;
        } else if (valorUmMenorQueValorDois(cartao.getSaldo(), transacoesDTO.getValor())) {
            return RetornoTrasacaoEnum.SALDO_INSUFICIENTE;
        }
        return RetornoTrasacaoEnum.OK;
    }

}
