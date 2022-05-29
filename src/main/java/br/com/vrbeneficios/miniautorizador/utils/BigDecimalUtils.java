package br.com.vrbeneficios.miniautorizador.utils;

import java.math.BigDecimal;

public class BigDecimalUtils {

    private BigDecimalUtils() {
    }

    public static boolean valorUmMenorQueValorDois(BigDecimal valor1, BigDecimal valor2){
        if(valor1 == null){
            valor1 = BigDecimal.ZERO;
        }
        if(valor2 == null){
            valor2 = BigDecimal.ZERO;
        }
        return valor1.compareTo(valor2) == -1;
    }

}
