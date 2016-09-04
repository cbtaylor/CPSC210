package exceptions;

/**
 * Indicates that the user is out of time!
 */
public class TimeIsUpException extends Exception {

    private static final long serialVersionUID = 1L;

    public TimeIsUpException(String msg) {
        super(msg);
    }

}
