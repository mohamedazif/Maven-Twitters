package org.twitter.service;

import org.twitter.model.User;
import org.twitter.repository.UserRepository;
import org.twitter.service.interfaces.FollowSuggestionService;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * To suggest new members to the user to follow
 *
 * @version             1.0 15-Oct-2025
 * @author              Mohamed Abdul Azif
 */
public class FollowSuggestions implements FollowSuggestionService {

    /**
     * For Suggesting some members to the user.
     *
     * @param user      Logged-in User
     * @return          List of suggesting members
     */
    @Override
    public List<User> getSuggestedUsers(final User user) {
        List<User> suggestions = new ArrayList<>();
        Map<String, User> usersList = UserRepository.getUsersList();

        for (User suggUser : usersList.values()) {
            if (!suggUser.getUserId().equals(user.getUserId()) &&
                    !user.getFollowing().contains(suggUser.getUserId())) {
                suggestions.add(suggUser);
            }
        }

        return suggestions;
    }

    /**
     * Make the logged-in user to follow another user.
     *
     * @param follower  Logged-in User
     * @param toFollow  Member who user wants to follow
     */
    @Override
    public void followUser(final User follower, final User toFollow) {
        if (!follower.getFollowing().contains(toFollow.getUserId())) {
            follower.getFollowing().add(toFollow.getUserId());
            toFollow.getFollowers().add(follower.getUserId());
        }
    }
}
