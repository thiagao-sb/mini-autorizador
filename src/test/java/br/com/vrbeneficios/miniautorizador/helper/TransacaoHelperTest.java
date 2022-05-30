package br.com.vrbeneficios.miniautorizador.helper;

import br.com.vrbeneficios.miniautorizador.application.cartao.dto.TransacoesDTO;
import br.com.vrbeneficios.miniautorizador.application.cartao.helper.TransacaoHelper;
import br.com.vrbeneficios.miniautorizador.domain.model.cartao.Cartao;
import br.com.vrbeneficios.miniautorizador.domain.model.enums.RetornoTrasacaoEnum;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;

import static org.junit.Assert.assertEquals;

@SpringBootTest
public class TransacaoHelperTest {

    @Test
    public void test_validarTransacao_saldo_maior(){
        final Cartao cartao = new Cartao().comNumeroCartao("123").comSenha("456").comSaldo(BigDecimal.TEN);
        final TransacoesDTO transacoesDTO = new TransacoesDTO("123", "456", BigDecimal.ONE);

        RetornoTrasacaoEnum resultado = TransacaoHelper.validarTransacao(cartao, transacoesDTO);

        assertEquals(RetornoTrasacaoEnum.OK, resultado);
    }

    @Test
    public void test_validarTransacao_saldo_menor(){
        final Cartao cartao = new Cartao().comNumeroCartao("123").comSenha("456").comSaldo(BigDecimal.ONE);
        final TransacoesDTO transacoesDTO = new TransacoesDTO("123", "456", BigDecimal.TEN);

        RetornoTrasacaoEnum resultado = TransacaoHelper.validarTransacao(cartao, transacoesDTO);

        assertEquals(RetornoTrasacaoEnum.SALDO_INSUFICIENTE, resultado);
    }

    @Test
    public void test_validarTransacao_com_senha_invalida(){
        final Cartao cartao = new Cartao().comNumeroCartao("123").comSenha("456").comSaldo(BigDecimal.ONE);
        final TransacoesDTO transacoesDTO = new TransacoesDTO("123", "000", BigDecimal.TEN);

        RetornoTrasacaoEnum resultado = TransacaoHelper.validarTransacao(cartao, transacoesDTO);

        assertEquals(RetornoTrasacaoEnum.SENHA_INVALIDA, resultado);
    }

    @Test
    public void test_validarTransacao_com_cartao_nao_encontrado(){
        final Cartao cartao = null;
        final TransacoesDTO transacoesDTO = new TransacoesDTO("000", "456", BigDecimal.TEN);

        RetornoTrasacaoEnum resultado = TransacaoHelper.validarTransacao(cartao, transacoesDTO);

        assertEquals(RetornoTrasacaoEnum.CARTAO_INEXISTENTE, resultado);
    }

}
