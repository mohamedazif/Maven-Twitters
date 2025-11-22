package user.exceptions;

public class RegistrationFailedException extends RuntimeException{

    public RegistrationFailedException(final String message) {
        super(message);
    }
}
