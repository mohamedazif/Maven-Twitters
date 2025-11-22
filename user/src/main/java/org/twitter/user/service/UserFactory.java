package org.twitter.user.service;

import org.twitter.user.repository.UserRepositoryImpl;
import org.twitter.user.service.impl.UserLoginServiceImpl;
import org.twitter.user.service.impl.UserRegisterServiceImpl;

/**
 * To manage object creation of user services in a same place.
 *
 * @version             1.0
 * @author              Mohamed Abdul Azif
 */
public final class UserFactory {

    private static final UserFactory INSTANCE = new UserFactory();

    private UserFactory() { }

    /**
     * Creates instance of ServiceFactory to create other objects.
     *
     * @return Instance of ServiceFactory
     */
    public static UserFactory getInstance() { return INSTANCE; }

    /**
     * Creates Register Object.
     *
     * @return Object of Register
     */
    public UserRegisterService getRegisterService() {
        return new UserRegisterServiceImpl(new UserRepositoryImpl());
    }

    /**
     * Creates Login Object.
     *
     * @return Object of Login
     */
    public UserLoginService getLoginService() {
        return new UserLoginServiceImpl(new UserRepositoryImpl());
    }
}
