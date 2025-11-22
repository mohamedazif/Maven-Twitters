package org.twitter.user.service;

import org.twitter.user.model.User;

import java.util.Collection;

/**
 * Interface for User look up.
 *
 * @version             1.0
 * @author              Mohamed Azif
 */
public interface UserRetrievalService {
    User getUserById(final long userId);

    User getUserByHandle(final String handle);

    Collection<User> getAllUsers();
}
