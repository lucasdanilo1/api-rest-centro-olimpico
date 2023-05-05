package cop.api.exceptions;

public class TurmaFechadaException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public TurmaFechadaException() {
        super("Turma Fechada.");
    }

}