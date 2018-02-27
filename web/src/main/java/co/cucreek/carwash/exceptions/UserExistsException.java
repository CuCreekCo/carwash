package co.cucreek.carwash.exceptions;

/**
 * @author jljdavidson on 2/10/18.
 */
public class UserExistsException extends Exception {
    private static final long serialVersionUID = 8324242758930091811L;
    public UserExistsException() {
        super("User already exists.");
    }
}
