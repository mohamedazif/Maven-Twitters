package org.twitter.service;

import org.twitter.model.User;
import org.twitter.service.interfaces.EditService;

import java.util.Objects;

/**
 * To edit the details of the user.
 *
 * @version             1.0 15-Oct-2025
 * @author              Mohamed Abdul Azif
 */
public final class EditProfile implements EditService {

    /**
     * To change the username.
     *
     * @param user          Logged-in User
     * @param newUsername   New Username to change
     */
    @Override
    public void changeUsername(final User user, final String newUsername) {

        if (Objects.isNull(newUsername) || newUsername.isEmpty()) {
            throw new IllegalArgumentException("Username cannot be empty.");
        }
        user.setUserName(newUsername.trim());
    }

    /**
     * To change the password.
     *
     * @param user          Logged-in User
     * @param newPassword   New password to change
     */
    @Override
    public void changePassword(final User user, final String newPassword) {
        if (Utility.isValidPassword(newPassword)) {
            user.setPassword(Utility.hashPassword(newPassword));
            return;
        }
        throw new IllegalArgumentException("Invalid password format!");
    }

    /**
     * To change the age.
     *
     * @param user      Logged-in User
     * @param newAge    New age of the user
     */
    @Override
    public void changeAge(final User user, final int newAge) {
        if (0 >= newAge) {
            throw new IllegalArgumentException("Age must be positive.");
        }
        user.setAge(newAge);
    }

    /**
     * To change the bio.
     *
     * @param user      Logged-in User
     * @param newBio    New Bio of the user
     */
    @Override
    public void changeBio(final User user, final String newBio) {
        if (Objects.isNull(newBio)) {
            throw new IllegalArgumentException("Bio cannot be null.");
        }
        user.setBio(newBio.trim());
    }
}
