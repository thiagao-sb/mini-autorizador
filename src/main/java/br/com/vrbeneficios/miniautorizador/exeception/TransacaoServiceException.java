package br.com.vrbeneficios.miniautorizador.exeception;

public class TransacaoServiceException extends RuntimeException{

    public TransacaoServiceException(String message, Throwable cause) {
        super(message, cause);
    }
}
