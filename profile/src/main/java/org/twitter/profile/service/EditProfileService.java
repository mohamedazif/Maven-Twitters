package org.twitter.profile.service;

import org.twitter.user.model.User;

/**
 * Interface for providing edit profile service.
 *
 * @version             1.0
 * @author              Mohamed Azif
 */
public interface EditProfileService {

    void changeUsername(final User user, final String newUsername);

    void changePassword(final User user, final String newPassword);

    void changeAge(final User user, final int newAge);

    void changeBio(final User user, final String newBio);

    void removeAccount(final User user);
}
