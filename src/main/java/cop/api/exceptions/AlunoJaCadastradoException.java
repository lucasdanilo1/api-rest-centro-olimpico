package cop.api.exceptions;

public class AlunoJaCadastradoException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public AlunoJaCadastradoException() {
        super("CPF Já cadastrado");
    }

}
