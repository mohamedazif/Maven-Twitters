package org.twitter.user.controller;

import org.twitter.user.model.User;
import org.twitter.user.service.UserLoginService;
import org.twitter.user.service.UserRegisterService;
import org.twitter.user.service.UserFactory;
import org.twitter.user.service.UserValidationUtils;

import java.util.Optional;

/**
 * To coordinate the control between the view and User-related Services.
 *
 * @version             1.0
 * @author              Mohamed Abdul Azif
 */
public final class UserController {

    private final UserRegisterService userRegisterService;
    private final UserLoginService userLoginService;

    public UserController() {
        final UserFactory factory = UserFactory.getInstance();
        this.userRegisterService = factory.getRegisterService();
        this.userLoginService = factory.getLoginService();
    }

    public UserController(final UserRegisterService registerService, final UserLoginService loginService) {
        this.userRegisterService = registerService;
        this.userLoginService = loginService;
    }
    /**
     * To register the user with provided credentials.
     *
     * @param handle        User-ID created by the user
     * @param email         E-mail ID of the user
     * @param name          Name of the user
     * @param password      Password provided by the user
     * @param age           Age of the user
     * @param bio           Biography of the user
     * @return              Whether the user registered or not
     */
    public boolean registerUser(final String handle, final String email,
                                final String name, final String password,
                                final int age, final String bio) {
        final User user = new User.UserBuilder()
                .handle(handle)
                .email(email)
                .name(name)
                .password(UserValidationUtils.hashPassword(password))
                .age(age)
                .bio(bio)
                .build();

        return userRegisterService.register(user);
    }

    /**
     * To log in the user by checking the credentials.
     *
     * @param handle        User-Id of the user trying to log in
     * @param password      Password provided by the user
     * @return              If credentials match User object otherwise null
     */
    public User loginUser(final String handle, final String password) {
        final Optional<User> loggedUser = userLoginService.login(handle,
                UserValidationUtils.hashPassword(password));

        return loggedUser.orElse(null);
    }
}