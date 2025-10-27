package org.twitter.controller;

import org.twitter.model.User;
import org.twitter.service.ServiceFactory;
import org.twitter.service.interfaces.FollowSuggestionService;

import java.util.List;
import java.util.Set;

/**
 * To coordinate the control between view and FollowSuggestionService.
 *
 * @version         1.0 15 Oct 2025
 * @author          Mohamed Abdul Azif
 */
public final class FollowSuggestionController {

    private final FollowSuggestionService followSuggestionService;

    public FollowSuggestionController() {
        this.followSuggestionService = ServiceFactory.getInstance().getFollowSuggestionService();
    }

    /**
     * For Suggesting some members to the user.
     *
     * @param user      Logged-in User
     * @return          List of suggesting members
     */
    public List<User> getSuggestedUser(final User user) {
        return followSuggestionService.getSuggestedUsers(user);
    }

    /**
     * Make the logged-in user to follow another user.
     *
     * @param follower  Logged-in User
     * @param toFollow  Member who user wants to follow
     */
    public void followUser(final User follower, final User toFollow) {
        followSuggestionService.followUser(follower, toFollow);
    }

    /**
     * Gets the list followers of the user.
     *
     * @param userId    Logged-in User ID
     * @return          Set of user's followers
     */
    public Set<String> getFollowers(final String userId) {
        return followSuggestionService.getFollowers(userId);
    }

    /**
     * Gets the set of users who are followed by the logged-user.
     *
     * @param userId    Logged-in User Id
     * @return          Set of users followed by the logged-user.
     */
    public Set<String> getFollowing(final String userId) {
        return followSuggestionService.getFollowing(userId);
    }
}
