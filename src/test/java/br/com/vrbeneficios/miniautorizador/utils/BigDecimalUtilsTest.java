package br.com.vrbeneficios.miniautorizador.utils;

import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

@SpringBootTest
public class BigDecimalUtilsTest {

    @Test
    public void test_valorUmMenorQueValorDois_com_valor_um_menor_que_dois(){
        boolean restultado = BigDecimalUtils.valorUmMenorQueValorDois(BigDecimal.ZERO, BigDecimal.TEN);
        assertTrue(restultado);
    }

    @Test
    public void test_valorUmMenorQueValorDois_com_valor_um_maior_que_dois(){
        boolean restultado = BigDecimalUtils.valorUmMenorQueValorDois(BigDecimal.TEN, BigDecimal.ZERO);
        assertFalse(restultado);
    }

    @Test
    public void test_valorUmMenorQueValorDois_com_valor_um_nulo(){
        boolean restultado = BigDecimalUtils.valorUmMenorQueValorDois(null, BigDecimal.TEN);
        assertTrue(restultado);
    }

    @Test
    public void test_valorUmMenorQueValorDois_com_valor_dois_nulo(){
        boolean restultado = BigDecimalUtils.valorUmMenorQueValorDois(BigDecimal.TEN, null);
        assertFalse(restultado);
    }



}
