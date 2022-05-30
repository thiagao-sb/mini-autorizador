package br.com.vrbeneficios.miniautorizador.application.cartao.service;

import br.com.vrbeneficios.miniautorizador.application.cartao.dto.CartaoDTO;
import br.com.vrbeneficios.miniautorizador.application.cartao.dto.TransacoesDTO;
import br.com.vrbeneficios.miniautorizador.application.cartao.repository.CartaoRepository;
import br.com.vrbeneficios.miniautorizador.domain.model.cartao.Cartao;
import br.com.vrbeneficios.miniautorizador.domain.model.enums.RetornoTrasacaoEnum;
import br.com.vrbeneficios.miniautorizador.exeception.CartaoServiceException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.Optional;

import static java.math.BigDecimal.ONE;
import static java.math.BigDecimal.TEN;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CartaoServiceTest {

    @Autowired
    private CartaoService cartaoService;

    @MockBean
    private CartaoRepository cartaoRepository;

    @Test
    public void teste_criarCartao(){
        when(this.cartaoRepository.findById(Mockito.anyString())).thenReturn(Optional.ofNullable(null));
        when(this.cartaoRepository.save(Mockito.any(Cartao.class))).thenReturn(new Cartao().comNumeroCartao("748596").comSenha("123"));

        final CartaoDTO cartaoDTO = cartaoService.criarCartao(new CartaoDTO().comNumeroCartao("748596").comSenha("123"));

        assertEquals("748596", cartaoDTO.getNumeroCartao());
        assertEquals("123", cartaoDTO.getSenha());
    }

    @Test(expected = CartaoServiceException.class)
    public void test_validaCartaoExistente_com_cartao_inexistente(){
        when(this.cartaoRepository.findById(Mockito.anyString())).thenReturn(Optional.ofNullable(new Cartao().comNumeroCartao("748596")));

        final CartaoDTO cartaoDTO = new CartaoDTO().comNumeroCartao("748596");

        cartaoService.criarCartao(cartaoDTO);
    }

    @Test(expected = CartaoServiceException.class)
    public void test_obterSaldoCartao_cartao_nao_encontrado(){
        when(this.cartaoRepository.findById(Mockito.anyString())).thenReturn(Optional.ofNullable(null));

        cartaoService.obterSaldoCartao("748596");
    }

    @Test
    public void test_obterSaldoCartao_cartao_encontrado(){
        when(this.cartaoRepository.findById(Mockito.anyString())).thenReturn(Optional.ofNullable(new Cartao().comSaldo(TEN)));

        final BigDecimal resultado = cartaoService.obterSaldoCartao("748596");

        assertEquals(TEN, resultado);
    }

    @Test
    public void test_realizarTransacao(){
        when(this.cartaoRepository.findById(Mockito.anyString())).thenReturn(Optional.ofNullable(new Cartao().comNumeroCartao("748596")
                                                                                                                .comSenha("1234")
                                                                                                                .comSaldo(TEN)));
        final TransacoesDTO transacoesDTO = new TransacoesDTO("748596", "1234", ONE);

        final RetornoTrasacaoEnum resultado = cartaoService.realizarTransacao(transacoesDTO);

        assertEquals(RetornoTrasacaoEnum.OK, resultado);
    }

}
