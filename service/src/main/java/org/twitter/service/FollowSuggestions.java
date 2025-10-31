package org.twitter.service;

import org.twitter.model.User;
import org.twitter.repository.FollowRepository;
import org.twitter.repository.UserRepository;
import org.twitter.service.interfaces.FollowSuggestionService;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * To suggest new members to the user to follow
 *
 * @version             1.0
 * @author              Mohamed Abdul Azif
 */
public final class FollowSuggestions implements FollowSuggestionService {

    /**
     * For Suggesting some members to the user.
     *
     * @param user      Logged-in User
     * @return          List of suggesting members
     */
    @Override
    public List<User> getSuggestedUsers(final User user) {
        final List<User> suggestions = new ArrayList<>();
        final Map<String, User> usersList = UserRepository.getUsersList();
        final Set<String> following = FollowRepository.getFollowing(user.getId());

        for (final User suggestUser : usersList.values()) {
            if (!suggestUser.getId().equals(user.getId()) &&
                    !following.contains(suggestUser.getId())) {
                suggestions.add(suggestUser);
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
        FollowRepository.follow(follower.getId(), toFollow.getId());
    }

    /**
     * Gets the list followers of the user.
     *
     * @param userId    Logged-in User ID
     * @return          Set of user's followers
     */
    @Override
    public Set<String> getFollowers(final String userId) {
        return FollowRepository.getFollowers(userId);
    }

    /**
     * Gets the set of users who are followed by the logged-user.
     *
     * @param userId    Logged-in User Id
     * @return          Set of users followed by the logged-user.
     */
    @Override
    public Set<String> getFollowing(final String userId) {
        return FollowRepository.getFollowing(userId);
    }
}
