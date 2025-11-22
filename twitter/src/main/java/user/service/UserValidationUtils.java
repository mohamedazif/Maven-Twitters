package user.service;

import java.util.Objects;

/**
 * Utility class for checking if the Email provided is valid or not,
 * provided password matches the pattern and to hash the password.
 *
 * @version         1.0
 * @author          Mohamed Azif
 */
public final class UserValidationUtils {

    // Private constructor to block from initialization
    private UserValidationUtils() { }

    /**
     * To check the email format.
     *
     * @param email     Email produced by the user
     * @return          true if format matches else false
     */
    public static boolean isValidEmail(final String email) {
        return email.matches("^[A-Za-z0-9+.-_]+@[A-Za-z0-9.-]+$");
    }

    /**
     * To check password format.
     *
     * @param password  Password created by the user
     * @return          true if format matches else false
     */
    public static boolean isValidPassword(final String password) {
//        final String pwdRegex = "^(?=.*[A-Z])(?=.*[a-z])(?=.*\\d).{8,}$";
//        return password.matches(pwdRegex);
        return password.matches("^(?=.*[A-Z])(?=.*[a-z])(?=.*\\d).{8,}$");
    }

    /**
     * To hash the given password for storing.
     *
     * @param password  User created password
     * @return          Hash value of the password in string format
     */
    public static String hashPassword(final String password) {
        return String.valueOf(Objects.hash(password));
    }
}

