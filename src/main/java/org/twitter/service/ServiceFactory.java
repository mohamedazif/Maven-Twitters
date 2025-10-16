package org.twitter.service;

import org.twitter.service.interfaces.*;

/**
 * To manage object creation in a same place.
 *
 * @version             1.0 15 Oct 2025
 * @author              Mohamed Abdul Azif
 */
public final class ServiceFactory {
    private static final ServiceFactory INSTANCE = new ServiceFactory();

    private ServiceFactory() { }

    /**
     * Creates instance of ServiceFactory to create other objects.
     *
     * @return Instance of ServiceFactory
     */
    public static ServiceFactory getInstance() { return INSTANCE; }

    /**
     * Creates UserRegister Object.
     *
     * @return Object of UserRegister
     */
    public RegisterService getRegisterService() {
        return new UserRegister();
    }

    /**
     * Creates UserLogin Object.
     *
     * @return Object of UserLogin
     */
    public LoginService getLoginService() {
        return new UserLogin();
    }

    /**
     * Creates UserTimeLine object.
     *
     * @return Object of UserTimeLine
     */
    public TimelineService getTimeLineService() {
        return new UserTimeline();
    }

    /**
     * Creates UserProfile Object.
     *
     * @return Object of UserProfile
     */
    public ProfileService getProfileService() {
        return new UserProfile();
    }

    /**
     * Creates PostTweet Object.
     *
     * @return Object of PostTweet
     */
    public TweetService getTweetService() {
        return new TweetPost();
    }

    /**
     * Creates FollowSuggestion Object.
     *
     * @return Object of FollowSuggestion
     */
    public FollowSuggestionService getFollowSuggestionService() {
        return new FollowSuggestions();
    }

    /**
     * Creates object for EditProfile.
     *
     * @return Object of ProfileEdit
     */
    public EditService getEditService() {
        return new EditProfile();
    }
}
