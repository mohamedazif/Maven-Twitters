package org.twitter.controller;

import org.twitter.model.User;
import org.twitter.service.ServiceFactory;
import org.twitter.service.interfaces.EditService;

/**
 * To coordinate the control between view and EditService.
 *
 * @version             1.0 15 Oct 2025
 * @author              Mohamed Abdul Azif
 */
public class ProfileEditController {

    private final EditService editService;

    public ProfileEditController() {
        this.editService = ServiceFactory.getInstance().getEditService();
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
     * @param user      Logged-in User
     * @param newAge    New age of the user
     */
    public void changeAge(final User user, final int newAge) {
        editService.changeAge(user, newAge);
    }

    /**
     * To change the bio.
     *
     * @param user      Logged-in User
     * @param newBio    New Bio of the user
     */
    public void changeBio(final User user, final String newBio) {
        editService.changeBio(user, newBio);
    }
}
