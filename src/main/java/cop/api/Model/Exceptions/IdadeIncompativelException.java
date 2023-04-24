package cop.api.Model.Exceptions;

public class IdadeIncompativelException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public IdadeIncompativelException() {
        super("Idade Incompativel.");
    }

}
