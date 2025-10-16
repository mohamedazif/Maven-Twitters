package org.twitter.service;

import java.util.Objects;

/**
 * Utility class for checking if the Email provided is valid or not,
 * provided password matches the pattern and to provide the tweetId.
 *
 * @version         1.0 15 Oct 2025
 * @author          Mohamed Azif
 */
public final class Utility {

    // Private constructor to block from initialization
    private Utility() { }

    private static int tweetId = 1;

    /**
     * To check the email format.
     *
     * @param email     Email produced by the user
     * @return          true if format matches else false
     */
    public static boolean isValidEmail(final String email) {
        final String emailRegex = "^[A-Za-z0-9+.-_]+@[A-Za-z0-9.-]+$";
        return email.matches(emailRegex);
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
     * To provide unique ID for the Tweets.
     *
     * @return Unique ID for Tweets
     */
    public static int nextTweetId() {
        return tweetId++;
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

