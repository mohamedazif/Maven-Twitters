package org.twitter.user.service;

import org.twitter.user.model.User;
import org.twitter.user.repository.UserDBRepo;

import java.util.Objects;

/**
 * Checks credentials and logs in the user.
 *
 * @version                     1.0
 * @author                      Mohamed Abdul Azif
 */
public final class Login implements LoginService {

    /**
     * To check login of the users.
     *
     * @return User object if produced UserId and Password matches the
     *          registered User's UserId and Password else null
     */
    @Override
    public User loginUser(String userId, String password) {
        User loggedUser = UserDBRepo.getSpecificUser(userId);

        if (!Objects.isNull(loggedUser) && loggedUser.getId().equals(userId)
                && loggedUser.getPassword().equals(password)) {
            System.out.println("true");
            return loggedUser;
        } else {
            return null;
        }
    }
}
