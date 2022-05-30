package br.com.vrbeneficios.miniautorizador.application.cartao.service;

import br.com.vrbeneficios.miniautorizador.application.cartao.dto.TransacoesDTO;
import br.com.vrbeneficios.miniautorizador.domain.model.cartao.Cartao;
import br.com.vrbeneficios.miniautorizador.exeception.TransacaoServiceException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;

import static java.math.BigDecimal.ONE;
import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TransacaoServiceTest {

    @Autowired
    private TransacaoService transacaoService;

    @Test(expected = TransacaoServiceException.class)
    public void test_calculaSaldo_com_parametros_nulos(){
        transacaoService.calculaSaldo(null, null);
    }

    @Test
    public void test_calculaSaldo(){
        final Cartao cartao = new Cartao().comNumeroCartao("748596").comSenha("1234").comSaldo(new BigDecimal("10.00"));
        final TransacoesDTO transacoesDTO = new TransacoesDTO("748596", "1234", ONE);

        final Cartao restutado = transacaoService.calculaSaldo(cartao, transacoesDTO);

        assertEquals(new BigDecimal("9.00"), restutado.getSaldo());
    }

}
