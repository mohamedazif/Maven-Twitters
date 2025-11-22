package org.twitter.user.service;

import org.twitter.user.model.User;

import java.util.Optional;

/**
 * Interface for providing login service.
 *
 * @version             1.0
 * @author              Mohamed Azif
 */
public interface UserLoginService {

    Optional<User> login(final String handle, final String password);
}