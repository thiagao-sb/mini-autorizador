package br.com.vrbeneficios.miniautorizador.application.cartao.service;

import br.com.vrbeneficios.miniautorizador.application.cartao.dto.TransacoesDTO;
import br.com.vrbeneficios.miniautorizador.domain.model.cartao.Cartao;
import br.com.vrbeneficios.miniautorizador.exeception.TransacaoServiceException;
import org.springframework.stereotype.Service;

@Service
public class TransacaoService {

    public Cartao calculaSaldo(final Cartao cartao, TransacoesDTO transacoesDTO){
        try {
            return cartao.comSaldo(cartao.getSaldo().subtract(transacoesDTO.getValor()));
        } catch (Exception e){
            throw new TransacaoServiceException("Erro ao calcular saldo", e);
        }
    }

}
