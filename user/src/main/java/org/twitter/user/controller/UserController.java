package org.twitter.user.controller;

import org.twitter.user.model.User;
import org.twitter.user.service.LoginService;
import org.twitter.user.service.RegisterService;
import org.twitter.user.service.UserFactory;
import org.twitter.user.service.Utility;

/**
 * To coordinate the control between the view and User-related Services.
 *
 * @version             1.0
 * @author              Mohamed Abdul Azif
 */
public final class UserController {

    private final RegisterService registerService;
    private final LoginService loginService;

    public UserController() {
        UserFactory factory = UserFactory.getInstance();
        this.registerService = factory.getRegisterService();
        this.loginService = factory.getLoginService();
    }

    /**
     * To register the user with provided credentials.
     *
     * @param userName      Name of the user
     * @param email         E-mail ID of the user
     * @param userID        User-ID created by the user
     * @param password      Password provided by the user
     * @param age           Age of the user
     * @param bio           Biography of the user
     * @return              Whether the user registered or not
     */
    public boolean registerUser(final String userID, final String email,
                                final String userName, final String password,
                                final int age, final String bio) {
        User user = new User(userID, email, userName,
                Utility.hashPassword(password), age, bio);
        return registerService.registerUser(user);
    }

    /**
     * To log in the user by checking the credentials.
     *
     * @param userId        User-Id of the user trying to log in
     * @param password      Password provided by the user
     * @return              If credentials match User object otherwise null
     */
    public User loginUser(final String userId, final String password) {
        return loginService.loginUser(userId, password);
    }
}
