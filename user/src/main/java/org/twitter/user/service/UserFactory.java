package org.twitter.user.service;

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
    public RegisterService getRegisterService() {
        return new Register();
    }

    /**
     * Creates Login Object.
     *
     * @return Object of Login
     */
    public LoginService getLoginService() {
        return new Login();
    }

    /**
     * Creates object for EditProfile.
     *
     * @return Object of EditProfile
     */
    public EditProfileService getEditService() {
        return new EditProfile();
    }
}
