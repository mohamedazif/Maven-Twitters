package org.twitter.service;

import org.twitter.model.User;
import org.twitter.repository.UserRepository;
import org.twitter.service.interfaces.RegisterService;

/**
 * Registering the user into the application with credentials
 *
 * @version                 1.0 15-Oct-2025
 * @author                  Mohamed Abdul Azif
 */
public final class UserRegister implements RegisterService {

    /**
     * To Register the user in our app.
     */
    @Override
    public boolean registerUser(final User user) {
        return UserRepository.addRegisteredUser(user);
    }
}
