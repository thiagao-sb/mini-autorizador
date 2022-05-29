package br.com.vrbeneficios.miniautorizador.exeception;

public class DataIntegrityException extends RuntimeException {

    public static final String MSG_SALVAR = "Não foi possivel salvar";
    public static final String MSG_SALVAR_LISTA = "Não foi possivel salvar lsita de objetos";
    public static final String MSG_ADD = "Não foi possivel adicionar";
    public static final String MSG_EDITAR = "Não foi possivel editar";
    public static final String MSG_REMOVER = "Não foi possivel remover";

    public DataIntegrityException(final String msg, final String id, final Class<?> tipo) {
        super(msg + ". ID: " + id.toString() + ". Tipo: " + tipo.getName());
    }

    public DataIntegrityException(final String msg, final Class<?> tipo) {
        super(msg + ". Tipo: " + tipo.getName());
    }

    public DataIntegrityException(final String msg) {
        super(msg);
    }
}
