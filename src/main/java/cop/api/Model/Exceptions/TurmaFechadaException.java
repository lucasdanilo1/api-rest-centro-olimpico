package cop.api.Model.Exceptions;

public class TurmaFechadaException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public TurmaFechadaException() {
        super("Turma Fechada.");
    }

}