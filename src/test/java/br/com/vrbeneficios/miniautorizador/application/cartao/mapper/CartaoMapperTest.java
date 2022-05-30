package br.com.vrbeneficios.miniautorizador.application.cartao.mapper;

import br.com.vrbeneficios.miniautorizador.application.cartao.dto.CartaoDTO;
import br.com.vrbeneficios.miniautorizador.domain.model.cartao.Cartao;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static java.math.BigDecimal.TEN;
import static org.junit.Assert.assertEquals;

@SpringBootTest
public class CartaoMapperTest {

    @Test
    public void mapearParaCartao(){
        final CartaoDTO cartaoDTO = new CartaoDTO().comNumeroCartao("123456789").comSenha("1234");

        final Cartao resultado = CartaoMapper.mapearParaCartao(cartaoDTO);

        assertEquals("123456789", resultado.getNumeroCartao());
        assertEquals("1234", resultado.getSenha());
    }

    @Test
    public void mapearParaCartaoDTO(){
        final Cartao cartao = new Cartao().comNumeroCartao("987654321").comSenha("9874").comSaldo(TEN);

        final CartaoDTO resultado = CartaoMapper.mapearParaCartaoDTO(cartao);

        assertEquals("987654321", resultado.getNumeroCartao());
        assertEquals("9874", resultado.getSenha());

    }

}
