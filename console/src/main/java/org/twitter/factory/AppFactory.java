package org.twitter.factory;

import org.twitter.follow.controller.FollowerController;
import org.twitter.follow.repository.FollowRepositoryImpl;
import org.twitter.follow.service.FollowService;
import org.twitter.follow.service.FollowServiceImpl;
import org.twitter.follow.view.FollowerView;
import org.twitter.profile.controller.ProfileController;
import org.twitter.profile.controller.ProfileEditController;
import org.twitter.profile.repository.EditUserRepositoryImpl;
import org.twitter.profile.service.EditProfileService;
import org.twitter.profile.service.EditProfileServiceImpl;
import org.twitter.profile.service.ProfileService;
import org.twitter.profile.service.ProfileServiceImpl;
import org.twitter.profile.view.ProfileEditView;
import org.twitter.profile.view.ProfileView;
import org.twitter.timeline.controller.TimelineController;
import org.twitter.timeline.repository.TimelineRepositoryImpl;
import org.twitter.timeline.service.TimelineService;
import org.twitter.timeline.service.TimelineServiceImpl;
import org.twitter.timeline.view.TimelineView;
import org.twitter.tweet.controller.TweetController;
import org.twitter.tweet.repository.TweetRepositoryImpl;
import org.twitter.tweet.service.TweetService;
import org.twitter.tweet.service.TweetServiceImpl;
import org.twitter.tweet.view.TweetView;
import org.twitter.user.controller.UserController;
import org.twitter.user.repository.UserRepositoryImpl;
import org.twitter.user.service.UserLoginService;
import org.twitter.user.service.UserRegisterService;
import org.twitter.user.service.UserRetrievalService;
import org.twitter.user.service.impl.UserLoginServiceImpl;
import org.twitter.user.service.impl.UserRegisterServiceImpl;
import org.twitter.user.service.impl.UserRetrievalServiceImpl;
import org.twitter.user.view.UserView;

/**
 * Central factory to create and provide service instances.
 * Acts as the Composition Root to wire up dependencies.
 *
 * @version             1.0
 * @author              Mohamed Azif
 */
public final class AppFactory {
    private static final AppFactory INSTANCE = new AppFactory();
    private static final UserController USER_CONTROLLER = new UserController(
            getInstance().getUserRegisterService(),
            getInstance().getUserLoginService()
    );
    private static final TweetController TWEET_CONTROLLER = new TweetController(
            getInstance().getTweetService()
    );
    private static final TimelineController TIMELINE_CONTROLLER = new TimelineController(
            getInstance().getTimelineService()
    );
    private static final ProfileController PROFILE_CONTROLLER = new ProfileController(
            getInstance().getProfileService()
    );
    private static final FollowerController FOLLOWER_CONTROLLER = new FollowerController(
            getInstance().getFollowService()
    );
    private static final ProfileEditController PROFILE_EDIT_CONTROLLER = new ProfileEditController(
            getInstance().getEditProfileService()
    );

    private AppFactory() { }

    public static AppFactory getInstance() {
        return INSTANCE;
    }

    /**
     * To provide Login service.
     *
     * @return          Login service object along with repository
     */
    public UserLoginService getUserLoginService() {
        return new UserLoginServiceImpl(new UserRepositoryImpl());
    }

    /**
     * To provide Register service.
     *
     * @return          Register service object along with repository
     */
    public UserRegisterService getUserRegisterService() {
        return new UserRegisterServiceImpl(new UserRepositoryImpl());
    }

    /**
     * To provide User retrieval service.
     *
     * @return          User retrieval service object along with repository
     */
    public UserRetrievalService getUserRetrievalService() {
        return new UserRetrievalServiceImpl(new UserRepositoryImpl());
    }

    /**
     * Provides object of UserController.
     *
     * @return          UserController Object
     */
    public UserController getUserController() {
        return USER_CONTROLLER;
    }

    /**
     * Provides object of UserView class.
     *
     * @return          Object of UserView class
     */
    public UserView getUserView() {
        return new UserView(getUserController());
    }

    /**
     * To provide Tweet service.
     *
     * @return          Tweet service object along with repository
     */
    public TweetService getTweetService() {
        return new TweetServiceImpl(new TweetRepositoryImpl());
    }

    /**
     * Provides an object of TweetController class.
     *
     * @return          Object of TweetController class
     */
    public TweetController getTweetController() {
        return TWEET_CONTROLLER;
    }

    /**
     * Provides an object of TweetView class.
     *
     * @return          Object of TweetView class
     */
    public TweetView getTweetView() {
        return new TweetView(getTweetController());
    }

    /**
     * To provide Timeline service.
     *
     * @return          Timeline service object along with repository
     */
    public TimelineService getTimelineService() {
        return new TimelineServiceImpl(new TimelineRepositoryImpl());
    }

    /**
     * Provides an object of TimelineController class.
     *
     * @return          Object of TimelineController class
     */
    public TimelineController getTimelineController() {
        return TIMELINE_CONTROLLER;
    }

    /**
     * Provides an object of TimelineView class.
     *
     * @return          Object of TimelineView class
     */
    public TimelineView getTimelineView() {
        return new TimelineView(getTimelineController());
    }

    /**
     * To provide services related to following users.
     *
     * @return          Follow related service object along with repository
     */
    public FollowService getFollowService() {
        return new FollowServiceImpl(new FollowRepositoryImpl(), getUserRetrievalService());
    }

    /**
     * Provides an object of FollowerController Class.
     *
     * @return          An object of FollowerController class
     */
    public FollowerController getFollowController() {
        return FOLLOWER_CONTROLLER;
    }

    /**
     * Provides an object of FollowerView class.
     *
     * @return          An object of FollowView class
     */
    public FollowerView getFollowerView() {
        return new FollowerView(getFollowController());
    }

    /**
     * To provide profile edit services.
     *
     * @return          Edit profile service object along with repository
     */
    public EditProfileService getEditProfileService() {
        return new EditProfileServiceImpl(new EditUserRepositoryImpl());
    }

    /**
     * Provides an object of ProfileEditController class.
     *
     * @return          An object of ProfileEditController class
     */
    public ProfileEditController getProfileEditController() {
        return PROFILE_EDIT_CONTROLLER;
    }

    /**
     * Provides an object of ProfileEditView class.
     *
     * @return          An object of ProfileEditView class
     */
    public ProfileEditView getProfileEditView() {
        return new ProfileEditView(getProfileEditController());
    }

    /**
     * To provide profile related services.
     *
     * @return          Profile service object along with repository
     */
    public ProfileService getProfileService() {
        return new ProfileServiceImpl(
                getFollowService(),
                getTimelineService(),
                getTweetService()
        );
    }

    /**
     * Provides an object of ProfileController class.
     *
     * @return          An object of ProfileController class
     */
    public ProfileController getProfileController() {
        return PROFILE_CONTROLLER;
    }

    /**
     * Provides an object of ProfileView class.
     *
     * @return          An object of ProfileView class
     */
    public ProfileView getProfileView() {
        return new ProfileView(getProfileController());
    }
}