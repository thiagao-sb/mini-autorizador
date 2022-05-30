package br.com.vrbeneficios.miniautorizador.exeception;

public class CartaoServiceException extends RuntimeException{

    public CartaoServiceException(final String msg) {
        super(msg);
    }

}
