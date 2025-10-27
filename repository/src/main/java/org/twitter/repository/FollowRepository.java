package org.twitter.repository;

import java.util.*;

/**
 * Repository for storing following and followers information.
 *
 * @version         1.0
 * @author          Mohamed Azif
 */
public final class FollowRepository {

    private static final Map<String, Set<String>> followers = new HashMap<>();
    private static final Map<String, Set<String>> following = new HashMap<>();

    /**
     * To follow an user.
     *
     * @param followerId    Logged-in user's ID
     * @param toFollowId    UserId of the user to follow
     */
    public static void follow(final String followerId, final String toFollowId) {
        followers
                .computeIfAbsent(toFollowId, k -> new HashSet<>())
                .add(followerId);

        following
                .computeIfAbsent(followerId, k-> new HashSet<>())
                .add(toFollowId);
    }

    /**
     * To unfollow an user.
     *
     * @param followerId    Logged-in user's ID
     * @param toUnfollowId  UserId of the user to unfollow
     */
    public static void unfollow(final String followerId,
                                final String toUnfollowId) {
        followers
                .getOrDefault(toUnfollowId, new HashSet<>())
                .remove(followerId);

        following
                .getOrDefault(followerId, new HashSet<>())
                .remove(toUnfollowId);
    }

    /**
     * To get the set of followers.
     *
     * @param userId        Logged-in UserID
     * @return              Set of users followers
     */
    public static Set<String> getFollowers(final String userId) {
        return followers.getOrDefault(userId, Collections.emptySet());
    }

    /**
     * To get the set of users followed by the user.
     *
     * @param userId        Logged-in UserID
     * @return              Set of users followed by the user.
     */
    public static Set<String> getFollowing(final String userId) {
        return following.getOrDefault(userId, Collections.emptySet());
    }
}
