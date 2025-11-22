package org.twitter.profile.controller;

import org.twitter.user.model.User;
import org.twitter.profile.service.EditProfileService;

/**
 * To coordinate the control between view and EditService.
 *
 * @version             1.0
 * @author              Mohamed Abdul Azif
 */
public final class ProfileEditController {

    private final EditProfileService editService;

    public ProfileEditController(final EditProfileService editService) {
        this.editService = editService;
    }

    /**
     * To change the username.
     *
     * @param user          Logged-in User
     * @param newUsername   New Username to change
     */
    public void changeUsername(final User user, final String newUsername) {
        editService.changeUsername(user, newUsername);
    }

    /**
     * To change the password.
     *
     * @param user          Logged-in User
     * @param newPassword   New password to change
     */
    public void changePassword(final User user, final String newPassword) {
        editService.changePassword(user, newPassword);
    }

    /**
     * To change the age.
     *
     * @param user          Logged-in User
     * @param newAge        New age of the user
     */
    public void changeAge(final User user, final int newAge) {
        editService.changeAge(user, newAge);
    }

    /**
     * To change the bio.
     *
     * @param user          Logged-in User
     * @param newBio        New Bio of the user
     */
    public void changeBio(final User user, final String newBio) {
        editService.changeBio(user, newBio);
    }

    /**
     * To delete the user account.
     *
     * @param user          Logged-in User
     */
    public void removeAccount(final User user) {
        editService.removeAccount(user);
    }
}
