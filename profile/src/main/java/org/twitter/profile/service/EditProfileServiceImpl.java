package org.twitter.profile.service;

import org.twitter.profile.repository.EditUserRepository;
import org.twitter.user.model.User;
import org.twitter.user.service.UserValidationUtils;

import java.util.Objects;

/**
 * To edit the details of the user.
 *
 * @version             1.0
 * @author              Mohamed Abdul Azif
 */
public final class EditProfileServiceImpl implements EditProfileService {
    private final EditUserRepository repository;

    public EditProfileServiceImpl(EditUserRepository editUserRepository) {
        this.repository = editUserRepository;
    }

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
        repository.updateUsername(user.getId(), newUsername.trim());
    }

    /**
     * To change the password.
     *
     * @param user          Logged-in User
     * @param newPassword   New password to change
     */
    @Override
    public void changePassword(final User user, final String newPassword) {
        if (UserValidationUtils.isValidPassword(newPassword)) {
            repository.updatePassword(user.getId(),
                    UserValidationUtils.hashPassword(newPassword));
        } else {
            throw new IllegalArgumentException("Invalid password format!");
        }
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
        repository.updateAge(user.getId(), newAge);
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
        repository.updateBio(user.getId(), newBio.trim());
    }

    /**
     * To remove the account.
     *
     * @param user Logged-in user
     */
    @Override
    public void removeAccount(User user) {
        repository.deleteUser(user.getId());
    }
}

