package br.com.vrbeneficios.miniautorizador.application.cartao.service;

import br.com.vrbeneficios.miniautorizador.application.cartao.dto.CartaoDTO;
import br.com.vrbeneficios.miniautorizador.application.cartao.dto.TransacoesDTO;
import br.com.vrbeneficios.miniautorizador.application.cartao.helper.TransacaoHelper;
import br.com.vrbeneficios.miniautorizador.application.cartao.repository.CartaoRepository;
import br.com.vrbeneficios.miniautorizador.domain.model.cartao.Cartao;
import br.com.vrbeneficios.miniautorizador.domain.model.enums.RetornoTrasacaoEnum;
import br.com.vrbeneficios.miniautorizador.exeception.CartaoServiceException;
import br.com.vrbeneficios.miniautorizador.exeception.ObjectNotFoundException;
import br.com.vrbeneficios.miniautorizador.service.CrudService;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Optional;

import static br.com.vrbeneficios.miniautorizador.application.cartao.mapper.CartaoMapper.mapearParaCartao;
import static br.com.vrbeneficios.miniautorizador.application.cartao.mapper.CartaoMapper.mapearParaCartaoDTO;

@Service
public class CartaoService implements CrudService<Cartao, String> {

    private final CartaoRepository cartaoRepository;

    private final TransacaoService transacaoService;

    public CartaoService(CartaoRepository cartaoRepository,
                         TransacaoService transacaoService) {
        this.cartaoRepository = cartaoRepository;
        this.transacaoService = transacaoService;
    }

    public CartaoDTO criarCartao(final CartaoDTO cartaoDTO) {
        this.validaCartaoExistente(cartaoDTO);
        final Cartao cartao = this.salvar(mapearParaCartao(cartaoDTO).comSaldo(new BigDecimal("500")));
        return mapearParaCartaoDTO(cartao);
    }

    private void validaCartaoExistente(final CartaoDTO cartaoDTO){
        Optional<Cartao> cartaoOptional = getRepository().findById(cartaoDTO.getNumeroCartao());
        cartaoOptional.ifPresent(cartao -> {
            throw new CartaoServiceException("Cartão já existente");
        });
    }

    public BigDecimal obterSaldoCartao(final String numeroCartao) throws CartaoServiceException {
        final Optional<Cartao> cartao = cartaoRepository.findById(numeroCartao);

        cartao.orElseThrow(() -> new CartaoServiceException("Cartão com o numero "+cartao+" não encontrado."));

        return cartao.get().getSaldo();
    }

    public RetornoTrasacaoEnum realizarTransacao(final TransacoesDTO transacoesDTO){
        try {
            final Cartao cartao = this.buscarPorId(transacoesDTO.getNumeroCartao());
            final RetornoTrasacaoEnum retornoTrasacaoEnum = TransacaoHelper.validarTransacao(cartao, transacoesDTO);
            if(RetornoTrasacaoEnum.OK.equals(retornoTrasacaoEnum)){
                this.salvar(this.transacaoService.calculaSaldo(cartao, transacoesDTO));
            }
            return retornoTrasacaoEnum;
        } catch (ObjectNotFoundException e){
            return RetornoTrasacaoEnum.CARTAO_INEXISTENTE;
        }
    }

    @Override
    public JpaRepository<Cartao, String> getRepository() {
        return this.cartaoRepository;
    }
}
