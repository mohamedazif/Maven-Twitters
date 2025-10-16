package org.twitter.service;

import org.twitter.model.User;
import org.twitter.repository.UserRepository;
import org.twitter.service.interfaces.LoginService;

/**
 * Checks credentials and logs in the user.
 *
 * @version                     1.0 15 Oct 2025
 * @author                      Mohamed Abdul Azif
 */
public final class UserLogin implements LoginService {

    /**
     * To check login of the users.
     *
     * @return User object if produced UserId and Password matches the
     *          registered User's UserId and Password else null
     */
    @Override
    public User loginUser(final String userId, final String password) {
        User logInUser = UserRepository.getSpecificUser(userId);

        if (logInUser != null && logInUser.getUserId().equals(userId)
                && logInUser.getPassword().equals(Utility.hashPassword(password))) {
            return logInUser;
        } else {
            return null;
        }
    }
}
