package org.twitter.service.interfaces;

import org.twitter.model.User;

import java.util.List;
import java.util.Set;

public interface FollowSuggestionService {
    /**
     * Returns a list of users that the given user can follow.
     */
    List<User> getSuggestedUsers(User user);

    /**
     * Makes one user follow another.
     */
    void followUser(User follower, User toFollow);

    Set<String> getFollowers(String userId);

    Set<String> getFollowing(String userId);
}
