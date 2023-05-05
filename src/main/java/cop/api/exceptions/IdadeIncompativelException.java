package cop.api.exceptions;

public class IdadeIncompativelException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public IdadeIncompativelException() {
        super("Idade Incompativel.");
    }

}
