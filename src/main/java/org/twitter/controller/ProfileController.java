package org.twitter.controller;

import org.twitter.model.User;
import org.twitter.service.ServiceFactory;
import org.twitter.service.interfaces.ProfileService;

import java.util.List;

/**
 * To coordinate the control between View and ProfileService.
 *
 * @version             1.0 15 Oct 2025
 * @author              Mohamed Abdul Azif
 */
public final class ProfileController {
    private final ProfileService profileService;

    public ProfileController() {
        this.profileService = ServiceFactory.getInstance().getProfileService();
    }

    /**
     * To get tweets and retweets of the User.
     *
     * @param user  Logged-in User
     * @return      List containing tweets and retweets of the user
     */
    public List<Object> getUserProfileTweets(final User user) {
        return profileService.getUserProfileTweets(user);
    }
}
