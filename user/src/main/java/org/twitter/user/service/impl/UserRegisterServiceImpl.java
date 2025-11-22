package org.twitter.user.service.impl;

import org.twitter.user.model.User;
import org.twitter.user.repository.UserRepository;
import org.twitter.user.service.UserRegisterService;

public final class UserRegisterServiceImpl implements UserRegisterService {
    private final UserRepository repository;

    public UserRegisterServiceImpl(final UserRepository repository) {
        this.repository = repository;
    }
    /**
     * To register a user.
     *
     * @param user  Details of the user as User object
     * @return      Whether user registered or not
     */
    @Override
    public boolean register(final User user) {
        return repository.registerUser(user);
    }
}
