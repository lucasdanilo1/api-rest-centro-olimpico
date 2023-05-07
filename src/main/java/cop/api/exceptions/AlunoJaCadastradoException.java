package cop.api.exceptions;

public class AlunoJaCadastradoException extends RuntimeException {

    public AlunoJaCadastradoException() {
        super("CPF Já cadastrado");
    }

}
