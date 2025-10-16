package org.twitter.repository;

import org.twitter.model.User;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * Repository for storing user and their information.
 *
 * @version             1.0 15-Oct-2025
 * @author              Mohamed Abdul Azif
 */
public final class UserRepository {

    private static final Map<String, User> USERS_LIST = new HashMap<>();

    /**
     * Adds a new user to the repository after validating uniqueness
     * of userId and email.
     *
     * @param user      The user object to register
     * @return          True if registration is successful; false otherwise
     */
    public static boolean addRegisteredUser(final User user) {
        // Check if userId is already in use
        if (USERS_LIST.containsKey(user.getUserId())) {
            System.err.println("User ID already exists!");
            return false;
        }

        // Check if email is already in use
        for (User u : USERS_LIST.values()) {
            if (u.getEmail().equalsIgnoreCase(user.getEmail())) {
                System.err.println("Email is already registered!");
                return false;
            }
        }

        // Both are unique register the user
        USERS_LIST.put(user.getUserId(), user);
        return true;
    }

    /**
     * Static method to get a User who has the specific UserID.
     *
     * @param userId    To retrieve the User with this UserID
     * @return          Returns User Object if UserID is found else null
     */
    public static User getSpecificUser(final String userId) {
        return USERS_LIST.get(userId);
    }

    /**
     * Static method to return all the registered Users.
     *
     * @return Returns a hashmap of Users where UserID is mapped to User Object
     */
    public static Map<String, User> getUsersList() {
        return Collections.unmodifiableMap(USERS_LIST);
    }
}
