package br.com.vrbeneficios.miniautorizador.application.cartao.service;

import br.com.vrbeneficios.miniautorizador.application.cartao.dto.CartaoDTO;
import br.com.vrbeneficios.miniautorizador.application.cartao.repository.CartaoRepository;
import br.com.vrbeneficios.miniautorizador.domain.model.Cartao;
import br.com.vrbeneficios.miniautorizador.domain.model.CartaoId;
import br.com.vrbeneficios.miniautorizador.exeception.CartaoJaCadastradoException;
import br.com.vrbeneficios.miniautorizador.exeception.ObjectNotFoundException;
import br.com.vrbeneficios.miniautorizador.service.CrudService;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Optional;

import static br.com.vrbeneficios.miniautorizador.application.cartao.mapper.CartaoMapper.mapearParaCartao;
import static br.com.vrbeneficios.miniautorizador.application.cartao.mapper.CartaoMapper.mapearParaCartaoDTO;

@Service
public class CartaoService implements CrudService<Cartao, CartaoId> {

    private final CartaoRepository cartaoRepository;

    public CartaoService(CartaoRepository cartaoRepository) {
        this.cartaoRepository = cartaoRepository;
    }

    public CartaoDTO criarCartao(final CartaoDTO cartaoDTO) {
        this.validaCartaoExistente(cartaoDTO);
        final Cartao cartao = this.salvar(mapearParaCartao(cartaoDTO).comSaldo(new BigDecimal("500")));
        return mapearParaCartaoDTO(cartao);
    }

    private void validaCartaoExistente(final CartaoDTO cartaoDTO){
        Optional<Cartao> cartaoOptional = getRepository().findById(new CartaoId(cartaoDTO.getNumeroCartao(), cartaoDTO.getSenha()));
        cartaoOptional.ifPresent(cartao -> {
            try {
                throw new CartaoJaCadastradoException("Cartão já existente");
            } catch (CartaoJaCadastradoException e) {
                throw new RuntimeException(e);
            }
        });
    }

    @Override
    public JpaRepository<Cartao, CartaoId> getRepository() {
        return this.cartaoRepository;
    }
}
