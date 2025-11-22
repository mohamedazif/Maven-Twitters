package user.service.impl;

import org.twitter.user.model.User;
import org.twitter.user.repository.UserRepository;
import org.twitter.user.service.UserRetrievalService;

import java.util.Collection;


public class UserRetrievalServiceImpl implements UserRetrievalService {
    private final UserRepository repository;

    public UserRetrievalServiceImpl(final UserRepository repository) {
        this.repository = repository;
    }

    /**
     * To retrieve a user's details through ID.
     *
     * @param userId        ID of the user to get
     * @return              User object if found else null
     */
    @Override
    public User getUserById(final long userId) {
        return repository.getUserById(userId).orElse(null);
    }

    @Override
    public User getUserByHandle(final String handle) {
        return repository.getUserByHandle(handle).orElse(null);
    }

    @Override
    public Collection<User> getAllUsers() {
        return repository.getAllUsers();
    }
}
