package org.twitter.user.service;

import org.twitter.user.model.User;
import org.twitter.user.repository.UserDBRepo;

public final class Register implements RegisterService {

    /**
     * To register a user.
     *
     * @param       user Details of the user as User object
     * @return      Whether user registered or not
     */
    @Override
    public boolean registerUser(User user) {
        return UserDBRepo.addRegisteredUser(user);
    }
}
